package kr.or.dgit.SaleManagement.controller;

import java.awt.Event;
import java.io.IOException;
import java.util.List;
import java.util.Observer;

import javax.swing.text.StyledEditorKit.BoldAction;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import kr.or.dgit.SaleManagement.MainApp;
import kr.or.dgit.SaleManagement.ProductTestMain;
import kr.or.dgit.SaleManagement.controller.dialogController.SalesEditDialogController;
import kr.or.dgit.SaleManagement.dto.Account;
import kr.or.dgit.SaleManagement.dto.BigClass;
import kr.or.dgit.SaleManagement.dto.Product;
import kr.or.dgit.SaleManagement.dto.SalesLevel;
import kr.or.dgit.SaleManagement.dto.SmallClass;
import kr.or.dgit.SaleManagement.service.AccountService;
import kr.or.dgit.SaleManagement.service.BigClassService;
import kr.or.dgit.SaleManagement.service.ProductService;
import kr.or.dgit.SaleManagement.service.SmallClassService;
import kr.or.dgit.SaleManagement.util.TextFieldUtil;

public class ProductController {
	@FXML private BorderPane pane;
	@FXML
	private TextField searchTf;
	
	@FXML
	private Button searchBtn;
	
	@FXML
	private TextField nameTf;
	
	@FXML
	private ComboBox<String> admitCb;
	
	@FXML
	private TextField costTf;
	
	@FXML
	private TextField priceTf;
	
	@FXML
	private ComboBox<BigClass> bigCb;
	
	@FXML
	private ComboBox<SmallClass> smallCb;
	
	@FXML
	private Button classAddBtn;
	
	@FXML
	private Button deleteBtn;
	
	@FXML
	private Button changeBtn;
	
	@FXML
	private Button addBtn;
	
	@FXML
	private TableView<Product> pdtTable;
	
	@FXML
	private CheckBox pdtCheck;
	@FXML
	private TableColumn<Product, Boolean> chckTc;
	
	@FXML
	private TableColumn<Product, Integer> codeTc;
	
	@FXML
	private TableColumn<Product, String> nameTc;
	
	@FXML
	private TableColumn<Product, String> accTc;
	
	@FXML
	private TableColumn<Product, Integer> costTc;
	
	@FXML
	private TableColumn<Product, Integer> priceTc;
	
	@FXML private TableColumn<Product, String> admitTc;
	
	
	private ObservableList<Product> levellist = FXCollections.observableArrayList();
	
	private static ProductService pdtService;
	private static BigClassService bigService;
	private static SmallClassService smallService;
	private static AccountService accService;
	
	private ObservableList<Product> myList = FXCollections.observableArrayList();
	
	private ObservableList<BigClass> biglist = FXCollections.observableArrayList();
	private ObservableList<SmallClass> smalllist = FXCollections.observableArrayList();
	
	private ObservableList<String> abminlist = FXCollections.observableArrayList();
	
