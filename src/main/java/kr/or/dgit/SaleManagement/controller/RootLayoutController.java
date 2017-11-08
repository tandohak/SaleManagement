package kr.or.dgit.SaleManagement.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class RootLayoutController {
	@FXML
	private Parent rootPane;
	
	private double dx;
	private double dy;
	final Delta dragDelta = new Delta();
	class Delta { double x, y; } 
	
	@FXML
	private void initialize() {
		setWindowMove();
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
