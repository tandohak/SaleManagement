package kr.or.dgit.SaleManagement.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import kr.or.dgit.SaleManagement.MainApp;
import kr.or.dgit.SaleManagement.controller.dialogController.SalesEditDialogController;
import kr.or.dgit.SaleManagement.dto.Account;
import kr.or.dgit.SaleManagement.dto.Sales;
import kr.or.dgit.SaleManagement.dto.SalesLevel;
import kr.or.dgit.SaleManagement.service.SalesLevelService;
import kr.or.dgit.SaleManagement.service.SalesService;
import kr.or.dgit.SaleManagement.util.TextFieldUtil;

public class SalesController {
	@FXML private BorderPane pane;
	@FXML private TextField searchAllTf;

	@FXML private TextField nameTf;	
	@FXML private Label codeLabel;
	@FXML private TextField idTf;	
	@FXML private TextField pwTf;	
	@FXML private TextField pwComfTf;	
	@FXML private TextField picTf;	
	@FXML private TextField addrTf;	
	@FXML private TextField addrZipTf;	
	@FXML private TextField telTf;
	
	@FXML private TableColumn<Sales, Integer> codeTc;
	@FXML private TableColumn<Sales, String> telTc;
	@FXML private TableColumn<Sales, String> nameTc;
	@FXML private TableColumn<Sales, String> levelTc;
	@FXML private TableColumn<Sales, String> leaveTc;
	@FXML private TableColumn<Sales, Boolean> chckTc;
	@FXML private TableColumn<Sales, String> addrTc;
	
	@FXML private ComboBox<SalesLevel> levelCb;
	@FXML private CheckBox saleCheck;
	@FXML private CheckBox allSales;
	
	@FXML private TableView<Sales> saleTable;
	
	@FXML private ImageView checkIdIcon;;
	@FXML private ImageView checkPwIcon;;
	
	@FXML private Button submitBtn;
	
	private TextFieldUtil tfUtil = new TextFieldUtil();
	private ObservableList<Sales> myList = FXCollections.observableArrayList();
	
	private ObservableList<SalesLevel> levellist = FXCollections.observableArrayList();
	
	private SalesService saleSerivce;
	private SalesLevelService slevelService;

	private boolean idCheckOk;

	private boolean pwCheckOk;
	
