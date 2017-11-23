package kr.or.dgit.SaleManagement.controller.dialogController;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
	
	@FXML private Button bigBtn;
	@FXML private Button smallBtn;
	
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
		
		
		resetBigCb();
		
		
		
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
		biglist = FXCollections.observableArrayList();
		smallService = SmallClassService.getInstance();
		
		List<BigClass> blist = bigService.findAll();
		for(BigClass big : blist) {	
			biglist.add(big);
		}
		bigCb.setItems(biglist);
	}
	
	private void checkAlert(boolean isOk,String pwck) throws Exception {
		if(!isOk) {
			throw new Exception(pwck);
		}
	}
	
	
	//대분류 수정, 추가
	BigClass bigCh = new BigClass();
	
	@FXML
    private void bigchangeBtn() {
		bigBtn.setText("수정");
		bigCh = bigTb.getSelectionModel().getSelectedItem();
		
		try {
			checkAlert(bigCh==null ? false : true,"수정할 열을 선택 해주세요.");
		}catch(Exception e){
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			e.printStackTrace();
			e.printStackTrace();
			return ;
		}
		if(bigCh!=null) {
			bigTf.setText(bigCh.getBigName());
		}
	}
	
	@FXML
    private void bigAddBtn() {
		bigBtn.setText("추가");
		bigTf.setText("");
	}
	
	
	
	@FXML
    private void bigAddAction() {
		if(bigBtn.getText()=="추가") {
			
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
				bigTableReset();
				smallTableReset();
				
				okClicked = true;	
			}
		}else {
			String name = bigTf.getText();
			if(tfComfrimField()) {
				bigCh.setBigName(name);
				
				bigService.updatebigClass(bigCh);
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle(null);
				alert.setHeaderText(null);
				alert.setContentText(bigCh.toString()+"수정되었습니다.");
				alert.showAndWait();
				
				bigTableReset();
				smallTableReset();
				okClicked = true;	
				//bigBtn.setText("추가");
			}
		}
	}
	
	//소분류 추가 수정
	@FXML
    private void smallAddBtn() {
		smallBtn.setText("추가");
		resetBigCb();
		smallTf.setText(" ");
	}

	SmallClass smallCh = new SmallClass();
	
	@FXML
    private void smallchangeBtn() {
		smallBtn.setText("수정");
		smallCh = smallTb.getSelectionModel().getSelectedItem();
		
		try {
			checkAlert(smallCh==null ? false : true,"수정할 열을 선택 해주세요.");
		}catch(Exception e){
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			e.printStackTrace();
			e.printStackTrace();
			return ;
		}
		if(smallCh!=null) {
			BigClass b1 = new BigClass();
			BigClass b2 = new BigClass();
			b1.setBigClass(smallCh.getsBigClass());
			b2 = bigService.findBigClassByBigClass(b1);

			bigCb.setValue(b2);
			smallTf.setText(smallCh.getSmallName());
		}
	}
	
	@FXML
    private void smallAddAction() {
		String name = smallTf.getText();
		if(smallBtn.getText()=="수정") {
			if(tfComfrimField1()) {
					
					smallCh.setSmallName(name);
					smallCh.setsBigClass(bigCb.getValue().getBigClass());
					smallService.updateSmallClass(smallCh);
					
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle(null);
					alert.setHeaderText(null);
					alert.setContentText(name + "소분류 수정");
					alert.showAndWait();
					
					smallTableReset();
					okClicked = true;		
				}
		}else {
			if(tfComfrimField1()) {

					int big = bigCb.getValue().getBigClass();
					
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
					
					smallTableReset();
					okClicked = true;	
				}
			}	
	}
	
	
	//reset
	private void bigTableReset() {
		biglistTb = FXCollections.observableArrayList();
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
	}

	private void smallTableReset() {
		smalllist =  FXCollections.observableArrayList();
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
	}
	
	public boolean isOkClicked() {
		 return okClicked;
	}
	
}
