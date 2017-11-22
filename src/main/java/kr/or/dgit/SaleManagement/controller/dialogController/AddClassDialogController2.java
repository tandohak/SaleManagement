package kr.or.dgit.SaleManagement.controller.dialogController;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import kr.or.dgit.SaleManagement.dto.BigClass;
import kr.or.dgit.SaleManagement.dto.Product;
import kr.or.dgit.SaleManagement.dto.SmallClass;
import kr.or.dgit.SaleManagement.service.BigClassService;
import kr.or.dgit.SaleManagement.service.SmallClassService;
import kr.or.dgit.SaleManagement.util.TextFieldUtil;

public class AddClassDialogController2 {
	@FXML
	private TextField bigTf;
	
	@FXML
	private TextField bigTf1;
	
	@FXML
	private TextField smallTf;
	
	@FXML
	private TextField smallTf2;
	
	@FXML
	private ComboBox<BigClass> bigCb;
	
	
	@FXML
	private TableView<BigClass> bigTb; 
	
	
	@FXML
	private TableColumn<BigClass, String> bNameTc;
	
	@FXML
	private TableColumn<BigClass, Integer> bCodeTc;
	
	
	@FXML
	private TableView<SmallClass> smallTb; 
	
	@FXML
	private TableColumn<SmallClass, String> bNameTc1;
	
	@FXML
	private TableColumn<SmallClass, String> sNameTc;
	
	@FXML
	private TableColumn<SmallClass, Integer> sCodeTc;
	
	
	@FXML
	private TableColumn<Product, String> nameTc;
	private TextFieldUtil tfUtil = new TextFieldUtil();
	
	
	private static BigClassService bigService;
	private static SmallClassService smallService;
	
	private ObservableList<BigClass> biglist = FXCollections.observableArrayList();
	private ObservableList<BigClass> biglistTb = FXCollections.observableArrayList();
//	private ObservableList<BigClass> biglistTb = FXCollections.observableArrayList();
	private ObservableList<SmallClass> smalllist =  FXCollections.observableArrayList();

	private boolean okClicked = false;

	
	@FXML
	private void initialize() {
		bigService = BigClassService.getInstance();
		smallService = SmallClassService.getInstance();
		
		List<BigClass> lists = bigService.findAll();
		
		
		for(BigClass b : lists) {
			BigClass b1 = new BigClass();
			b1.setBigClass(b.getBigClass());
			b1.setBigName(b.getBigName().trim());
			biglistTb.add(b1);
		}
		
		List<SmallClass> lists1 = smallService.findAll();
		

		
		for(SmallClass s : lists1) {
			BigClass b1 = new BigClass();
			BigClass b2 = new BigClass();
			b1.setBigClass(s.getsBigClass());
			b2 = bigService.findBigClassByBigClass(b1);
			b1.setBigClass(b2.getBigClass());
			b1.setBigName(b2.getBigName().trim());
			s.setBigName(b1.getBigNameProperty());
			smalllist.add(s);
		}
		
		bCodeTc.setCellValueFactory(cellData -> cellData.getValue().getBigClassProperty().asObject());
		bNameTc.setCellValueFactory(cellData -> cellData.getValue().getBigNameProperty());
		
		sCodeTc.setCellValueFactory(cellData -> cellData.getValue().getSmallClassProperty().asObject());
		sNameTc.setCellValueFactory(cellData -> cellData.getValue().getSmallNameProperty());
		bNameTc1.setCellValueFactory(cellData -> cellData.getValue().getBigNameProperty());
		
		bigTb.setItems(biglistTb);
		smallTb.setItems(smalllist);
		
		
		//resetBigCb();
		
		
		
	}

	private Boolean tfComfrimField() {
		try {
			tfUtil.tfComfrim(bigTf);
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
	
	private Boolean tfComfrimField1() {
		try {
			tfUtil.tfComfrim(smallTf);
			tfUtil.cbComfrim(bigCb);
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

	private void resetBigCb() {
		smallService = SmallClassService.getInstance();
		
		
		List<BigClass> blist = bigService.findAll();
		for(BigClass big : blist) {	
			biglist.add(big);
			//공백 콤보박스에 공백 들어가서 크기가 커지니까 trim()으로 공백 없앤뒤 입력해야함
		}
		bigCb.setItems(biglist);
	}
	
	
	
	@FXML
    private void bigAddAction() {
		
		String name = bigTf.getText();
		
		if(tfComfrimField()) {
			BigClass big = new BigClass();
			int maxCode = bigService.findMaxCode();
			
			big.setBigClass(maxCode+1);
			big.setBigName(name);
			
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText(big.toString()+"추가되었습니다.");
			alert.showAndWait();
					
			bigService.insertbigClass(big);
			bigService = BigClassService.getInstance();
			
			
			List<BigClass> lists = bigService.findAll();
			
			
			for(BigClass b : lists) {
				BigClass b1 = new BigClass();
				b1.setBigClass(b.getBigClass());
				b1.setBigName(b.getBigName().trim());
				biglistTb.add(b1);
			}
			bigTb.setItems(biglistTb);
			resetBigCb();
			okClicked = true;	
		}
	}
	
	@FXML
    private void smallAddAction() {
		if(tfComfrimField1()) {

			int big = bigCb.getValue().getBigClass();
			String name = smallTf.getText();
			
			SmallClass small = new SmallClass();
			int maxCode = smallService.findMaxCode();
			
			
			small.setsBigClass(big);
			small.setSmallName(name);
			small.setSmallClass(maxCode+1);
			
			smallService.insertSmallClass(small);
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText(name + "소분류추가");
			alert.showAndWait();
			
			
			
			
			smallService = SmallClassService.getInstance();
			List<SmallClass> lists1 = smallService.findAll();
			
			for(SmallClass s : lists1) {
				BigClass b1 = new BigClass();
				BigClass b2 = new BigClass();
				b1.setBigClass(s.getsBigClass());
				b2 = bigService.findBigClassByBigClass(b1);
				b1.setBigClass(b2.getBigClass());
				b1.setBigName(b2.getBigName().trim());
				s.setBigName(b1.getBigNameProperty());
				smalllist.add(s);
			}
		
			smallTb.setItems(smalllist);
			okClicked = true;	
		}
	}
	
	public boolean isOkClicked() {
		 return okClicked;
	}
	
}
