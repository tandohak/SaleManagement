package kr.or.dgit.SaleManagement.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.ibatis.javassist.URLClassPath;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
	private TextField codeTf;
	
	@FXML
	private ImageView checkPwIcon;
	
	private Stage dialogStage;
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

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
	private void pwTypeHandler(KeyEvent event) {
		String path = System.getProperty("user.dir");
		File file = new File(path + "/DataFile/ic_block_black_48dp_1x.png" );
		Image image = new Image(file.toURI().toString());
		checkPwIcon.setImage(image);
	}
	
	@FXML
	private void submitClickAction() {
		if(tfComfrimField()) {
			idTf.getText();
			nameTf.getText();
			phoneTf.getText();
			codeTf.getText();
			pwTf.getText();
		};
		
	}

	private boolean tfComfrimField() {		
		try {
			tfComfrim(idTf);
			tfComfrim(nameTf);
			tfComfrim(phoneTf);
			tfComfrim(codeTf);
			tfComfrim(pwTf);
			tfComfrim(pwTfComf);
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
	
	public void tfComfrim(TextField tf) throws Exception {
		if(tf.getText().equals("")) {
		  throw new Exception("공백 존재");
		}
	}
	
	@FXML
	private void closeDialogAction() {
		 dialogStage.close();
	}
}
