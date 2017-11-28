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
import kr.or.dgit.SaleManagement.dto.AddrItem;
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
	private boolean okJoin = false;
	
	@FXML
	private void initialize() {
	}

	public boolean isOkJoin() {
		return okJoin;
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
			
			Alert alert =new Alert(AlertType.CONFIRMATION);
			alert.setTitle(null);			
			alert.setHeaderText(null);
			alert.setContentText("정말 가입하시겠습니까?");
			
			ButtonType clickType= alert.showAndWait().get();
			
			if(clickType.getText().equals("취소")) {
				return;
			}
			
			Account account = new Account();
			account.setAccName(nameTf.getText());
			String pw = changeKorean(pwTf.getText());
			account.setAccPw(pw);
			account.setAccId(idTf.getText());
			account.setAccTel(phoneTf.getText());
			account.setAccAddr("[" + addrZipTf.getText() +"]"+ addrTf.getText());
			account.setAccAdmit("false");
			account.setAccLevel("no");
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
			
			okJoin = true;
			this.closeDialogAction();
		};
	}

	

	private String changeKorean(String word) {
		// 분리할 단어
				String result = "";
				// 결과 저장할 변수
				String resultEng = "";
				// 알파벳으로
				char[] arrChoSung = { 0x3131, 0x3132, 0x3134, 0x3137, 0x3138, 0x3139, 0x3141, 0x3142, 0x3143, 0x3145, 0x3146,
						0x3147, 0x3148, 0x3149, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e };

				char[] arrJungSung = { 0x314f, 0x3150, 0x3151, 0x3152, 0x3153, 0x3154, 0x3155, 0x3156, 0x3157, 0x3158, 0x3159,
						0x315a, 0x315b, 0x315c, 0x315d, 0x315e, 0x315f, 0x3160, 0x3161, 0x3162, 0x3163 };

				char[] arrJongSung = { 0x0000, 0x3131, 0x3132, 0x3133, 0x3134, 0x3135, 0x3136, 0x3137, 0x3139, 0x313a, 0x313b,
						0x313c, 0x313d, 0x313e, 0x313f, 0x3140, 0x3141, 0x3142, 0x3144, 0x3145, 0x3146, 0x3147, 0x3148, 0x314a,
						0x314b, 0x314c, 0x314d, 0x314e };

				String[] arrChoSungEng = { "r", "R", "s", "e", "E", "f", "a", "q", "Q", "t", "T", "d", "w", "W", "c", "z", "x",
						"v", "g" };

				String[] arrJungSungEng = { "k", "o", "i", "O", "j", "p", "u", "P", "h", "hk", "ho", "hl", "y", "n", "nj", "np",
						"nl", "b", "m", "ml", "l" };

				String[] arrJongSungEng = { "", "r", "R", "rt", "s", "sw", "sg", "e", "f", "fr", "fa", "fq", "ft", "fx", "fv",
						"fg", "a", "q", "qt", "t", "T", "d", "w", "c", "z", "x", "v", "g" };

				String[] arrSingleJaumEng = { "r", "R", "rt", "s", "sw", "sg", "e", "E", "f", "fr", "fa", "fq", "ft", "fx",
						"fv", "fg", "a", "q", "Q", "qt", "t", "T", "d", "w", "W", "c", "z", "x", "v", "g" };

				for (int i = 0; i < word.length(); i++) { /* 한글자씩 읽어들인다. */
					char chars = (char) (word.charAt(i) - 0xAC00);
					if (chars >= 0 && chars <= 11172) {
						/* A. 자음과 모음이 합쳐진 글자인경우 */ /* A-1. 초/중/종성 분리 */
						int chosung = chars / (21 * 28);
						int jungsung = chars % (21 * 28) / 28;
						int jongsung = chars % (21 * 28) % 28;
						/* A-2. result에 담기 */
						result = result + arrChoSung[chosung] + arrJungSung[jungsung];
						/* 자음분리 */
						if (jongsung != 0x0000) { /* A-3. 종성이 존재할경우 result에 담는다 */
							result = result + arrJongSung[jongsung];
						} /* 알파벳으로 */
						resultEng = resultEng + arrChoSungEng[chosung] + arrJungSungEng[jungsung];
						if (jongsung != 0x0000) { /* A-3. 종성이 존재할경우 result에 담는다 */
							resultEng = resultEng + arrJongSungEng[jongsung];
						}
					} else { /* B. 한글이 아니거나 자음만 있을경우 */
						/* 자음분리 */
						result = result + ((char) (chars + 0xAC00)); /* 알파벳으로 */
						if (chars >= 34097 && chars <= 34126) { /* 단일자음인 경우 */
							int jaum = (chars - 34097);
							resultEng = resultEng + arrSingleJaumEng[jaum];
						} else if (chars >= 34127 && chars <= 34147) { /* 단일모음인 경우 */
							int moum = (chars - 34127);
							resultEng = resultEng + arrJungSungEng[moum];
						} else { /* 알파벳인 경우 */
							resultEng = resultEng + ((char) (chars + 0xAC00));
						}
					}
				}
				return resultEng;
			
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
			checkAlert(idCheckOk,"아이디 중복 체크를 해주세요.");
			checkAlert(pwCheckOk,"비밀번호가 같지 않습니다.");
			util.regexTfComfirmTel(phoneTf);
			util.regexTfComfirmPw(pwTf);
			util.regexTfComfirmAccName(nameTf);
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
