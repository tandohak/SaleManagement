package kr.or.dgit.SaleManagement.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class TextFieldUtil {

	public void tfComfrim(TextField tf) throws Exception {
		if(tf.getText().equals("")) {
		  throw new Exception("공백 존재");
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
}
