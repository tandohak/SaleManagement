package kr.or.dgit.SaleManagement.controller;


import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import kr.or.dgit.SaleManagement.MainApp;
import kr.or.dgit.SaleManagement.dto.Account;
import kr.or.dgit.SaleManagement.dto.Sales;
import kr.or.dgit.SaleManagement.service.ProductService;

public class MainViewController  {
	@FXML
	private BorderPane admNode;
	
	@FXML
	private Button changeViewBtn;
	
	@FXML
	private Label nameLb;
	
	private RootLayoutController rootLayoutController;
	
	private Sales saleUser;
	
	private Account accUser;
	
	private MainApp mainApp;
	
	private String userName;
	
	
	@FXML
	private void initialize() {
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
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
		System.out.println(saleUser);
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
	
	
	class Delta { double x, y; }


	public void init(RootLayoutController rootLayoutController) {
		this.rootLayoutController = rootLayoutController;
	}
}
