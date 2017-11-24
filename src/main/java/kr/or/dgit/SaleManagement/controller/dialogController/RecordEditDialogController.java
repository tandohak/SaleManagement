package kr.or.dgit.SaleManagement.controller.dialogController;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
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
import kr.or.dgit.SaleManagement.dto.SmallClass;
import kr.or.dgit.SaleManagement.service.AccountLevelService;
import kr.or.dgit.SaleManagement.service.AccountService;
import kr.or.dgit.SaleManagement.service.ProductService;
import kr.or.dgit.SaleManagement.service.SalesLevelService;
import kr.or.dgit.SaleManagement.service.SalesService;
import kr.or.dgit.SaleManagement.service.SmallClassService;
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
    @FXML private TextField sumPriceTf;
    
	private Stage dialogStage;
	private Record record;
	private boolean okClicked = false;
	private TextFieldUtil tfUtil = new TextFieldUtil();
	
	private SalesService saleService;
	private AccountService accService;
	private ProductService pdtService;
	private SalesLevelService sLevelService;
	private AccountLevelService accLevelService;
	SmallClassService sClassService;
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
		sClassService = SmallClassService.getInstance();
		
		dateDP.valueProperty().addListener(new ChangeListener<LocalDate>() {

			@Override
			public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue,
					LocalDate newValue) {
				if(record.getRecDate().isAfter(newValue)) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle(null);
					alert.setHeaderText(null);
					alert.setContentText("이전 날짜를 선택 하였습니다.");
					alert.showAndWait();
					newValue = oldValue;
				}
			}
		});
		
		
	}

	public void setRecord(Record record) {
		this.record = record;
		
		dateDP.setValue(record.getRecDate());		
		sales = saleService.findSalesByCode(new Sales(record.getrSalecode()));
	    saleTf.setText(record.getSaleNamey());;
	    saleLevelTf.setText(sales.getSaleLevel());
	    pdt = pdtService.findBypdtProduct(new Product(record.getrProductCode()));
	    pdtTf.setText(record.getPdtName());
	    SmallClass sClass = sClassService.findBySmallClass(new SmallClass(pdt.getPdtClass()));
	    pdtClassTf.setText(sClass.getSmallName());
	    accTf.setText(record.getAccName());
	    acc = accService.findAccountByCode(new Account(pdt.getAccCode()));	   
	    accLevelTf.setText(acc.getAccLevel());
	    priceTf.setText(pdt.getPdtPrice()+"");
	    
	    int dis =  record.getRecDisrate();
    	int unitDisPrice = (pdt.getPdtPrice()/100)*dis;
    	int unitPrice = pdt.getPdtPrice() - unitDisPrice;
    	unitPriceTf.setText(unitPrice+"");	
	    disPriceTf.setText(unitDisPrice+"");
	    
	    countTf.setText(record.getRecCount()+"");
	    disrateTf.setText(record.getRecDisrate()+"");
	    
	    if(!countTf.getText().equals("")) {
		    int count = record.getRecCount();
		    int sumPrice = unitPrice*count;
		    sumPriceTf.setText(sumPrice+"");
	    }else {
	    	int sumPrice = unitPrice*1;
			sumPriceTf.setText(sumPrice+"");
	    }
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
			record.setRecDate(dateDP.getValue());
			record.setAccName(acc.getAccName());
			record.setPdtName(pdt.getPdtName());
			record.setrProductCode(pdt.getPdtCode());
			record.setSumPrice(Integer.parseInt(sumPriceTf.getText()));
			record.setrSalecode(sales.getSaleCode());
			record.setSaleName(sales.getSaleName());
			record.setRecDisprice(Integer.parseInt(disPriceTf.getText()));
			record.setRecDisrate(Integer.parseInt(disrateTf.getText().replaceAll("%", "")));
			record.setRecCount(Integer.parseInt(countTf.getText()));
			dialogStage.close();
        }
    }
	
	
	
	@FXML
	private void searchAccountAction() {
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

	    	    pdtTf.setText("");
	    	    pdtClassTf.setText("");	    	    
	    	    unitPriceTf.setText("");
	    	    priceTf.setText("");
	    	    disPriceTf.setText("");
	    	    sumPriceTf.setText("");
	    	    countTf.setText("1");
	        }

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@FXML
	private void handleCancel() {
	      dialogStage.close();
	}
	
	@FXML
	private void changCountNumAction(KeyEvent event) {	
		if(countTf.getText().equals("")){
			
		}
		int count = Integer.parseInt(countTf.getText());
		int unitPrice = Integer.parseInt(unitPriceTf.getText());
	    int sumPrice = unitPrice*count;
	    sumPriceTf.setText(sumPrice+"");
	}
	
	@FXML
	private void searchSalesAction() {
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
	        	
	        	int sumDisrate = 0;
	        	
	        	if(accLevelTf.getText().equals("")){
	        		disrateTf.setText(saleDis.getSalDisrate() + "%");
	        	}else{
	        		sumDisrate = saleDis.getSalDisrate() + accDis.getAccDisrate(); 
		        	disrateTf.setText(sumDisrate + "%");	
	        	}
	        	
	        	
	        	if(!pdtTf.getText().equals("")) {      			        	
		        	int dis = sumDisrate;
		        	int unitDisPrice = (pdt.getPdtPrice()/100)*dis;
		        	int unitPrice = pdt.getPdtPrice() - unitDisPrice;
		        	unitPriceTf.setText(unitPrice+"");	
		    	    disPriceTf.setText(unitDisPrice+"");
	        	
	    	    refrashSumPrice(unitPrice);
	        	}
	        }

		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}
	
	@FXML
	private void searchProductAction() {
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
	        System.out.println(acc);
	        ProductSearchDialog controller = loader.getController(); 
	        controller.setAccounnt(acc);
	        controller.setDialogStage(dialogStage);	      
	   
	        dialogStage.showAndWait();

	        if(controller.isOkClicked()) {
	        	pdt = controller.getItem();
	        	
	        	pdtTf.setText(pdt.getPdtName());
	        	SmallClass sClass = sClassService.findBySmallClass(new SmallClass(pdt.getPdtClass()));
	        	pdtClassTf.setText(sClass.getSmallName());
	        	priceTf.setText(pdt.getPdtPrice()+"");
	        	
	        	int dis = Integer.parseInt(disrateTf.getText().replace("%", ""));
	        	int unitDisPrice = (pdt.getPdtPrice()/100)*dis;
	        	int unitPrice = pdt.getPdtPrice() - unitDisPrice;
	        	unitPriceTf.setText(unitPrice+"");	
	    	    disPriceTf.setText(unitDisPrice+"");
	    	    
	    	    refrashSumPrice(unitPrice);
	        }

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void refrashSumPrice(int unitPrice) {
		if(!countTf.getText().equals("")) {
			int count = Integer.parseInt(countTf.getText().replace("%", ""));
			int sumPrice = unitPrice*count;
			sumPriceTf.setText(sumPrice+"");
		}else {
			int sumPrice = unitPrice*1;
			sumPriceTf.setText(sumPrice+"");
		}
	}
	
	
	private boolean tfComfrimField() {
		try {	
			tfUtil.tfComfrim(pdtClassTf);
			tfUtil.tfComfrim(pdtTf);
			tfUtil.tfComfrim(countTf);			
			tfUtil.regexTfComfirmNumber(countTf);
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
