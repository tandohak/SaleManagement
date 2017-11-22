package kr.or.dgit.SaleManagement.controller.dialogController;

import java.io.IOException;
import java.util.List;

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
import kr.or.dgit.SaleManagement.dto.Product;
import kr.or.dgit.SaleManagement.dto.Record;
import kr.or.dgit.SaleManagement.dto.Sales;
import kr.or.dgit.SaleManagement.service.AccountService;
import kr.or.dgit.SaleManagement.service.ProductService;
import kr.or.dgit.SaleManagement.service.RecordSerivce;
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
    @FXML private TextField costTf;
    @FXML private TextField disPriceTf;	
    @FXML private TextField countTf;    
    
	private Stage dialogStage;
	private Record record;
	private boolean okClicked = false;
	private TextFieldUtil tfUtil = new TextFieldUtil();
	
	private SalesService saleService;
	private AccountService accService;
	private ProductService pdtService;

	private Account acc;
	private Product pdt;
	private Sales sales;
	
	@FXML
	private void initialize() {
		saleService = SalesService.getInstance();
		accService = AccountService.getInstance();
		pdtService = ProductService.getInstance();
	}

	public void setRecord(Record record) {
		this.record = record;
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
	    costTf.setText(record.getRecCost()+"");
	    disPriceTf.setText(record.getRecPrice()+"");
	    countTf.setText(record.getRecCount()+"");
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
	        	System.out.println(acc);
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
	        	System.out.println(sales);
	        }

		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}
	
	
	private boolean tfComfrimField() {
		try {
			
			
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
