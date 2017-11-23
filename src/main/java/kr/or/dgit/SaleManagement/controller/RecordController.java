package kr.or.dgit.SaleManagement.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import com.mysql.jdbc.Util;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import kr.or.dgit.SaleManagement.MainApp;
import kr.or.dgit.SaleManagement.controller.dialogController.RecordEditDialogController;
import kr.or.dgit.SaleManagement.controller.dialogController.SalesEditDialogController;
import kr.or.dgit.SaleManagement.dto.Account;
import kr.or.dgit.SaleManagement.dto.Product;
import kr.or.dgit.SaleManagement.dto.Record;
import kr.or.dgit.SaleManagement.dto.Sales;
import kr.or.dgit.SaleManagement.service.AccountService;
import kr.or.dgit.SaleManagement.service.ProductService;
import kr.or.dgit.SaleManagement.service.RecordSerivce;
import kr.or.dgit.SaleManagement.service.SalesService;
import kr.or.dgit.SaleManagement.util.TextFieldUtil;

public class RecordController {
		@FXML private BorderPane pane;
	    @FXML private TextField searchAllTf;
	    @FXML private ComboBox<String> optionCb;
	    
	    @FXML private TableView<Record> recTable;
	    @FXML private TableColumn<Record, Boolean> chckTc;
	    @FXML private CheckBox recCheck;
	    @FXML private TableColumn<Record, Integer> noTc;
	    @FXML private TableColumn<Record, String> dateTc;
	    @FXML private TableColumn<Record, String> accNameTc;
	    @FXML private TableColumn<Record, String> pdtNameTc;
	    @FXML private TableColumn<Record, Integer> costTc;
	    @FXML private TableColumn<Record, Integer> priceTc;
	    @FXML private TableColumn<Record, Integer> dispriceTc;
	    @FXML private TableColumn<Record, Integer> disrateTc;
	    @FXML private TableColumn<Record, Integer> countTc;
	    @FXML private TableColumn<Record, String> saleNameTc;
	    
	    private TextFieldUtil tfUtil = new TextFieldUtil();
		private ObservableList<Record> myList = FXCollections.observableArrayList();
		
		private ObservableList<String> optionlist = FXCollections.observableArrayList();
		
		private SalesService saleService;
		private AccountService accService;
		private ProductService pdtService;
	    private RecordSerivce recService;
	    
		@FXML
		private void initialize() {
			saleService = SalesService.getInstance();
			accService = AccountService.getInstance();
			pdtService = ProductService.getInstance();
			recService = RecordSerivce.getInstance();
			
			refreshTable();
					
			chckTc.setCellFactory(new Callback<TableColumn<Record,Boolean>, TableCell<Record,Boolean>>() {
					@Override
					public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> param) {
						CheckBoxTableCell<Record, Boolean> checkBoxTbc = new CheckBoxTableCell<>();
						checkBoxTbc.setSelectedStateCallback(new Callback<Integer, ObservableValue<Boolean>>() {
							
							@Override
							public ObservableValue<Boolean> call(Integer index) {
								
								return recTable.getItems().get(index).selectedProperty();
							}
						});
						
						return checkBoxTbc;
					}
			});
			
