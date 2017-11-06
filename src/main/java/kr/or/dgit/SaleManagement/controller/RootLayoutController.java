package kr.or.dgit.SaleManagement.controller;


import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import kr.or.dgit.SaleManagement.MainApp;

public class RootLayoutController {
	@FXML
	private BorderPane rootPane;
	
	@FXML
	private Button changeViewBtn;
	
	private MainApp mainApp;
	
	@FXML
	private void initialize() {
		
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	public void setRootLayout() {
		
	}
	
	@FXML
	private void hadleShowSalesManager() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/SalesManagementView.fxml"));
			BorderPane pane = (BorderPane)loader.load();
			rootPane = new BorderPane();
			rootPane.setCenter(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void hadleShowProductManager() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ProductManagementView.fxml"));
			BorderPane pane = (BorderPane)loader.load();
			rootPane.setCenter(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void hadleShowInsertSell() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/InsertSell.fxml"));
			BorderPane pane = (BorderPane)loader.load();
			rootPane.setCenter(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void hadleShowAccountManager() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AccountManagementView.fxml"));
			BorderPane pane = (BorderPane)loader.load();
			
			rootPane.setCenter(pane);
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
	private void handleLogoutBtn() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/loginView.fxml"));
			Parent pane = (AnchorPane)loader.load();
			((Stage)changeViewBtn.getScene().getWindow()).setScene(new Scene(pane, 500, 400));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleLoginBtn() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/RootLayout.fxml"));
			Parent pane = (BorderPane)loader.load();
			((Stage)changeViewBtn.getScene().getWindow()).setScene(new Scene(pane, 1080, 675));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
