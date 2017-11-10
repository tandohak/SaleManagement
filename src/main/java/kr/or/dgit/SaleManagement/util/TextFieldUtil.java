package kr.or.dgit.SaleManagement.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class TextFieldUtil {

	public void tfComfrim(TextField tf) throws Exception {
		if(tf.getText().equals("")) {
		  throw new Exception("공백 존재");
		}
	}
}
