package kr.or.dgit.SaleManagement.controller;
 
 
import java.io.IOException;
 
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import kr.or.dgit.SaleManagement.MainApp;
 
public class MainViewController {
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
	
	@FXML
	private void hadleShowSalesManager() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/management/SalesManagement.fxml"));
			BorderPane pane = (BorderPane)loader.load();
			rootPane.setCenter(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void hadleShowProductManager() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/management/ProductManagementView.fxml"));
			BorderPane pane = (BorderPane)loader.load();
			rootPane.setCenter(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void hadleShowInsertSell() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ProductManager.fxml"));
			BorderPane pane = (BorderPane)loader.load();
			rootPane.setCenter(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void hadleShowAccountManager() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/management/AccountManagement.fxml"));
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
//			((Stage)changeViewBtn.getScene().getWindow()).setScene(new Scene(pane, 500, 400));
			changeViewBtn.getScene().getWindow().setWidth(500);
			changeViewBtn.getScene().getWindow().setHeight(400);
			((BorderPane)changeViewBtn.getScene().getRoot()).setCenter(pane);;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	class Delta { double x, y; } 
}