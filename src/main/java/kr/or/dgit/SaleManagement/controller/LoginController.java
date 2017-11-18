package kr.or.dgit.SaleManagement.controller;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
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
	private RootLayoutController rootLayoutController;
	
	@FXML
	private Button changView;

	@FXML
	private TextField idTf;

	@FXML
	private PasswordField pwTf;

	private MainApp mainApp;

	private static AccountService accService;
	private static SalesService salesService;
	private String loginId;

	@FXML
	private void initialize() {
		salesService = SalesService.getInstance();
		accService = AccountService.getInstance();
	}

	@FXML
	public void SaleTfTypeHandle(KeyEvent event) {
		if (idTf.getText().equals("") || pwTf.getText().equals("")) {
			changView.setDisable(true);
		} else {
			changView.setDisable(false);
		}
	}

	@FXML
	private void changeView() {
		Sales sales = new Sales();
		sales.setSaleId(idTf.getText().trim());
	
		Account acc = new Account();
		acc.setAccId(idTf.getText().trim());
		
		Sales saleFind = salesService.findSalesByCode(sales);
		Account accFind = accService.findAccountByCode(acc);
			
		boolean checkId = false;

		if (saleFind != null) {
			String findSalePw = saleFind.getSalePw().trim();
			if(findSalePw.equals(pwTf.getText().trim())) {
				checkId = true;
				loginId = saleFind.getSaleId();
			}			
		}

		if (accFind != null) {
			String findAccPw = accFind.getAccPw().trim();
			if(findAccPw.equals(pwTf.getText().trim())) {
				checkId = true;
				loginId = accFind.getAccId();
			}
		}
		
		if (!checkId) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText("존재하지 않는 아이디 이거나 비밀번호가 틀립니다.");

			alert.showAndWait();
			return;
		}
			
		if(saleFind != null) {
			rootLayoutController.changeSaleView(saleFind);
		}else {
			rootLayoutController.changeAccView(accFind);
		}
			
	}

	public FXMLLoader createFXMLLoader(URL location) {
		return new FXMLLoader(location, null, new JavaFXBuilderFactory(), null,
				Charset.forName(FXMLLoader.DEFAULT_CHARSET_NAME));
	}

	@FXML
	private void handleCloseBtn() {
		System.exit(0);
	}

	@FXML
	private void showJoinDialogAcc() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/dialog/AccountjoinUserDialog.fxml"));
			BorderPane pane = (BorderPane) loader.load();

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
	
	public void init(RootLayoutController rootLayoutController) {
		this.rootLayoutController = rootLayoutController;
	}

}