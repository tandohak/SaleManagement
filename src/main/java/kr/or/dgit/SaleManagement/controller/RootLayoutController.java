package kr.or.dgit.SaleManagement.controller;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import kr.or.dgit.SaleManagement.MainApp;
import kr.or.dgit.SaleManagement.dto.Account;
import kr.or.dgit.SaleManagement.dto.Sales;
import kr.or.dgit.SaleManagement.service.AccountService;
import kr.or.dgit.SaleManagement.service.ProductService;
import kr.or.dgit.SaleManagement.service.SalesService;

public class RootLayoutController {
	@FXML
	private BorderPane rootPane;
	
	@FXML
	private MainViewController mainViewController;
	
	@FXML
	private LoginController loginController;
	
	@FXML
	private Label nameLb;
	
	public Sales saleUser;
	private Account accUser;
	
	private double dx;
	private double dy;
	final Delta dragDelta = new Delta();
	class Delta { double x, y; } 
	
	@FXML
	private void initialize() throws IOException {
		setWindowMove();
		
		
		changeLoginView(false);
	}	

	public void changeLoginView(boolean sizeDown) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("view/loginView.fxml"));
		AnchorPane pane = loader.load();
		loginController = loader.getController();
		loginController.init(this);		
		if(sizeDown) {
		rootPane.getScene().getWindow().setWidth(500);
		rootPane.getScene().getWindow().setHeight(400);		
		}
		rootPane.setCenter(pane);
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
	
	public void setSaleUser(Sales saleUser) {
		this.saleUser = saleUser;
	}

	public void changeSaleView(Sales saleFind) {
		saleUser = saleFind;
		FXMLLoader loader = new FXMLLoader();
		BorderPane pane = null;
		try {
			if(saleUser.getSaleId().equals("admin")) {
				loader.setLocation(MainApp.class.getResource("view/AdimMainView.fxml"));
				
				pane = loader.load();	
			}else {				
				loader.setLocation(MainApp.class.getResource("view/SalesMainView.fxml"));
				
				pane = loader.load();	
			}
			
			mainViewController = loader.getController();
			mainViewController.init(this);
			rootPane.getScene().getWindow().setWidth(1080);
			rootPane.getScene().getWindow().setHeight(675);
			rootPane.setCenter(pane);
			mainViewController.setSaleUser(saleUser);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void changeAccView(Account accUser) {
		this.accUser = accUser;
		try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MainApp.class.getResource("view/AccountMainView.fxml"));
				
				BorderPane pane = loader.load();	
				
				mainViewController = loader.getController();
				mainViewController.init(this);
				rootPane.getScene().getWindow().setWidth(1080);
				rootPane.getScene().getWindow().setHeight(675);
				rootPane.setCenter(pane);
				mainViewController.setAccUser(accUser);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