	private TextFieldUtil tfUtil = new TextFieldUtil();
	
	
	@FXML
	private void initialize() {
		abminlist.add("true");
		abminlist.add("false");
		pdtService = ProductService.getInstance();
		accService = AccountService.getInstance();
		
		Account findAcc = new Account();
		Account resAcc = new Account();
		List<Product> lists = pdtService.findAll();
		for(Product pdt : lists) {
			findAcc.setAccCode(pdt.getAccCode());
			resAcc = accService.findAccountByCode(findAcc);
			pdt.setAccName(resAcc.getAccNameProperty());
			myList.add(pdt);
		}
		
		bigService = BigClassService.getInstance();
		List<BigClass> blist = bigService.findAll();
		
		
		for(BigClass big : blist) {	
			biglist.add(big);
			//공백 콤보박스에 공백 들어가서 크기가 커지니까 trim()으로 공백 없앤뒤 입력해야함
		}
		
//		smallService = SmallClassService.getInstance();
//		List<SmallClass> slist = smallService.findAll();
//		for(SmallClass small: slist) {
//			smalllist.add(small);
//		}
		
		/* ********************* *
		 * 테이블 cell안 체크박스 삽입   *
		 * ********************* */
		chckTc.setCellFactory(new Callback<TableColumn<Product,Boolean>,TableCell<Product,Boolean>>(){
	        @Override 
	        public TableCell<Product,Boolean> call( TableColumn<Product,Boolean> p ){
	           CheckBoxTableCell<Product, Boolean> checkBoxTbC = new CheckBoxTableCell<>(); 
	           checkBoxTbC.setSelectedStateCallback(new Callback<Integer, ObservableValue<Boolean>>() {
	        	   //checkbox에 callback 함수 달기 
				@Override
				public ObservableValue<Boolean> call(Integer index) {
					
					return pdtTable.getItems().get(index).selectedProperty();
					 //필드의 변동을 확인하기 위해 table에서 item을 받아와 selectedProperty() 함수를 받아 리턴한다.
					 // ObservableValue<T>를 리턴하여 ui에서 변동된 사항을 감지한다.
				}
			});
	           
	           return checkBoxTbC;//checboxtablecell 리턴
	      
	           }
	        });		
		
		
		
		
		/**
		 * 전체 선택 체크 체인지 리스너
		 * */
		pdtCheck.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {
					for(Product pdt : myList) {
						pdt.setCheckedBox(true);
					}
				}else {
					for(Product pdt : myList) {
						pdt.setCheckedBox(false);
					}
				}				
			}
			
		});
		
