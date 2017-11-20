package kr.or.dgit.SaleManagement.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kr.or.dgit.SaleManagement.MainApp;
import kr.or.dgit.SaleManagement.controller.dialogController.AddrDialogController;
import kr.or.dgit.SaleManagement.dto.Account;
import kr.or.dgit.SaleManagement.service.AccountService;
import kr.or.dgit.SaleManagement.service.SalesService;
import kr.or.dgit.SaleManagement.util.TextFieldUtil;

public class JoinUserController{
	@FXML
	private Parent pane;
	//이미지 관련 필드
	
	private TextField imgNameTf;
	
	private Button userImgBtn;
	
	private ImageView userImg;
	
	//텍스트 필드	
	@FXML
	private TextField idTf;

	@FXML
	private PasswordField pwTf;

	@FXML
	private PasswordField pwTfComf;

	@FXML
	private TextField nameTf;

	@FXML
	private TextField phoneTf;
	
	@FXML
	private ImageView checkPwIcon;
	
	@FXML
	private ImageView checkIdIcon;
	
	@FXML
	private TextField addrZipTf; 
	
	@FXML
	private TextField addrTf; 
	
	@FXML
	private Button idCheckBtn;
	
	private Stage dialogStage;
	
	private boolean idCheckOk = false;
	private boolean pwCheckOk = false;
	private AccountService accService;
	private SalesService salesService;
	
	@FXML
	private void initialize() {
	}
		
	public AccountService getAccService() {
		return accService;
	}

	public void setAccService(AccountService accService) {
		this.accService = accService;
	}

	public SalesService getSalesService() {
		return salesService;
	}

	public void setSalesService(SalesService salesService) {
		this.salesService = salesService;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	

	TextFieldUtil util= new TextFieldUtil();

	@FXML
	private void openDialogFileChooser() {
		FileChooser fileChooser = new FileChooser();

		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("jpg,png,gif files ", "*.jpg", "*.png",
				"*.gif");

		fileChooser.getExtensionFilters().add(extFilter);

		File file = fileChooser.showOpenDialog(pane.getScene().getWindow());

		if (file != null) {
			try {
				InputStream is = new FileInputStream(file);
				userImg.setImage(new Image(is));
				imgNameTf.setText(file.getName());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	@FXML
	private void searchAddrAction() {
		try {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/dialog/AddrZipSearchDialog.fxml"));
	        BorderPane page = (BorderPane) loader.load();

	        Stage dialogStage = new Stage();
	        dialogStage.setTitle(null);
	        dialogStage.initModality(Modality.WINDOW_MODAL);		        
	        dialogStage.initOwner(pane.getScene().getWindow());
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
	        
	        AddrDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	     
	        
	        dialogStage.showAndWait();

//	        if(controller.isOkClicked()) {
//	        	
//	        	
//	        }
	   } catch (IOException e) {
	        e.printStackTrace();
	   }
	}
	
	@FXML
	private void idTypeCheck(KeyEvent event) {
		idCheckOk = false;
		checkIdIcon.setVisible(false);
		
	}
	

	@FXML
	private void idTypeHandler() {
		String path = System.getProperty("user.dir");
		
		try {
			util.regexTfComfirmId(idTf);
		} catch (Exception e) {		
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			e.printStackTrace();
			return;
		}
		
		idCheckOk = util.idOverlapCheck(idTf);
		
		if(idCheckOk) {
			File file = new File(path + "/DataFile/ic_check_circle_black_48dp_1x.png" );
			Image image = new Image(file.toURI().toString());
			checkIdIcon.setImage(image);
			checkIdIcon.setVisible(true);
		}else {
			checkIdIcon.setVisible(false);
		}	
	}
	
	@FXML
	private void pwTypeHandler(KeyEvent event) {
		String path = System.getProperty("user.dir");
		String pwVal =pwTf.getText();
		String pwComVal = pwTfComf.getText();
	
		if(pwVal.equals("") || pwComVal.equals("")) {
			checkPwIcon.setVisible(false);
		}else if(pwVal.equals(pwComVal)) {
			File file = new File(path + "/DataFile/ic_check_circle_black_48dp_1x.png" );
			Image image = new Image(file.toURI().toString());
			checkPwIcon.setImage(image);
			checkPwIcon.setVisible(true);
			pwCheckOk = true;
		}else {
			File file = new File(path + "/DataFile/ic_block_black_48dp_1x.png" );
			Image image = new Image(file.toURI().toString());
			checkPwIcon.setImage(image);
			checkPwIcon.setVisible(true);
			pwCheckOk = false;
		}
	}
	
	@FXML
	private void submitClickAction() {
		if(tfComfrimField()) {
			try {
				checkAlert(idCheckOk,"아이디 중복 체크를 해주세요.");
				checkAlert(pwCheckOk,"비밀번호가 같지 않습니다.");
				
				
			} catch (Exception e) {
				Alert alert =new Alert(AlertType.WARNING);
				alert.setTitle(null);			
				alert.setHeaderText(null);
				alert.setContentText(e.getMessage());
				alert.showAndWait();
				e.printStackTrace();
				return;
			}
			
			
			Alert alert =new Alert(AlertType.CONFIRMATION);
			alert.setTitle(null);			
			alert.setHeaderText(null);
			alert.setContentText("정말 가입하시겠습니까?");
			
			ButtonType clickType= alert.showAndWait().get();
			
			if(clickType.getText().equals("취소")) {
//				this.closeDialogAction(); 
				return;
			}
			
			
			Account account = new Account();
			account.setAccName(nameTf.getText());
			account.setAccPw(pwTf.getText());
			account.setAccId(idTf.getText());
			account.setAccTel(phoneTf.getText());
			account.setAccAddr("[" + addrZipTf.getText() +"]"+ addrTf.getText());			
			// 코드 지정
			String code = "2";
			String year   = new java.text.SimpleDateFormat("yy").format(new java.util.Date());
			
			int codeNum = Integer.parseInt(code+year+00001);
			int maxCode = accService.findMaxCode();
			
			if(maxCode >= codeNum) {				
				maxCode++;
				String maxCodeStr = maxCode+"";
				code += year+ maxCodeStr.substring(3, maxCodeStr.length());
				codeNum = Integer.parseInt(code);
			}
			account.setAccCode(codeNum);
			accService.insertAccount(account);
			
			this.closeDialogAction();
		};
	}

	

	private void checkAlert(boolean isOk,String pwck) throws Exception {
		if(!isOk) {
			throw new Exception(pwck);
		}
	}

	private boolean tfComfrimField() {		
		try {
			util.regexTfComfirmTel(phoneTf);
			util.regexTfComfirmPw(pwTf);
			util.regexTfComfirmAccName(nameTf);
			util.tfComfrim(idTf);
			util.tfComfrim(nameTf);
			util.tfComfrim(phoneTf);
			util.tfComfrim(pwTf);
			util.tfComfrim(pwTfComf);
			util.tfComfrim(addrTf);
			util.tfComfrim(addrZipTf);
			return true;
		} catch (Exception e) {
			Alert alert =new Alert(AlertType.WARNING);
			alert.setTitle(null);			
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.show();
			e.printStackTrace();
			return false;
		}		
	}
	
	@FXML
	private void closeDialogAction() {
		 dialogStage.close();
	}
}
