package kr.or.dgit.SaleManagement.controller.dialogController;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kr.or.dgit.SaleManagement.MainApp;
import kr.or.dgit.SaleManagement.controller.ProductSearchAccountController;
import kr.or.dgit.SaleManagement.dto.Account;
import kr.or.dgit.SaleManagement.dto.AccountLevel;
import kr.or.dgit.SaleManagement.dto.Product;
import kr.or.dgit.SaleManagement.dto.Record;
import kr.or.dgit.SaleManagement.dto.Sales;
import kr.or.dgit.SaleManagement.dto.SalesLevel;
import kr.or.dgit.SaleManagement.service.AccountLevelService;
import kr.or.dgit.SaleManagement.service.AccountService;
import kr.or.dgit.SaleManagement.service.ProductService;
import kr.or.dgit.SaleManagement.service.SalesLevelService;
import kr.or.dgit.SaleManagement.service.SalesService;
import kr.or.dgit.SaleManagement.util.TextFieldUtil;

public class RecordEditDialogController {
	@FXML
    private BorderPane pane;

    @FXML private Button joinSalesBtn;
    @FXML private Button closeDialog;
    
    @FXML private DatePicker dateDP;
    @FXML private TextField accTf;
    @FXML private TextField accLevelTf;
    @FXML private TextField saleTf;
    @FXML private TextField saleLevelTf;
    @FXML private TextField pdtTf;
    @FXML private TextField pdtClassTf;
    @FXML private TextField priceTf;
    @FXML private TextField unitPriceTf;
    @FXML private TextField disPriceTf;	
    @FXML private TextField countTf;    
    @FXML private TextField disrateTf;
    
	private Stage dialogStage;
	private Record record;
	private boolean okClicked = false;
	private TextFieldUtil tfUtil = new TextFieldUtil();
	
	private SalesService saleService;
	private AccountService accService;
	private ProductService pdtService;
	private SalesLevelService sLevelService;
	private AccountLevelService accLevelService;
	
	private Account acc;
	private Product pdt;
	private Sales sales;
	
	@FXML
	private void initialize() {
		saleService = SalesService.getInstance();
		accService = AccountService.getInstance();
		pdtService = ProductService.getInstance();
		sLevelService = SalesLevelService.getInstance();
		accLevelService= AccountLevelService.getInstance();
	}

	public void setRecord(Record record) {
		this.record = record;
		
		dateDP.setValue(record.getRecDate());		
		sales = saleService.findSalesByCode(new Sales(record.getrSalecode()));
	    saleTf.setText(record.getSaleNamey());;
	    saleLevelTf.setText(sales.getSaleLevel());
	    pdt = pdtService.findBypdtProduct(new Product(record.getrProductCode()));
	    pdtTf.setText(record.getPdtName());
	    pdtClassTf.setText(pdt.getPdtClass()+"");
	    accTf.setText(record.getAccName());
	    acc = accService.findAccountByCode(new Account(pdt.getAccCode()));	   
	    accLevelTf.setText(acc.getAccLevel());
	    priceTf.setText(record.getRecPrice()+"");
	    
	    int dis =  record.getRecDisrate();
    	int unitDisPrice = (pdt.getPdtPrice()/100)*dis;
    	int unitPrice = pdt.getPdtPrice() - unitDisPrice;
    	unitPriceTf.setText(unitPrice+"");	
	    disPriceTf.setText(unitDisPrice+"");
	    
	    countTf.setText(record.getRecCount()+"");
	    disrateTf.setText(record.getRecDisrate()+"");	    
	}

	public Record getRecord() {
		return record;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}	
	
	public boolean isOkClicked() {
        return okClicked;
    }

	@FXML
    private void handleOk() {
        if (tfComfrimField()) {        	
			okClicked = true;		
			dialogStage.close();
        }
    }
	
	
	@FXML
	private void handleCancel() {
	      dialogStage.close();
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
	        
	       
	        ProductSearchAccountController controller = loader.getController(); 
	        controller.setDialogStage(dialogStage);	        
	        dialogStage.showAndWait();

	       
	        
	        if(controller.isOkClicked()) {
	        	acc = controller.getGetAcc();
	        	accTf.setText(acc.getAccName());
	        	accLevelTf.setText(acc.getAccLevel());
	        	
	        	SalesLevel saleDis = sLevelService.findOneSalesLevel(new SalesLevel(saleLevelTf.getText()));	        	
	        	AccountLevel accDis = accLevelService.findOneAccount(new AccountLevel(accLevelTf.getText()));	        	
	        	int sumDisrate = saleDis.getSalDisrate() + accDis.getAccDisrate(); 
	        	disrateTf.setText(sumDisrate + "%");
	        }

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@FXML
	public void SearchSalesAction() {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/dialog/SalesSearchDialog.fxml"));
        BorderPane page;
		try {
			page = (BorderPane) loader.load();
			
			Stage dialogStage = new Stage();
	        dialogStage.setTitle("Sales");
	        dialogStage.initModality(Modality.WINDOW_MODAL);		        
	        dialogStage.initOwner(pane.getScene().getWindow());
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
	        
	       
	        SalesSearchDialogController controller = loader.getController(); 
	        controller.setDialogStage(dialogStage);	 	        
	        dialogStage.showAndWait();

	       
	        
	        if(controller.isOkClicked()) {
	        	sales = controller.getItem();
	        	saleTf.setText(sales.getSaleName());
	        	saleLevelTf.setText(sales.getSaleLevel());
	        	
	        	SalesLevel saleDis = sLevelService.findOneSalesLevel(new SalesLevel(saleLevelTf.getText()));	        	
	        	AccountLevel accDis = accLevelService.findOneAccount(new AccountLevel(accLevelTf.getText()));	        	
	        	int sumDisrate = saleDis.getSalDisrate() + accDis.getAccDisrate(); 
	        	disrateTf.setText(sumDisrate + "%");	
	        }

		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}
	
	@FXML
	public void SearchProductAction() {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/dialog/ProductSearchDialog.fxml"));
        BorderPane page;
		try {
			page = (BorderPane) loader.load();
			
			Stage dialogStage = new Stage();
	        dialogStage.setTitle("Product");
	        dialogStage.initModality(Modality.WINDOW_MODAL);		        
	        dialogStage.initOwner(pane.getScene().getWindow());
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
	        
	        ProductSearchDialog controller = loader.getController(); 
	        controller.setDialogStage(dialogStage);	        
	        dialogStage.showAndWait();

	        if(controller.isOkClicked()) {
	        	pdt = controller.getItem();
	        	pdtTf.setText(pdt.getAccName());
	        	pdtClassTf.setText(pdt.getPdtName());
	        	priceTf.setText(pdt.getPdtPrice()+"");
	        	        	
//	        	int unitDisPrice = (pdt.getPdtPrice()/100)*dis;
//	        	int unitPrice = pdt.getPdtPrice() - unitDisPrice;
//	        	unitPriceTf.setText(unitPrice+"");	
//	    	    disPriceTf.setText(unitDisPrice+"");
	        }

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	private boolean tfComfrimField() {
		try {
			tfUtil.tfComfrim(countTf);			
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
