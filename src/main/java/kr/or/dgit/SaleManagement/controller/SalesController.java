package kr.or.dgit.SaleManagement.controller;

import java.io.File;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
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
import javafx.util.Callback;
import kr.or.dgit.SaleManagement.dto.Sales;
import kr.or.dgit.SaleManagement.dto.SalesLevel;
import kr.or.dgit.SaleManagement.service.SalesLevelService;
import kr.or.dgit.SaleManagement.service.SalesService;
import kr.or.dgit.SaleManagement.util.TextFieldUtil;

public class SalesController {
	@FXML private TextField searchAllTf;

	@FXML private TextField nameTf;	
	@FXML private Label codeLabel;
	@FXML 
	private TextField idTf;	
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
	
	@FXML private TableView<Sales> saleTable;
	
	@FXML private ImageView checkIdIcon;;
	@FXML private ImageView checkPwIcon;;
	
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
		
		
		saleSerivce = SalesService.getInstance();
		List<Sales> lists = saleSerivce.findSaleAll();
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
	private void deleteSelectedCell(ActionEvent event) {
		for(int i=0; myList.size()>i; i++) {
			Sales Sales = myList.get(i);
			
			if(Sales.getCheckedBox()) {
				 myList.remove(Sales);
				 saleSerivce.deleteSales(Sales);
				 i = 0;
			};
		}
	}
	
	@FXML
	private void submitClickAction() {
		if(tfComfrimField()) {
			try {
				checkAlert(idCheckOk,"아이디 중복 체크를 해주세요.");
				checkAlert(pwCheckOk,"비밀번호가 같지 않습니다.");
				
				tfUtil.regexTfComfirmSaleName(nameTf);
				tfUtil.regexTfComfirmPw(pwTf);
				tfUtil.regexTfComfirmTel(telTf);
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle(null);
				alert.setHeaderText(null);
				alert.setContentText(e.getMessage());
				alert.showAndWait();
				e.printStackTrace();
				return;
			}
			
			Alert alert =new Alert(AlertType.CONFIRMATION);
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
	
	@FXML
	private void pwTypeHandler(KeyEvent event) {
		String path = System.getProperty("user.dir");
		String pwVal =pwTf.getText();
		String pwComVal = pwComfTf.getText();
	
		if(pwVal.equals("") || pwComVal.equals("")) {
			 
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
			alert.setTitle("공백존재");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			e.printStackTrace();
			return false;
		}
		
	}
}
