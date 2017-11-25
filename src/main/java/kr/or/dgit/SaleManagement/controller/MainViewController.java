package kr.or.dgit.SaleManagement.controller;


import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kr.or.dgit.SaleManagement.MainApp;
import kr.or.dgit.SaleManagement.controller.dialogController.AccountEditDialogController;
import kr.or.dgit.SaleManagement.controller.dialogController.SalesEditDialogController;
import kr.or.dgit.SaleManagement.dto.Account;
import kr.or.dgit.SaleManagement.dto.Sales;
import kr.or.dgit.SaleManagement.service.AccountService;
import kr.or.dgit.SaleManagement.service.SalesService;

public class MainViewController  {
	@FXML
	private BorderPane admNode;
	
	@FXML
	private Button changeViewBtn;
	
	@FXML
	private Label nameLb;
	
	@FXML
	private Label myPage;
	
	private RootLayoutController rootLayoutController;
	
	private Sales saleUser;
	
	private Account accUser;
	
	private MainApp mainApp;
	
	private String userName;

	private SalesService saleSerivce;

	private AccountService accountService;
	
	
	@FXML
	private void initialize() {	
		saleSerivce =SalesService.getInstance();
		accountService = AccountService.getInstance();
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML
	private void editSaleUserInfoAction() {
		try {
		        FXMLLoader loader = new FXMLLoader();
		        loader.setLocation(MainApp.class.getResource("view/dialog/SalesEditDialog.fxml"));
		        BorderPane page = (BorderPane) loader.load();

		        Stage dialogStage = new Stage();
		        dialogStage.setTitle(null);
		        dialogStage.initModality(Modality.WINDOW_MODAL);		        
		        dialogStage.initOwner(admNode.getScene().getWindow());
		        Scene scene = new Scene(page);
		        dialogStage.setScene(scene);
		        
		        SalesEditDialogController controller = loader.getController();
		        controller.setDialogStage(dialogStage);
		        controller.setSales(saleUser);
		        controller.changeHeader();
		        dialogStage.showAndWait();

		        if(controller.isOkClicked()) {
		        	saleSerivce.updateSales(controller.getSales());
		        }
		   } catch (IOException e) {
		        e.printStackTrace();
		   }
	}
	
	@FXML
	private void editAccUserInfoAction() {
		try {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/dialog/AccountEditDialog.fxml"));
	        BorderPane page = (BorderPane) loader.load();
	        Stage dialogStage = new Stage();
	        
	        dialogStage.setTitle(null);
	        dialogStage.initModality(Modality.WINDOW_MODAL);		        
	        dialogStage.initOwner(admNode.getScene().getWindow());
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
	        
	        AccountEditDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setAccount(accUser);
	        controller.changeHeader();
	        dialogStage.showAndWait();
	        
	        if(controller.isOkClicked()) {
	        	System.out.println(controller.getAccount());
	        	accountService.updateAccount(controller.getAccount());
	        }
	   } catch (IOException e) {
	        e.printStackTrace();
	   }
	}
	
	@FXML
	private void hadleShowSalesManager() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/management/SalesManagement.fxml"));
			BorderPane pane = (BorderPane)loader.load();
			admNode.setCenter(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void hadleShowProductManager() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/management/ProductManager.fxml"));
			BorderPane pane = (BorderPane)loader.load();
			ProductController controller = loader.getController();
			if(accUser != null) {
				controller.setUserAccSetting(accUser);
			}
			
			admNode.setCenter(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void hadleShowInsertSell() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/management/InsertRecordManagement.fxml"));
			BorderPane pane = (BorderPane)loader.load();
			admNode.setCenter(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void hadleShowAccountManager() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/management/AccountManagement.fxml"));
			BorderPane pane = (BorderPane)loader.load();

			admNode.setCenter(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void setSaleUser(Sales user) {
		this.saleUser = user;		
		if(saleUser.getSaleId().equals("admin")) {
			myPage.setVisible(false);
		}
		nameLb.setText(saleUser.getSaleName());
	}
	
	public void setAccUser(Account user) {
		this.accUser = user;
		nameLb.setText(accUser.getAccName());
	}
	
	@FXML
	private void hadleShowSellManager() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/management/RecordManagement.fxml"));
			BorderPane pane = (BorderPane)loader.load();
			RecordController controller = loader.getController();
			if(accUser != null) {
				controller.setUserAccSetting(accUser);
			}
			
			admNode.setCenter(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleCloseBtn() {
		System.exit(0);
	}
	
	@FXML
	private void handleMinimaize() {
		
	}
	
	
	@FXML
	private void handleLogoutBtn() throws IOException {
		rootLayoutController.changeLoginView(true);
	}
	

	public void init(RootLayoutController rootLayoutController) {
		this.rootLayoutController = rootLayoutController;
	}
}
