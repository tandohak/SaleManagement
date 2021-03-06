package kr.or.dgit.SaleManagement.controller;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kr.or.dgit.SaleManagement.MainApp;
import kr.or.dgit.SaleManagement.controller.dialogController.PasswordChangeDialogController;
import kr.or.dgit.SaleManagement.dto.Account;
import kr.or.dgit.SaleManagement.dto.Sales;
import kr.or.dgit.SaleManagement.jdbc_setting.jdbcSetting;
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
	@FXML private ImageView bgImgview;
	private MainApp mainApp;

	private static AccountService accService;
	private static SalesService salesService;
	private String loginId;
	private String path = System.getProperty("user.dir");
	
	@FXML
	private void initialize() {
		salesService = SalesService.getInstance();
		accService = AccountService.getInstance();
		
		InputStream is;
		try {
			is = new FileInputStream(new File(path+"/DataFile/loginBg_2.jpg"));
			bgImgview.setImage(new Image(is));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
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
		
		
		
		
		if ((saleFind != null)) {
			String findSalePw = saleFind.getSalePw().trim();
			boolean saleLeaveCk = saleFind.getSaleLeave().equals("true");
			if(findSalePw.equals(pwTf.getText().trim()) && saleLeaveCk) {
				checkId = true;
				loginId = saleFind.getSaleId();
			}			
		}

		if ((accFind != null)) {
			String findAccPw = accFind.getAccPw().trim();
			boolean accAdmitCk = accFind.getAccAdmit().equals("true");
			if(!accAdmitCk) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle(null);
				alert.setHeaderText(null);
				alert.setContentText("허가받은 아이디가 아닙니다. 관리자에게 문의하세요.");

				alert.showAndWait();
				return;
			}
			
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
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/dialog/AccountjoinUserDialog.fxml"));
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
			
			if(joinUserDialog.isOkJoin()) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setContentText("회원 가입이 완료 되었습니다.");
				alert.showAndWait();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 비밀번호변경 다이얼로그 연결
	@FXML
	private void showPasswordChangeDialog() {
		try {
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/dialog/PasswordChangeDialog.fxml"));
			BorderPane pane = (BorderPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.initModality(Modality.WINDOW_MODAL);
			Scene scene = new Scene(pane);
			dialogStage.setScene(scene);
			
			PasswordChangeDialogController ChangeDialog = loader.getController();
			ChangeDialog.setDialogStage(dialogStage);
			dialogStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void showJDBCSetting() {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					jdbcSetting frame = new jdbcSetting();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void init(RootLayoutController rootLayoutController) {
		this.rootLayoutController = rootLayoutController;
	}

}