package kr.or.dgit.SaleManagement.controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import kr.or.dgit.SaleManagement.dto.Account;
import kr.or.dgit.SaleManagement.dto.AccountLevel;

public class ProductSearchAccountController {
	@FXML private BorderPane pane;
	@FXML private TextField searchAllTf;

	@FXML private TableColumn<Account, Integer> codeTc;
	@FXML private TableColumn<Account, String> telTc;
	@FXML private TableColumn<Account, String> nameTc;
	@FXML private TableColumn<Account, String> levelTc;
	@FXML private TableColumn<Account, String> admitTc;
	@FXML private TableColumn<Account, Boolean> chckTc;
	@FXML private TableColumn<Account, String> addrTc;
	
	
	@FXML private TableView<Account> accTable;
	
	@FXML private CheckBox accCheck;

	@FXML
	private void initialize() {
		
	}


}
