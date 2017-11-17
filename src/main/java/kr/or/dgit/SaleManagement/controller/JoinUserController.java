package kr.or.dgit.SaleManagement.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import kr.or.dgit.SaleManagement.dto.Account;
import kr.or.dgit.SaleManagement.dto.Sales;
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
	private void idTypeHandler() {
		String path = System.getProperty("user.dir");
		
		Sales sales = new Sales();
		sales.setSaleId(idTf.getText());
		
		Account acc = new Account();
		acc.setAccId(idTf.getText());
		
		Sales saleFind = salesService.findSalesByCode(sales);
		Account accFind = accService.findAccountById(acc);
		
		boolean checkId = true;	
		try{util.tfComfrim(idTf);}catch (Exception e) {
			Alert alert =new Alert(AlertType.WARNING);
			alert.setTitle("공백존재");			
			alert.setContentText("공백이 존재합니다.");
			alert.show();
			e.printStackTrace();
			return;
		}
			
		if(saleFind != null) {
			checkId = false;
		}
		
		if(accFind != null) {
			checkId = false;
		}
			
		if(checkId) {
			File file = new File(path + "/DataFile/ic_check_circle_black_48dp_1x.png" );
			Image image = new Image(file.toURI().toString());
			checkIdIcon.setImage(image);
			checkIdIcon.setVisible(true);
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText("사용가능한 아이디 입니다.");
			alert.showAndWait();
			idCheckOk= true;
		}else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText("아이디가 이미 존재합니다.");
			alert.showAndWait();
			checkIdIcon.setVisible(false);
			idCheckOk= false;
		}
	}
	
	@FXML
	private void pwTypeHandler(KeyEvent event) {
		String path = System.getProperty("user.dir");
		String pwVal =pwTf.getText();
		String pwComVal = pwTfComf.getText();
	
		if(pwVal.equals("") || pwComVal.equals("")) {
			 
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
				String pattern = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$";
				String errmsg = "핸드폰 형식에 맞춰 입력해 주세요 ex) 010-1234-1234";
				util.regexTfComfirm(pattern, errmsg, phoneTf);
				util.regexTfComfirm("^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$", "비밀번호는 8-15자리 이내로 영문자,숫자,특수문자 조합이어야 합니다.", pwTf);
				util.regexTfComfirm("^[a-zA-Z가-힣]{1,15}$", "업체명은 한글/영문만 가능합니다.", nameTf);;
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
			alert.showAndWait();
			Account account = new Account();
			account.setAccName(nameTf.getText());
			account.setAccPw(pwTf.getText());
			account.setAccId(idTf.getText());
			account.setAccTel(phoneTf.getText());
			account.setAccAddr("[" + addrZipTf.getText() +"]"+ addrTf.getText());
			String code = "2";
			String year   = new java.text.SimpleDateFormat("yy").format(new java.util.Date());
		

			int maxCode = accService.findMaxCode() + 1;
			String maxCodeStr = maxCode+"";
			code += year+ maxCodeStr.substring(3, maxCodeStr.length());
			
			account.setAccCode(Integer.parseInt(code));
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
			alert.setTitle("공백존재");			
			alert.setContentText("공백이 존재합니다.");
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
