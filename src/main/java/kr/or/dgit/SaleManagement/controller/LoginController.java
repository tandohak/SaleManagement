package kr.or.dgit.SaleManagement.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kr.or.dgit.SaleManagement.MainApp;

public class LoginController {
	@FXML
	private BorderPane rootPane;
	
	@FXML
	private Button changViewSale;
	
	@FXML
	private Button changeViewAcc;
	
	@FXML
	private TextField saleIdTf;
	
	@FXML
	private PasswordField salePwTf;
	
	@FXML
	private TextField accIdTf;
	
	@FXML
	private PasswordField accPwTf;
	
	private MainApp mainApp;
	
	@FXML
	private void initialize() {
	}
	
	
	@FXML
	public void SaleTfTypeHandle(KeyEvent event) {
		if(saleIdTf.getText().equals("") || salePwTf.getText().equals("")) {
			changViewSale.setDisable(true);
		}else {
			changViewSale.setDisable(false);
		}				
	}
	
	@FXML
	public void AccTfTypeHandle(KeyEvent event) {
		if(accIdTf.getText().equals("") || accPwTf.getText().equals("")) {
			changeViewAcc.setDisable(true);
		}else {
			changeViewAcc.setDisable(false);
		}				
	}
	
	@FXML
	private void changeViewSale() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/RootLayout.fxml"));
			Parent pane = (BorderPane)loader.load();
			((Stage)changViewSale.getScene().getWindow()).setScene(new Scene(pane, 1080, 675));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleCloseBtn() {
		System.exit(0);
	}
	
	@FXML
	private void changeViewAcc() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/RootLayout.fxml"));
			Parent pane = (BorderPane)loader.load();
			((Stage)changeViewAcc.getScene().getWindow()).setScene(new Scene(pane, 1080, 675));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void showJoinDialog() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/JoinUserAcc.fxml"));
			BorderPane pane = (BorderPane)loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.initModality(Modality.WINDOW_MODAL);
			Scene scene = new Scene(pane);
			dialogStage.setScene(scene);
			
			dialogStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
