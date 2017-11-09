package kr.or.dgit.SaleManagement.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class JoinUserDialog {
	@FXML
	private Parent pane;

	@FXML
	private TextField imgNameTf;

	@FXML
	private Button userImgBtn;

	@FXML
	private ImageView userImg;

	@FXML
	private TextField idTf;

	@FXML
	private TextField pwTf;

	@FXML
	private TextField psTfComf;

	@FXML
	private TextField nameTf;

	@FXML
	private TextField phoneTf;

	@FXML
	private TextField codeTf;
	
	
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
	private void closeDialogAction() {
		 dialogStage.close();
	}
}
