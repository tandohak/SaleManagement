package kr.or.dgit.SaleManagement.controller;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kr.or.dgit.SaleManagement.MainApp;
import kr.or.dgit.SaleManagement.controller.dialogController.SalesSearchDialogController;
import kr.or.dgit.SaleManagement.dto.Account;
import kr.or.dgit.SaleManagement.dto.AccountLevel;
import kr.or.dgit.SaleManagement.dto.Product;
import kr.or.dgit.SaleManagement.dto.Record;
import kr.or.dgit.SaleManagement.dto.SalesLevel;
import kr.or.dgit.SaleManagement.service.AccountService;
import kr.or.dgit.SaleManagement.service.BigClassService;
import kr.or.dgit.SaleManagement.service.ProductService;
import kr.or.dgit.SaleManagement.service.RecordSerivce;
import kr.or.dgit.SaleManagement.service.SmallClassService;

public class StateController {
	@FXML private BorderPane pane;
	
	@FXML private TextField testTf;
	
	@FXML private TableView<Record> accTable;
	@FXML private TableColumn<Record, Integer> accNo;
	@FXML private TableColumn<Record, Integer> accCode;
	@FXML private TableColumn<Record, String> accName;
	@FXML private TableColumn<Record, Integer> accCount;
	@FXML private TableColumn<Record, Integer> accPrice;
	@FXML private TableColumn<Record, Integer> accCost;
	@FXML private TableColumn<Record, Integer> accDisprice;
	@FXML private TableColumn<Record, Integer> accMargin;
	@FXML private TableColumn<Record, Integer> accProfit;
	
	
	
	private ObservableList<Record> acclist = FXCollections.observableArrayList();
	private static ProductService pdtService;
	private static BigClassService bigService;
	private static SmallClassService smallService;
	private static AccountService accService;
	private static RecordSerivce recordService;
	
	@FXML
	private void initialize() {
		pdtService = ProductService.getInstance();
		accService = AccountService.getInstance();
		recordService = RecordSerivce.getInstance();
		
		List<Record> lists = recordService.findRecordByAll();
		Record record = new Record();
		
		for(Record r : lists) {
			Account acc = new Account();
			Product pdt = pdtService.findBypdtProduct(new Product(r.getrProductCode()));
			acc.setAccCode(pdt.getAccCode());
			
//			Account acc1 = new Account();
//			acc1 =	accService.findAccountByCode(acc);
//			System.out.println(acc1);
//			
//			r.setAccName(acc1.getAccName());
			
			
			acclist.add(r);
		}
		
		
		
		accDisprice.setCellValueFactory(cellData -> cellData.getValue().getRecDispriceProperty().asObject());
		accName.setCellValueFactory(cellData -> cellData.getValue().getAccNameProperty());
		accTable.setItems(acclist);
		
	}
	
	
	
	
	
	
	
	
	
	
	//사원검색
	@FXML
	public void SearchSalesAction() {
		System.out.println("사원검색");
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
	        
	        System.out.println("사원검색");
//	        if(controller.isOkClicked()) {
//	        	sales = controller.getItem();
//	        	saleTf.setText(sales.getSaleName());
//	        	saleLevelTf.setText(sales.getSaleLevel());
//	        	
//	        	SalesLevel saleDis = sLevelService.findOneSalesLevel(new SalesLevel(saleLevelTf.getText()));	        	
//	        	AccountLevel accDis = accLevelService.findOneAccount(new AccountLevel(accLevelTf.getText()));	        	
//	        	int sumDisrate = saleDis.getSalDisrate() + accDis.getAccDisrate(); 
//	        	disrateTf.setText(sumDisrate + "%");	
//	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//제품검색
	@FXML
	public void SearchProductAction() {
		System.out.println("제품별검색");
	}
	
	
	//거래처검색
	@FXML
	public void SearchAccountAction() {
		System.out.println("거래처별검색");
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/dialog/AccountSearchDialog.fxml"));
        BorderPane page;
		try {
			page = (BorderPane) loader.load();
			
			Stage dialogStage = new Stage();
	        dialogStage.setTitle("거래처별");
	        dialogStage.initModality(Modality.WINDOW_MODAL);		        
	        dialogStage.initOwner(pane.getScene().getWindow());
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
	        
	       
	        ProductSearchAccountController controller = loader.getController(); 
	        controller.setDialogStage(dialogStage);
	        
	        dialogStage.showAndWait();

	       
	        
//	        if(controller.isOkClicked()) {
//	        	accCodeTf.setText(controller.getGetAcc().getAccCode()+"");
//	        }

		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}
}