	@FXML
	private void initialize() {
		slevelService = SalesLevelService.getInstance();
		List<SalesLevel> sLevelLists = slevelService.findAllSalesLevel();
		for(SalesLevel saleLevel : sLevelLists) {
			levellist.add(saleLevel);
		}	
		levelCb.setItems(levellist);
		
		Sales leaveSales = new Sales();
		leaveSales.setSaleLeave("true");
		
		List<Sales> lists = saleSerivce.findSalesByLeave(leaveSales);
		for(Sales sale : lists) {
			myList.add(sale);
		}		
				
		chckTc.setCellFactory(new Callback<TableColumn<Sales,Boolean>, TableCell<Sales,Boolean>>() {
				@Override
				public TableCell<Sales, Boolean> call(TableColumn<Sales, Boolean> param) {
					CheckBoxTableCell<Sales, Boolean> checkBoxTbc = new CheckBoxTableCell<>();
					checkBoxTbc.setSelectedStateCallback(new Callback<Integer, ObservableValue<Boolean>>() {
						
						@Override
						public ObservableValue<Boolean> call(Integer index) {
							
							return saleTable.getItems().get(index).selectedProperty();
						}
					});
					
					return checkBoxTbc;
				}
		});
		
		saleCheck.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {
					for(Sales sale : myList) {
						sale.setCheckedBox(true);
					}
				}else {
					for(Sales sale : myList) {
						sale.setCheckedBox(false);
					}
				}							
			}
		
		});
		
				
		codeTc.setCellValueFactory(cellData -> cellData.getValue().getSaleCodeProperty().asObject());
		nameTc.setCellValueFactory(cellData -> cellData.getValue().getSaleNameProperty());
		telTc.setCellValueFactory(cellData -> cellData.getValue().getSaleTelProperty());
		levelTc.setCellValueFactory(cellData -> cellData.getValue().getSaleLevelProperty());
		leaveTc.setCellValueFactory(cellData -> cellData.getValue().getSaleLeaveProperty());
		addrTc.setCellValueFactory(cellData -> cellData.getValue().getSaleAddrProperty());;
		
		saleTable.setItems(myList);
	}
	
	
	
	
	@FXML
	private void deleteCellMenuAction() {
		int index = saleTable.getSelectionModel().getSelectedIndex();
		Sales sales = saleTable.getSelectionModel().getSelectedItem();
		saleSerivce.deleteSales(sales);
		saleTable.getItems().remove(index);
	}
	
	@FXML
	private void deleteSelectedCell(ActionEvent event) {
		for(int i=0; myList.size()>i; i++) {
			Sales sales = myList.get(i);
			
			if(sales.getCheckedBox()) {
				 myList.remove(sales);
				 saleSerivce.deleteSales(sales);
				 i = 0;
			};
		}
	}
	
	@FXML
	private void getCellMenuAction() {		
		Sales sales = saleTable.getSelectionModel().getSelectedItem();
		
		try {
			checkAlert(sales==null ? false : true,"수정할 열을 선택 해주세요.");
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
		        loader.setLocation(MainApp.class.getResource("view/dialog/SalesEditDialog.fxml"));
		        BorderPane page = (BorderPane) loader.load();

		        Stage dialogStage = new Stage();
		        dialogStage.setTitle(null);
		        dialogStage.initModality(Modality.WINDOW_MODAL);		        
		        dialogStage.initOwner(pane.getScene().getWindow());
		        Scene scene = new Scene(page);
		        dialogStage.setScene(scene);
		        
		        SalesEditDialogController controller = loader.getController();
		        controller.setDialogStage(dialogStage);
		        controller.setLevellist(levellist);
		        controller.setSales(sales);
		        
		        dialogStage.showAndWait();

		        if(controller.isOkClicked()) {
		        	System.out.println(controller.getSales());
		        	saleSerivce.updateSales(controller.getSales());
		        	refreshTable();
		        }
		   } catch (IOException e) {
		        e.printStackTrace();
		   }	
	}
	
	
	@FXML
	private void submitClickAction() {		
		if(tfComfrimField()) {
			try {
				checkAlert(idCheckOk,"아이디 중복 체크를 해주세요.");
				checkAlert(pwCheckOk,"비밀번호가 같지 않습니다.");				
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle(null);
				alert.setHeaderText(null);
				alert.setContentText(e.getMessage());
				alert.showAndWait();
				e.printStackTrace();
				return;
			}
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle(null);			
			alert.setHeaderText(null);
			alert.setContentText("정말 등록하시겠습니까?");
			
			ButtonType clickType= alert.showAndWait().get();
			
			if(clickType.getText().equals("취소")) {
				return;
			}
			
			Sales sales = new Sales();
			sales.setSaleName(nameTf.getText());
			sales.setSaleId(idTf.getText());
			sales.setSalePw(pwTf.getText());
			sales.setSaleTel(telTf.getText());
			sales.setSaleAddr("["+ addrZipTf.getText() + "]" + addrTf.getText());
			sales.setSaleLevel(levelCb.getValue().getSalLevel());
			String code = "";
			String year   = new java.text.SimpleDateFormat("yy").format(new java.util.Date());
			
			int codeNum = Integer.parseInt(year+00001);
			int maxCode = saleSerivce.findMaxCode();
			
			if(maxCode >= codeNum) {				
				maxCode++;
				String maxCodeStr = maxCode+"";
				code += year+ maxCodeStr.substring(2, maxCodeStr.length());
				codeNum = Integer.parseInt(code);
			}
			sales.setSaleCode(codeNum);
			saleSerivce.insertSales(sales);
			refreshTable();
		}
	}

	private void checkAlert(boolean isOk,String pwck) throws Exception {
		if(!isOk) {
			throw new Exception(pwck);
		}
	}
	
	@FXML
	private void idTypeCheck(KeyEvent event) {
		idCheckOk = false;
		checkIdIcon.setVisible(false);		
	}
	
		
	@FXML
	private void idTypeHandler() {
		String path = System.getProperty("user.dir");
		
		try {
			tfUtil.regexTfComfirmId(idTf);
		} catch (Exception e) {		
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			e.printStackTrace();
			return;
		}
		
		idCheckOk = tfUtil.idOverlapCheck(idTf);
		
		if(idCheckOk) {
			File file = new File(path + "/DataFile/ic_check_circle_black_48dp_1x.png" );
			Image image = new Image(file.toURI().toString());
			checkIdIcon.setImage(image);
			checkIdIcon.setVisible(true);
		}else {
			checkIdIcon.setVisible(false);
		}	
	}
	
	private void refreshTable() {
		myList = FXCollections.observableArrayList();
		saleSerivce = SalesService.getInstance();
		List<Sales> lists = saleSerivce.findSaleAll();
		for(Sales sale : lists) {
			myList.add(sale);
		}
		saleTable.setItems(myList);
	}
	
	@FXML
	private void pwTypeHandler(KeyEvent event) {
		String path = System.getProperty("user.dir");
		String pwVal =pwTf.getText();
		String pwComVal = pwComfTf.getText();
	
		if(pwVal.equals("") || pwComVal.equals("")) {
			checkPwIcon.setVisible(false);
		}else if(pwVal.equals(pwComVal)) {
			File file = new File(path + "/DataFile/ic_check_circle_black_48dp_1x.png" );
			Image image = new Image(file.toURI().toString());
			checkPwIcon.setImage(image);
			checkPwIcon.setVisible(true);
			pwCheckOk = true;
		}else {
			File file = new File(path + "/DataFile/ic_block_black_48dp_1x.png" );
			Image image = new Image(file.toURI().toString());
			checkPwIcon.setImage(image);
			checkPwIcon.setVisible(true);
			pwCheckOk = false;
		}
	}

	private Boolean tfComfrimField() {
		try {
			tfUtil.regexTfComfirmSaleName(nameTf);
			tfUtil.regexTfComfirmPw(pwTf);
			tfUtil.regexTfComfirmTel(telTf);
			tfUtil.tfComfrim(nameTf);
			tfUtil.cbComfrim(levelCb);
			tfUtil.tfComfrim(idTf);
			tfUtil.tfComfrim(pwTf);
			tfUtil.tfComfrim(pwComfTf);
			tfUtil.tfComfrim(telTf);
			tfUtil.tfComfrim(addrTf);
			tfUtil.tfComfrim(addrZipTf);
			return true;
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			e.printStackTrace();
			return false;
		}		
	}
	
	@FXML
	private boolean allSalesCheck() {
		return allSales.isSelected();
	}
	
	@FXML
	private void searchSales() {
		Sales findSales = new Sales();
		List<Sales> lists;
		findSales.setSaleName("%" + searchAllTf.getText() + "%");
		if(findSales.getSaleName().equals("%%")) {
			if(allSalesCheck()) {
				refreshTable();
			}
			else {
				findSales.setSaleName(null);
				findSales.setSaleLeave("true");
				lists = saleSerivce.findSalesByLeave(findSales);
				setSalesModel(lists);
			}
			return;
		}
		if(!allSalesCheck()) {
			findSales.setSaleLeave("true");
			lists = saleSerivce.findSalesLikeName(findSales);
		}
		else {
			lists = saleSerivce.findSalesLikeName(findSales);
		}
		setSalesModel(lists);
	}
	
	
	private void setSalesModel(List<Sales> lists) {
		myList = FXCollections.observableArrayList();
		for(Sales sales : lists) {
			myList.add(sales);
		}
		saleTable.setItems(myList);
	}
}
