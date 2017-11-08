package kr.or.dgit.SaleManagement.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class WindowController {
	@FXML
	private Parent rootPane;
	private EventHandler<MouseEvent> mousePress;
	private EventHandler<MouseEvent> mouseDragged;
	private double dx;
	private double dy;
	
	class Delta { double x, y; } 
	
	
	public WindowController(Parent rootPane) {
		this.rootPane = rootPane;
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
	

	final Delta dragDelta = new Delta();
}
