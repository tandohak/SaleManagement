package kr.or.dgit.SaleManagement.controller.dialogController;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import kr.or.dgit.SaleManagement.dto.Account;
import kr.or.dgit.SaleManagement.service.AccountService;

public class PasswordChangeDialogController{
	@FXML private Parent pane;
	@FXML private TextField tfName;
	@FXML private TextField tfId;
	@FXML private PasswordField tfPassword;
	@FXML private PasswordField tfPasswordConfirm;
	@FXML private ImageView imgId;
	@FXML private ImageView imgPassword;
	
	private Stage dialogStage;
	private AccountService accountService;
	private Account existUser = new Account();
	private boolean pwChecker = false;
	
	
	@FXML private void initialize() {
		this.accountService = AccountService.getInstance();
		tfPassword.setVisible(false);
		tfPasswordConfirm.setVisible(false);
	}
		
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	// 유저 존재여부 확인 메소드
	public void findUser() {
		Account findAccount = new Account();
				
		// 확인1 : 텍스트필드 내 문자열 존재확인
		if(tfName.getText().equals("") || tfId.getText().equals("")) {
			// 경고알람 출력 : 업체명,ID 필수입력해주셔야 합니다.
			System.out.println("걸림");
			return;
		}
		
		// ID를 기반으로 거래처 검색
		findAccount.setAccId(tfId.getText());
		existUser = accountService.findAccountById(findAccount);
		// ID,업체명 동일 할 경우 거래처 복사 후 비밀번호 텍스트 활성화
		if(existUser.getAccName().equals(tfName.getText())) {
			imgId.setVisible(true);
			tfPassword.setVisible(true);
			tfPasswordConfirm.setVisible(true);
		}
		else {
			// 경고알람 출력 : 존재하지 않거나 정보가 일치하지 않습니다.
		}
	}
	
	// 변경버튼 클릭 시
	@FXML
	private void ok() {
		changePassword();
		// 알람출력 : 비밀번호 변경에 성공하였습니다.
		
		dialogStage.close();
	}
	
	// 비밀번호 변경 메소드
	public void changePassword() {
		// 비밀번호를 변경해도 되는지 패스워드체커를 통해 확인
		if(pwChecker) {
			existUser.setAccPw(tfPassword.getText());
			accountService.updateAccount(existUser);
		}
		// 패스워드체커가 불허 상태일 때 경고 후 포커스 이동
		else {
			// 경고알람 : 패스워드를 다시 확인해주세요.
			tfPassword.setFocusTraversable(true);
		}
	}
	
	
	// 비밀번호 테스트 메소드
	public void passwordConfirm(KeyEvent event) {
		String path = System.getProperty("user.dir");
		String password = tfPassword.getText();
		String passwordConfirm = tfPasswordConfirm.getText();
	
		// 패스워드 텍스트필드 공백확인
		if(password.equals("") || passwordConfirm.equals("")) {
			imgPassword.setVisible(false);
		}
		// 패스워드, 패스워드확인  문자열이 동일할 시
		else if(password.equals(passwordConfirm)) {
			File file = new File(path + "/DataFile/ic_check_circle_black_48dp_1x.png" );
			Image image = new Image(file.toURI().toString());
			imgPassword.setImage(image);
			imgPassword.setVisible(true);
			pwChecker = true;
		}
		// 패스워드, 패스워드확인 문자열이 동일하지 않을경우
		else {
			File file = new File(path + "/DataFile/ic_block_black_48dp_1x.png" );
			Image image = new Image(file.toURI().toString());
			imgPassword.setImage(image);
			imgPassword.setVisible(true);
			pwChecker = false;
		}
	}
	
	@FXML
	private void cancle() {
		dialogStage.close();
	}
	
	/*
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

	        if(controller.isOkClicked()) {
	          AddrItem addrItem  = controller.getAddrItem();
	          addrTf.setText(addrItem.getAddr());
	          addrZipTf.setText(addrItem.getAddrZip());
	        }
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
	*/
	
}
