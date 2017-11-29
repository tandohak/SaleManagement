package kr.or.dgit.SaleManagement.controller;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import kr.or.dgit.SaleManagement.MainApp;
import kr.or.dgit.SaleManagement.controller.dialogController.RecordEditDialogController;
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
	    @FXML private TableColumn<Record, Integer> sumPriceTc;
	    @FXML private TableColumn<Record, Integer> dispriceTc;
	    @FXML private TableColumn<Record, Integer> disrateTc;
	    @FXML private TableColumn<Record, Integer> countTc;
	    @FXML private TableColumn<Record, String> saleNameTc;
	    @FXML private TableColumn<Record, String> telTc;
	    @FXML private ButtonBar btnBar;
	    
	    private TextFieldUtil tfUtil = new TextFieldUtil();
		private ObservableList<Record> myList = FXCollections.observableArrayList();
		
		private ObservableList<String> optionlist = FXCollections.observableArrayList();
		
		private SalesService saleService;
		private AccountService accService;
		private ProductService pdtService;
	    private RecordSerivce recService;
		private boolean isUserLogin = false;
		private boolean isAccUserLogin;
	    
		@FXML
		private void initialize() {
			saleService = SalesService.getInstance();
			accService = AccountService.getInstance();
			pdtService = ProductService.getInstance();
			recService = RecordSerivce.getInstance();

			List<Record> reclists = recService.findRecordByAll();
			insesrtTable(reclists);
					
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
			sumPriceTc.setCellValueFactory(cellData -> cellData.getValue().getSumPriceProperty().asObject());
			dispriceTc.setCellValueFactory(cellData -> cellData.getValue().getRecDispriceProperty().asObject());;
			disrateTc.setCellValueFactory(cellData -> cellData.getValue().getRecDisrateProperty().asObject());;
			countTc.setCellValueFactory(cellData -> cellData.getValue().getRecCountProperty().asObject());;
			saleNameTc.setCellValueFactory(cellData -> cellData.getValue().getSaleNameProperty());	
			telTc.setCellValueFactory(cellData -> cellData.getValue().getSaleTelProperty());
			
			noTc.setSortType(TableColumn.SortType.DESCENDING);
			
			//recTable 역순 정렬
			recTable.getSortOrder().add(noTc);
			
			
			FilteredList<Record> filterData = new FilteredList<>(myList, r -> true);
			searchAllTf.textProperty().addListener((observable, oldValue, newValue)->{
				filterData.setPredicate(record ->{
					 if (newValue == null || newValue.isEmpty()) {
		                    return true;
		                }
					 
					 //대문자 -> 소문자로 변경
					 String lowerCaseFilter = newValue.toLowerCase();				
					 String recordAccName = record.getAccName().toLowerCase();
					 String recordSaleName = record.getSaleNamey().toLowerCase();
					 String recordPdtName = record.getPdtName().toLowerCase();
					 
					 if(recordAccName.contains(lowerCaseFilter)) {
						 return true;
					 }
					 
					 if(recordSaleName.contains(lowerCaseFilter)) {
						 return true;
					 }
					 
					 if(recordPdtName.contains(lowerCaseFilter)) {
						 return true;
					 }
					 
					return false;
				});
			});
			
			// 필터리스트를 sorted리스트에 넣는다
			SortedList<Record> sortedData = new SortedList<>(filterData);
			
			sortedData.comparatorProperty().bind(recTable.comparatorProperty());
			
	        recTable.setItems(sortedData);
	        
	        recTable.setRowFactory(tv -> {
				 TableRow<Record> row = new TableRow<>();
				    row.setOnMouseClicked(event -> {
				        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
				             && event.getClickCount() == 2) {
				        	Record clickedRow = row.getItem();
				            showEditDialog(clickedRow);
				        }
				    });
				    return row ;
			});
		}
		
		public void setSaleUserSetting(Sales saleUser) {
			isUserLogin = true;
			FilteredList<Record> filterData = new FilteredList<>(myList, r -> true);
			
			filterData.setPredicate(record ->{
				 if ((saleUser.getSaleCode() == record.getrSalecode())) {
	                    return true;
	                }
				 
				return false;
			});
			
			searchAllTf.textProperty().addListener((observable, oldValue, newValue)->{
				filterData.setPredicate(record ->{
					boolean saleOk = saleUser.getSaleCode() == record.getrSalecode();
					 if ((newValue == null || newValue.isEmpty()) && saleOk) {
		                    return true;
		                }
					 
					 //대문자 -> 소문자로 변경
					 String lowerCaseFilter = newValue.toLowerCase();				
					 String recordAccName = record.getAccName().toLowerCase();
					 String recordPdtName = record.getPdtName().toLowerCase();
					 
					 if(recordAccName.contains(lowerCaseFilter) && saleOk) {
						 return true;
					 }
					 
					 if(recordPdtName.contains(lowerCaseFilter) && saleOk) {
						 return true;
					 }
					 
					return false;
				});
			});
			
			// 필터리스트를 sorted리스트에 넣는다
			SortedList<Record> sortedData = new SortedList<>(filterData);
			
			sortedData.comparatorProperty().bind(recTable.comparatorProperty());
			
	        recTable.setItems(sortedData);	    
		}
		
		public void setUserAccSetting(Account accUser) {
			isAccUserLogin = true;
			btnBar.setVisible(false);
			chckTc.setVisible(false);
			telTc.setVisible(true);
			saleNameTc.setPrefWidth(75);
			dispriceTc.setPrefWidth(50);
			disrateTc.setPrefWidth(50);
			telTc.setPrefWidth(120);
			FilteredList<Record> filterData = new FilteredList<>(myList, r -> true);
			
			filterData.setPredicate(record ->{	
				if(record.getAccName().equals(accUser.getAccName()) ) {
					 return true;
				 }
				return false;
			});
			
			searchAllTf.textProperty().addListener((observable, oldValue, newValue)->{
				filterData.setPredicate(record ->{
					 boolean userOk = record.getAccName().contains(accUser.getAccName());
					 if ((newValue == null || newValue.isEmpty()) && userOk) {
		                    return true;
		                }
					 
					 //대문자 -> 소문자로 변경
					 
					 String lowerCaseFilter = newValue.toLowerCase();				
					 String recordSaleName = record.getSaleNamey().toLowerCase();
					 String recordPdtName = record.getPdtName().toLowerCase();
					 if(recordSaleName.contains(lowerCaseFilter) && userOk) {
						 return true;
					 } 
					 
					 if(recordPdtName.contains(lowerCaseFilter) && userOk) {
						 return true;
					 }
					 
					return false; 
				});
			});
			
			// 필터리스트를 sorted리스트에 넣는다
			SortedList<Record> sortedData = new SortedList<>(filterData);
			
			sortedData.comparatorProperty().bind(recTable.comparatorProperty());
			
	        recTable.setItems(sortedData);
	        
	        recTable.setRowFactory(tv -> {
				 TableRow<Record> row = new TableRow<>();
				    row.setOnMouseClicked(null);
				    return row ;
			});
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
			
			showEditDialog(rec);
			  
		}

		private void showEditDialog(Record rec) {
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
		        if(isUserLogin) {
		        	controller.setSaleUserSetting();
		        }
		        
		        dialogStage.showAndWait();		        
		        
		        if(controller.isOkClicked()) {
		        	Record record = controller.getRecord();
		        	recService.updateRecord(record);
		        	recTable.refresh();
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
		
		
		private void insesrtTable(List<Record> reclists) {

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
				rec.setSumPrice(pdt.getPdtCost()*rec.getRecCount());
				rec.setSaleTel(sale.getSaleTel());
				myList.add(rec);				
			}		
		}

		
			
		    
}