//		accService = AccountService.getInstance();
//		
//		accService.findAccountByCode(new Account());
		//cellData.getValue().getAccCodeProperty().asObject()
		
		codeTc.setCellValueFactory(cellData -> cellData.getValue().getPdtCodeProperty().asObject());
		nameTc.setCellValueFactory(cellData -> cellData.getValue().getPdtNameProperty());
		accTc.setCellValueFactory(cellData -> cellData.getValue().getAccNameProperty());
		costTc.setCellValueFactory(cellData -> cellData.getValue().getPdtCostProperty().asObject());
		priceTc.setCellValueFactory(cellData -> cellData.getValue().getPdtPriceProperty().asObject());
		admitTc.setCellValueFactory(cellData -> cellData.getValue().getPdtAdmitProperty());

		
		pdtTable.setItems(myList);
		bigCb.setItems(biglist);
		//smallCb.setItems(smalllist);
		admitCb.setItems(abminlist);
		
	}
	
	public ProductController() {}
	
	@FXML
	private void deleteSelectedCell(ActionEvent event) {
		
		for(int i=0; myList.size()>i; i++) {
			Product pdt = myList.get(i);
			System.out.println(pdt);
			if(pdt.getCheckedBox()) {
				 myList.remove(pdt);
				 pdtService.deleteProduct(pdt);
				 i = 0;
			};
		}
	}
	
	
	private ProductTestMain mainApp;
	
	public void setMainApp(ProductTestMain mainApp) {
	        this.mainApp = mainApp;
	        
	  }
	
	
	
	@FXML
	public void comboboxChange() {
	/*	Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(null);
		alert.setHeaderText(null);
		alert.setContentText("a");
		alert.showAndWait();*/
		
		int big = bigCb.getValue().getBigClass();
		SmallClass sm = new SmallClass();
		sm.setsBigClass(big);
		
		smalllist = FXCollections.observableArrayList();
		
		smallService = SmallClassService.getInstance();
		List<SmallClass> slist = smallService.findByBigClass(sm);
		for(SmallClass small: slist) {
			smalllist.add(small);
		}
		smallCb.setItems(smalllist);
	}
	
	@FXML
	public void submitClickAction() {
		
		if(tfComfrimField()) {
			
			int maxCode = pdtService.findMaxCode();
			
			Product pdt = new Product();
			
			pdt.setPdtCode(maxCode+1);
			pdt.setPdtName(nameTf.getText());
			pdt.setPdtClass(smallCb.getValue().getSmallClass());
			pdt.setPdtAdmit(admitCb.getValue().toString());
			pdt.setPdtCost(Integer.parseInt(costTf.getText().trim()));
			pdt.setPdtPrice(Integer.parseInt(priceTf.getText().trim()));
			//pdt.setAccCode(21722051);
			pdtService.insertProduct(pdt);
			
			myList = FXCollections.observableArrayList();
			
			refreshTable();
		}
		
	}
	

	@FXML
	public void SearchClickAction() {

		myList = FXCollections.observableArrayList();
		
		String searchtf = searchTf.getText();
		searchtf  = "%" + searchtf +  "%";
		Product pdt1 = new Product();
		pdt1.setPdtName(searchtf);
		
		List<Product> lists = pdtService.findByAllItem(pdt1);
		for(Product pdt : lists) {
			myList.add(pdt);
		}
		pdtTable.setItems(myList);
		
	}
	
	private void checkAlert(boolean isOk,String pwck) throws Exception {
		if(!isOk) {
			throw new Exception(pwck);
		}
	}
	
	@FXML
	public void getCellClassAction() {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/dialog/AddClassDialog.fxml"));
        BorderPane page;
		try {
			page = (BorderPane) loader.load();
			
			Stage dialogStage = new Stage();
	        dialogStage.setTitle("Product");
	        dialogStage.initModality(Modality.WINDOW_MODAL);		        
	        dialogStage.initOwner(pane.getScene().getWindow());
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
	        
//	        AddClassDialogController controller = loader.getController();
//	        controller.setDialogStage(dialogStage);
	        AddClassDialogController controller = loader.getController(); 
	        
	        dialogStage.showAndWait();
	        
	        
	        
	        if(controller.isOkClicked()) {
	        	 resetBigCb();
	        }

		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}
	
	@FXML
	public void SearchAccountAction() {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/dialog/AccountSearchDialog.fxml"));
        BorderPane page;
		try {
			page = (BorderPane) loader.load();
			
			Stage dialogStage = new Stage();
	        dialogStage.setTitle("Product");
	        dialogStage.initModality(Modality.WINDOW_MODAL);		        
	        dialogStage.initOwner(pane.getScene().getWindow());
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
	        
//	        ProductSearchAccountController controller = loader.getController();
//	        controller.setDialogStage(dialogStage);
//	        ProductSearchAccountController controller = loader.getController(); 
	        
	        dialogStage.showAndWait();
	        
	        
	        
//	        if(controller.isOkClicked()) {
//	        	 
//	        }

		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}
	
	
	@FXML
	public void getCellMenuAction() {
		Product pdt = pdtTable.getSelectionModel().getSelectedItem();
		
		try {
			checkAlert(pdt==null ? false : true,"수정할 열을 선택 해주세요.");
		}catch(Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			e.printStackTrace();
			e.printStackTrace();
			return ;
		}try {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/dialog/AdminInsertProduct.fxml"));
	        BorderPane page = (BorderPane) loader.load();

	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Product");
	        dialogStage.initModality(Modality.WINDOW_MODAL);		        
	        dialogStage.initOwner(pane.getScene().getWindow());
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
	        
	        ProductDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setLevellist(levellist);
	        controller.setProduct(pdt);
	
	        
	        dialogStage.showAndWait();

	        if(controller.isOkClicked()) {
	        	System.out.println(controller.getProduct());
	        	pdtService.updatePdt(controller.getProduct());
	        	refreshTable();
	        }
	   } catch (IOException e) {
	        e.printStackTrace();
	   }	
		
		
	}
	
	
	
	private void refreshTable() {
		pdtService = ProductService.getInstance();
		List<Product> lists = pdtService.findAll();
		for(Product pdt : lists) {
			myList.add(pdt);
		}
		pdtTable.setItems(myList);
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
	

	private Boolean tfComfrimField() {
		try {
			tfUtil.regexTfComfirmAccProductName(nameTf);
			tfUtil.cbComfrim(bigCb);
			tfUtil.cbComfrim(smallCb);
			tfUtil.cbComfrim(admitCb);
			tfUtil.regexTfComfirmNumber(priceTf);
			tfUtil.regexTfComfirmNumber(costTf);
			if(!tfUtil.regexTfComfirmCost(priceTf, costTf)) {
				return false;
			}
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
	
}
