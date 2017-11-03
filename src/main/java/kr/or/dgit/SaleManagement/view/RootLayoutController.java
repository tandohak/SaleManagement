package kr.or.dgit.SaleManagement.view;


import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import kr.or.dgit.SaleManagement.MainApp;

public class RootLayoutController {
	@FXML
	private BorderPane rootPane;
	
	
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
			FXMLLoader loader = new FXMLLoader(getClass().getResource("InsertSellReport.fxml"));
			BorderPane pane = loader.load();
			rootPane.setCenter(pane);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
