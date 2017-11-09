package kr.or.dgit.SaleManagement.controller;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import kr.or.dgit.SaleManagement.MainApp;

public class RootLayoutController {
	@FXML
	private BorderPane rootPane;
	
	private double dx;
	private double dy;
	final Delta dragDelta = new Delta();
	class Delta { double x, y; } 
	
	@FXML
	private void initialize() throws IOException {
		setWindowMove();
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("view/loginView.fxml"));
		AnchorPane pane = loader.load();
		
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
}
