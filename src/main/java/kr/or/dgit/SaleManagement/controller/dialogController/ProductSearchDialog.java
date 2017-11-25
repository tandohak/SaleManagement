package kr.or.dgit.SaleManagement.controller.dialogController;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import kr.or.dgit.SaleManagement.dto.Account;
import kr.or.dgit.SaleManagement.dto.Product;
import kr.or.dgit.SaleManagement.dto.Sales;
import kr.or.dgit.SaleManagement.service.AccountService;
import kr.or.dgit.SaleManagement.service.BigClassService;
import kr.or.dgit.SaleManagement.service.ProductService;
import kr.or.dgit.SaleManagement.service.SmallClassService;

public class ProductSearchDialog {
	@FXML private BorderPane pane;
	@FXML
	private TextField searchTf;
	
	@FXML
	private Button searchBtn;
	
	@FXML
	private TextField nameTf;

	@FXML private TableView<Product> pdtTable;
	@FXML private TableColumn<Product, Integer> codeTc;	
	@FXML private TableColumn<Product, String> nameTc;	
	@FXML private TableColumn<Product, String> accTc;	
	@FXML private TableColumn<Product, Integer> costTc;
	@FXML private TableColumn<Product, Integer> priceTc;
	@FXML private TableColumn<Product, String> admitTc;
	
	private Stage dialogStage;
	private Product getItem;
	private boolean okClicked = false;
	
	private ObservableList<Product> levellist = FXCollections.observableArrayList();
	private static ProductService pdtService;
	private static BigClassService bigService;
	private static SmallClassService smallService;
	private static AccountService accService;
	
	private ObservableList<Product> myList = FXCollections.observableArrayList();
	private Account acc;
	public boolean isOkClicked() {
		 return okClicked;
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public Product getItem() {
		return getItem;
	}

	public void setItem(Product getItem) {
		this.getItem = getItem;
	}
	
	@FXML
	private void initialize() {
		
		pdtService = ProductService.getInstance();
		accService = AccountService.getInstance();
		
		codeTc.setCellValueFactory(cellData -> cellData.getValue().getPdtCodeProperty().asObject());
		nameTc.setCellValueFactory(cellData -> cellData.getValue().getPdtNameProperty());
		accTc.setCellValueFactory(cellData -> cellData.getValue().getAccNameProperty());
		costTc.setCellValueFactory(cellData -> cellData.getValue().getPdtCostProperty().asObject());
		priceTc.setCellValueFactory(cellData -> cellData.getValue().getPdtPriceProperty().asObject());
		admitTc.setCellValueFactory(cellData -> cellData.getValue().getPdtAdmitProperty());
	}
	
	
	@FXML
	public void SearchAction() {

		myList = FXCollections.observableArrayList();
		
		String searchtf = searchTf.getText();
		searchtf  = "%" + searchtf +  "%";
		Product pdt1 = new Product();
		pdt1.setPdtName(searchtf);
		pdt1.setAccCode(acc.getAccCode());
		pdtService = ProductService.getInstance();
		accService = AccountService.getInstance();
		
		Account findAcc = new Account();
		Account resAcc = new Account();
		
		List<Product> lists = pdtService.findByAllItem(pdt1);
		for(Product pdt : lists) {
			findAcc.setAccCode(pdt.getAccCode());
			resAcc = accService.findAccountByCode(findAcc);
			pdt.setAccName(resAcc.getAccName());
			myList.add(pdt);
		}
		pdtTable.setItems(myList);		
	}
	
	@FXML
	private void handleOk() {
		Product selectItem = pdtTable.getSelectionModel().getSelectedItem();
		getItem = selectItem;

		try {
			checkAlert(selectItem==null ? false : true,"사원을 선택 해주세요.");
		}catch(Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			return ;			
		}		
		okClicked = true;
		
		dialogStage.close();	
	}
	
	private void checkAlert(boolean isOk,String pwck) throws Exception {
		if(!isOk) {
			throw new Exception(pwck);
		}
	}

	@FXML
	private void handleCancel() {
	      dialogStage.close();
	}

	public void setAccounnt(Account acc) {
		this.acc = acc;
		
		Product pdt1 = new Product();
		pdt1.setAccCode(acc.getAccCode());
		
		Account findAcc = new Account();
		Account resAcc = new Account();
		List<Product> lists = pdtService.findByAllItem(pdt1);
		for(Product pdt : lists) {
			findAcc.setAccCode(pdt.getAccCode());
			resAcc = accService.findAccountByCode(findAcc);
			pdt.setAccName(resAcc.getAccName());
			myList.add(pdt);
		}
		
		pdtTable.setItems(myList);
	}
}
