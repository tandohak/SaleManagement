package kr.or.dgit.SaleManagement.util;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import kr.or.dgit.SaleManagement.dto.Account;
import kr.or.dgit.SaleManagement.dto.Sales;
import kr.or.dgit.SaleManagement.service.AccountService;
import kr.or.dgit.SaleManagement.service.SalesService;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class TextFieldUtil {
	private AccountService accService;
	private SalesService salesService;
	
	
	
	public TextFieldUtil() {
		accService = AccountService.getInstance();
		salesService = SalesService.getInstance();
	}
	
		
	public void cbComfrim(ComboBox<?> cb) throws Exception {
		if(cb.getValue() == null) {
			throw new Exception("콤보박스를 선택하세요.");
		}
	}
	
	public void tfComfrim(TextField tf) throws Exception {
		if(tf.getText().trim().equals("")) {
		  tf.requestFocus();
		  throw new Exception("공백이 존재합니다.");
		}
	}
	
	public void tfComfrimAcc(TextField tf) throws Exception {
		if(tf.getText().trim().equals("")) {
		  throw new Exception("거래처 코드를 입력하세요");
		}
	}
	
	public void regexTfComfirm(String pattern, String errmsg, TextField comTf) throws Exception {
		Pattern p = Pattern.compile(pattern);
		
		Matcher m = p.matcher(comTf.getText());
		if(!m.find())
		{	
			throw new Exception(errmsg);
		}
	}
	
	public void regexTfComfirmNumber(TextField idTf) throws Exception {
		String pattern = "^[0-9]{1,20}$";
		String errmsg = "숫자만 입력 가능합니다.";

		Pattern p = Pattern.compile(pattern);
		
		Matcher m = p.matcher(idTf.getText());
		if(!m.find())
		{	
			idTf.requestFocus();
			throw new Exception(errmsg);
		}
	}
	
	public void regexTfComfirmId(TextField idTf) throws Exception {
		String pattern = "^[a-zA-Z0-9_]{1,15}$";
		String errmsg = "아이디는 영'문자/숫자/_' 만 가능합니다.";

		Pattern p = Pattern.compile(pattern);
		
		Matcher m = p.matcher(idTf.getText());
		if(!m.find())
		{	
			idTf.requestFocus();
			throw new Exception(errmsg);
		}
	}
	
	public void regexTfComfirmSaleName(TextField nameTf) throws Exception {
		String pattern = "^[가-힣]{1,4}$";
		String errmsg = "이름은 한글 1-4자리 까지만 가능합니다.";

		Pattern p = Pattern.compile(pattern);
		
		Matcher m = p.matcher(nameTf.getText());
		if(!m.find())
		{
			nameTf.requestFocus();
			throw new Exception(errmsg);
		}
	}
	
	public void regexTfComfirmAccountName(TextField nameTf) throws Exception {
		String pattern = "^[a-zA-Z가-힣0-9]{1,20}$";
		String errmsg = "관리자에게 문의하세요";

		Pattern p = Pattern.compile(pattern);
		
		Matcher m = p.matcher(nameTf.getText());
		if(!m.find())
		{
			nameTf.requestFocus();
			throw new Exception(errmsg);
		}
	}
	
	public void regexTfComfirmAccProductName(TextField nameTf) throws Exception {
		String pattern = "^[a-zA-Z가-힣0-9]{1,15}$";
		String errmsg = "제품명은 한글/영문/숫자만 가능합니다.";

		Pattern p = Pattern.compile(pattern);
		
		Matcher m = p.matcher(nameTf.getText());
		if(!m.find())
		{
			nameTf.requestFocus();
			throw new Exception(errmsg);
		}
	}
	
	
	public void regexTfComfirmAccName(TextField nameTf) throws Exception {
		String pattern = "^[a-zA-Z가-힣0-9]{1,15}$";
		String errmsg = "업체명은 한글/영문만 가능합니다.";

		Pattern p = Pattern.compile(pattern);
		
		Matcher m = p.matcher(nameTf.getText());
		if(!m.find())
		{
			nameTf.requestFocus();
			throw new Exception(errmsg);
		}
	}
	
	public void regexTfComfirmPw(TextField pwTf) throws Exception {
		String pattern = "^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$";
		String errmsg = "비밀번호는 8-15자리 이내로 영문자,숫자,특수문자 조합이어야 합니다.";
		String t = changeKorean(pwTf.getText());
		Pattern p = Pattern.compile(pattern);
		
		Matcher m = p.matcher(t);
		if(!m.find())
		{
			pwTf.requestFocus();
			throw new Exception(errmsg);
		}
	}
	
	public static String changeKorean(String word) {
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
	
	public void regexTfComfirmTel(TextField telTf) throws Exception {
		String pattern = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$";
		String errmsg = "핸드폰 형식에 맞춰 입력해 주세요 ex) 010-1234-1234";

		Pattern p = Pattern.compile(pattern);
		
		Matcher m = p.matcher(telTf.getText());
		if(!m.find())
		{
			telTf.requestFocus();
			throw new Exception(errmsg);
		}
	}
	
	public Boolean idOverlapCheck(TextField idTf) {

		
		Sales sales = new Sales();
		sales.setSaleId(idTf.getText());
		
		Account acc = new Account();
		acc.setAccId(idTf.getText());
		
		Sales saleFind = salesService.findSalesByCode(sales);
		Account accFind = accService.findAccountById(acc);
		
		boolean checkId = true;	
		try{tfComfrim(idTf);}catch (Exception e) {
			Alert alert =new Alert(AlertType.WARNING);
			alert.setTitle("공백존재");			
			alert.setContentText("공백이 존재합니다.");
			alert.show();
			e.printStackTrace();
			return false;
		}
			
		if(saleFind != null) {
			checkId = false;
		}
		
		if(accFind != null) {
			checkId = false;
		}
		
		if(checkId) {			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText("사용가능한 아이디 입니다.");
			alert.showAndWait();
			
			return true;
		}else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText("아이디가 이미 존재합니다.");
			alert.showAndWait();
			return false;
		}
	}


	public Boolean regexTfComfirmCost(TextField priceTf, TextField costTf) {
		int p = Integer.parseInt(priceTf.getText());
		int c = Integer.parseInt(costTf.getText());
		if(p < c) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText("제품원가가 판매정가보다 클 수 없습니다.");
			alert.showAndWait();
			return false;
		}else {
			return true;
		}
		
	}


	public void tfClear(TextField accTf) {
		accTf.setText("");
	}

	
	
}
