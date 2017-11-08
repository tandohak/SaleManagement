package kr.or.dgit.SaleManagement.controller;


import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import kr.or.dgit.SaleManagement.MainApp;

public class MainViewController {
	@FXML
	private BorderPane rootPane;
	
	@FXML
	private Button changeViewBtn;
	
	private MainApp mainApp;
	private double dx;
	private double dy;
	
	final Delta dragDelta = new Delta();
	
	
	@FXML
	private void initialize() {
		setWindowMove();
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML
	private void hadleShowSalesManager() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/SalesManagementView.fxml"));
			BorderPane pane = (BorderPane)loader.load();
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
	
	private void setWindowMove() {
		rootPane.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				dragDelta.x = rootPane.getScene().getWindow().getX() - event.getScreenX() ;
				dragDelta.y = rootPane.getScene().getWindow().getY() - event.getScreenY() ;
			}
		});
		
		rootPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {	
				if(event.getButton() == MouseButton.PRIMARY) {
					rootPane.getScene().getWindow().setX(event.getScreenX() + dragDelta.x);
					rootPane.getScene().getWindow().setY(event.getScreenY() + dragDelta.y);
				}
			}
		});
	}	
	
	
	class Delta { double x, y; } 
}
