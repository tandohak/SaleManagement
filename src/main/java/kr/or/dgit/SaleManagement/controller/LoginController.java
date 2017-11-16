package kr.or.dgit.SaleManagement.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kr.or.dgit.SaleManagement.MainApp;

public class LoginController {
	@FXML
	private BorderPane Node;
	
	@FXML
	private Button changView;
	
	@FXML
	private TextField idTf;
	
	@FXML
	private PasswordField pwTf;
	
	private MainApp mainApp;
	
	@FXML
	private void initialize() {}
	
	@FXML
	public void SaleTfTypeHandle(KeyEvent event) {
		if(idTf.getText().equals("") || pwTf.getText().equals("")) {
			changView.setDisable(true);
		}else {
			changView.setDisable(false);
		}				
	}
	
	
	@FXML
	private void changeView() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AdimMainView.fxml"));
			BorderPane pane = (BorderPane)loader.load();

			changView.getScene().getWindow().setWidth(1080);
			changView.getScene().getWindow().setHeight(675);
			((BorderPane)changView.getScene().getRoot()).setCenter(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleCloseBtn() {
		System.exit(0);
	}
	
	@FXML
	private void showJoinDialogAcc() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/dialog/JoinUserAccDialog.fxml"));
			BorderPane pane = (BorderPane)loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.initModality(Modality.WINDOW_MODAL);
			Scene scene = new Scene(pane);
			dialogStage.setScene(scene);

			JoinUserDialog joinUserDialog = loader.getController();
			joinUserDialog.setDialogStage(dialogStage);
			dialogStage.showAndWait();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void showJoinDialogSales() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/dialog/JoinUserSalesDialog.fxml"));
			BorderPane pane = (BorderPane)loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.initModality(Modality.WINDOW_MODAL);
			Scene scene = new Scene(pane);
			dialogStage.setScene(scene);
			
			JoinUserDialog joinUserDialog = loader.getController();
			joinUserDialog.setDialogStage(dialogStage);
			
			dialogStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
