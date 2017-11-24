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
		String pattern = "^[a-zA-Z가-힣]{1,15}$";
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

		Pattern p = Pattern.compile(pattern);
		
		Matcher m = p.matcher(pwTf.getText());
		if(!m.find())
		{
			pwTf.requestFocus();
			throw new Exception(errmsg);
		}
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
									
			Alert alert = new Alert(AlertType.CONFIRMATION);
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
			alert.setContentText("원가가 판매원가보다 클 수 없습니다.");
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
