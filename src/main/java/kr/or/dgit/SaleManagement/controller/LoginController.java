package kr.or.dgit.SaleManagement.controller;
 
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kr.or.dgit.SaleManagement.MainApp;
import kr.or.dgit.SaleManagement.dto.Account;
import kr.or.dgit.SaleManagement.dto.Sales;
import kr.or.dgit.SaleManagement.service.AccountService;
import kr.or.dgit.SaleManagement.service.SalesService;
 
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
	
	private static AccountService accService;
	private static SalesService salesService;
	
	@FXML
	private void initialize() {
		salesService = SalesService.getInstance();
		accService = AccountService.getInstance();
	}
	
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
		Sales sales = new Sales();
		sales.setSaleId(idTf.getText());
		sales.setSalePw(pwTf.getText());
		
		Account acc = new Account();
		acc.setAccId(idTf.getText());
		acc.setAccPw(pwTf.getText());
		Sales saleFind = salesService.findSalesByCode(sales);
		Account accFind = accService.findAccountById(acc);
		
		boolean checkId = false;
		
		if(saleFind != null) {
			checkId = true;
		}
		
		if(accFind != null) {
			checkId = true;
		}
		if(!checkId) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText("존재하지 않는 아이디 이거나 비밀번호가 틀립니다.");
			
			alert.showAndWait();
			return;
		}
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
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/dialog/AccountjoinUserDialog.fxml"));
			BorderPane pane = (BorderPane)loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.initModality(Modality.WINDOW_MODAL);
			Scene scene = new Scene(pane);
			dialogStage.setScene(scene);
 
			JoinUserController joinUserDialog = loader.getController();
			joinUserDialog.setDialogStage(dialogStage);
			joinUserDialog.setAccService(accService);
			joinUserDialog.setSalesService(salesService);
			dialogStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}