			recCheck.selectedProperty().addListener(new ChangeListener<Boolean>() {

				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					if(newValue) {
						for(Record rec : myList) {
							rec.setCheckedBox(true);
						}
					}else {
						for(Record rec : myList) {
							rec.setCheckedBox(false);
						}
					}							
				}
				
			});
			
					
			noTc.setCellValueFactory(cellData -> cellData.getValue().getRecNoProperty().asObject());
			dateTc.setCellValueFactory(cellData -> {
	              SimpleStringProperty property = new SimpleStringProperty();
	              DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	              property.setValue(formatter.format(cellData.getValue().getRecDate()));
	              return property;
	           });
			accNameTc.setCellValueFactory(cellData -> cellData.getValue().getAccNameProperty());
			pdtNameTc.setCellValueFactory(cellData -> cellData.getValue().getPdtNameProperty());
			costTc.setCellValueFactory(cellData -> cellData.getValue().getRecCostProperty().asObject());
			priceTc.setCellValueFactory(cellData -> cellData.getValue().getRecPriceProperty().asObject());
			dispriceTc.setCellValueFactory(cellData -> cellData.getValue().getRecDispriceProperty().asObject());;
			disrateTc.setCellValueFactory(cellData -> cellData.getValue().getRecDisrateProperty().asObject());;
			countTc.setCellValueFactory(cellData -> cellData.getValue().getRecCountProperty().asObject());;
			saleNameTc.setCellValueFactory(cellData -> cellData.getValue().getAccNameProperty());		
			
			recTable.setItems(myList);
		}
		
		@FXML
		private void deleteCellMenuAction() {
			int index = recTable.getSelectionModel().getSelectedIndex();
			Record rec = recTable.getSelectionModel().getSelectedItem();
			recService.deleteRecord(rec);
			recTable.getItems().remove(index);
		}
		
		@FXML
		private void deleteSelectedCell(ActionEvent event) {
			for(int i=0; myList.size()>i; i++) {
				Record rec = myList.get(i);
				
				if(rec.getCheckedBox()) {
					 myList.remove(rec);
					 recService.deleteRecord(rec);
					 i = 0;
				};
			}
		}
		
		@FXML
		private void getCellMenuAction() {		
			Record rec = recTable.getSelectionModel().getSelectedItem();
			
			try {
				checkAlert(rec==null ? false : true,"수정할 열을 선택 해주세요.");
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle(null);
				alert.setHeaderText(null);
				alert.setContentText(e.getMessage());
				alert.showAndWait();
				e.printStackTrace();
				e.printStackTrace();
				return ;
			}

			try {
		        FXMLLoader loader = new FXMLLoader();
		        loader.setLocation(MainApp.class.getResource("view/dialog/RecordEditDialog.fxml"));
		        BorderPane page = (BorderPane) loader.load();

		        Stage dialogStage = new Stage();
		        dialogStage.setTitle(null);
		        dialogStage.initModality(Modality.WINDOW_MODAL);		        
		        dialogStage.initOwner(pane.getScene().getWindow());
		        Scene scene = new Scene(page);
		        dialogStage.setScene(scene);
		        
		        RecordEditDialogController controller = loader.getController();
		        controller.setDialogStage(dialogStage);
		        controller.setRecord(rec);
		        dialogStage.showAndWait();

		        if(controller.isOkClicked()) {
		        
		        	refreshTable();
		        }
		   } catch (IOException e) {
		        e.printStackTrace();
		   }
			  
		}
		
		private void checkAlert(boolean isOk,String pwck) throws Exception {
			if(!isOk) {
				throw new Exception(pwck);
			}
		}
		
		
		private void refreshTable() {
			List<Record> reclists = recService.findRecordByAll();

			for(Record rec : reclists) {				
				Product pdt = new Product();
				System.out.println(rec.getrProductCode());
				pdt.setPdtCode(rec.getrProductCode());
				pdt = pdtService.findBypdtProduct(pdt);
				System.out.println(pdt);
				Account acc = new Account();
				acc.setAccCode(pdt.getAccCode());
				acc = accService.findAccountByCode(acc);
				Sales sale = new Sales();
				sale.setSaleCode(rec.getrSalecode());
				sale =saleService.findSalesByCode(sale);
				
				rec.setAccName(acc.getAccName());
				rec.setPdtName(pdt.getPdtName());
				rec.setSaleName(sale.getSaleName());
				rec.setRecCost(pdt.getPdtCost());
				rec.setRecPrice(pdt.getPdtPrice());
				
				myList.add(rec);
			}		
		}
			
		    
}
