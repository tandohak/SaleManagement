package kr.or.dgit.SaleManagement.controller;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import kr.or.dgit.SaleManagement.dto.Account;
import kr.or.dgit.SaleManagement.dto.AccountLevel;
import kr.or.dgit.SaleManagement.service.AccountLevelService;
import kr.or.dgit.SaleManagement.service.AccountService;

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

	private ObservableList<Account> myList = FXCollections.observableArrayList();
	private ObservableList<AccountLevel> levellist = FXCollections.observableArrayList();
	private AccountService accountService;
	private AccountLevelService aLevelService;
	private Stage dialogStage;
	private Account getAcc;
	private boolean okClicked = false;
	
	
	public boolean isOkClicked() {
		 return okClicked;
	}
	
	public Account getGetAcc() {
		return getAcc;
	}

	public void setGetAcc(Account getAcc) {
		this.getAcc = getAcc;
	}

	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	@FXML
	private void initialize() {
		aLevelService = AccountLevelService.getInstance();
		
		Account admitTrue = new Account();
		admitTrue.setAccAdmit("true");
		accountService = AccountService.getInstance();
		List<Account> lists = accountService.findAllAdmitAccount(admitTrue);
		for (Account account : lists) {
			myList.add(account);
		}
		
		codeTc.setCellValueFactory(cellData -> cellData.getValue().getAccCodeProperty().asObject());
		nameTc.setCellValueFactory(cellData -> cellData.getValue().getAccNameProperty());
		telTc.setCellValueFactory(cellData -> cellData.getValue().getAccTelProperty());
		levelTc.setCellValueFactory(cellData -> cellData.getValue().getAccLevelProperty());
		admitTc.setCellValueFactory(cellData -> cellData.getValue().getAccAdmitProperty());
		addrTc.setCellValueFactory(cellData -> cellData.getValue().getAccAddrProperty());

		accTable.setItems(myList);
	}
	
	@FXML
	private void setAccountModel(List<Account> lists) {
		myList = FXCollections.observableArrayList();
		for(Account account : lists) {
			myList.add(account);
		}
		accTable.setItems(myList);
	}
	
	
	@FXML
	private void searchAccount() {
		Account findAccount = new Account();
		List<Account> lists;
		findAccount.setAccName("%" + searchAllTf.getText() + "%");
		
			
		findAccount.setAccAdmit("true");
		lists = accountService.findAccountLikeName(findAccount);
		setAccountModel(lists);
	}
	
	private void checkAlert(boolean isOk,String pwck) throws Exception {
		if(!isOk) {
			throw new Exception(pwck);
		}
	}
	
	
	
	@FXML
	private void handleOk() {
		Account acc = accTable.getSelectionModel().getSelectedItem();
		getAcc = acc;

		try {
			checkAlert(acc==null ? false : true,"거래처를 선택 해주세요.");
		}catch(Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			//alert.setContentText("a");
			alert.showAndWait();
			//e.printStackTrace();
			return ;			
		}		
		okClicked = true;
		
		dialogStage.close();
		

	}
	
	@FXML
	private void handleCancel() {		
		dialogStage.close();
	}

}
