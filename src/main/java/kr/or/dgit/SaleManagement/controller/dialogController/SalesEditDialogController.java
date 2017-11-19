package kr.or.dgit.SaleManagement.controller.dialogController;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kr.or.dgit.SaleManagement.dto.Sales;
import kr.or.dgit.SaleManagement.dto.SalesLevel;

public class SalesEditDialogController {
	@FXML private TextField searchAllTf;

	@FXML private TextField nameTf;	
	@FXML private Label codeLabel;
	@FXML private Label idLabel;	
	@FXML private TextField pwTf;	
	@FXML private TextField pwComfTf;	
	@FXML private TextField picTf;	
	@FXML private TextField addrTf;	
	@FXML private TextField addrZipTf;	
	@FXML private TextField telTf;
	
	@FXML private ComboBox<SalesLevel> levelCb;
	
	private Stage dialogStage;
	private Sales sales;
	private boolean okClicked = false;
	
	
}
