package kr.or.dgit.SaleManagement.view;


import java.awt.event.MouseEvent;
import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import kr.or.dgit.SaleManagement.MainApp;

public class RootLayoutController {
	@FXML
	private BorderPane rootPane;
	@FXML
	private Pane dragPane;
	
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
	private void handleMoveWindow() {
		
	}
	
	
	@FXML
	private void handleShowProductManager() {
		
	}
	
	@FXML
	private void hadleShowSalesManager() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("salesManagementView.fxml"));
			AnchorPane pane = loader.load();
			rootPane.setCenter(pane);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
