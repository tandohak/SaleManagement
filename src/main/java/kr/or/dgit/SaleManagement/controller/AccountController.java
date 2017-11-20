package kr.or.dgit.SaleManagement.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import kr.or.dgit.SaleManagement.MainApp;
import kr.or.dgit.SaleManagement.controller.dialogController.SalesEditDialogController;
import kr.or.dgit.SaleManagement.dto.Account;
import kr.or.dgit.SaleManagement.dto.AccountLevel;
import kr.or.dgit.SaleManagement.dto.Sales;
import kr.or.dgit.SaleManagement.service.AccountLevelService;
import kr.or.dgit.SaleManagement.service.AccountService;
import kr.or.dgit.SaleManagement.service.SalesService;
import kr.or.dgit.SaleManagement.util.TextFieldUtil;

public class AccountController {
	@FXML private BorderPane pane;
	@FXML private TextField searchAllTf;

	@FXML private TextField nameTf;
	@FXML private Label codeLabel;
	@FXML private TextField idTf;
	@FXML private TextField pwTf;
	@FXML private TextField pwComfTf;
	@FXML private TextField picTf;
	@FXML private TextField addrTf;
	@FXML private TextField addrZipTf;
	@FXML private TextField telTf;
	@FXML private TableColumn<Account, Integer> codeTc;
	@FXML private TableColumn<Account, String> telTc;
	@FXML private TableColumn<Account, String> nameTc;
	@FXML private TableColumn<Account, String> levelTc;
	@FXML private TableColumn<Account, String> admitTc;
	@FXML private TableColumn<Account, Boolean> chckTc;
	@FXML private TableColumn<Account, String> addrTc;
	@FXML private ComboBox<AccountLevel> levelCb;
	@FXML private CheckBox accCheck;
	@FXML private TableView<Account> accTable;
	@FXML private Button submitBtn;
	@FXML private ImageView checkIdIcon;
	@FXML private ImageView checkPwIcon;

	private TextFieldUtil tfUtil = new TextFieldUtil();
	private ObservableList<Account> myList = FXCollections.observableArrayList();
	private ObservableList<AccountLevel> levellist = FXCollections.observableArrayList();
	private AccountService accountService;
	private AccountLevelService aLevelService;
	private boolean idCheckOk;
	private boolean pwCheckOk;

	@FXML
	private void initialize() {
		aLevelService = AccountLevelService.getInstance();
		List<AccountLevel> aLevelLists = aLevelService.findAllAccountLevel();
		for (AccountLevel accountLevel : aLevelLists) {
			levellist.add(accountLevel);
		}
		levelCb.setItems(levellist);

		accountService = AccountService.getInstance();
		List<Account> lists = accountService.findAllAccount();
		for (Account account : lists) {
			myList.add(account);
		}

		chckTc.setCellFactory(new Callback<TableColumn<Account, Boolean>, TableCell<Account, Boolean>>() {
			@Override
			public TableCell<Account, Boolean> call(TableColumn<Account, Boolean> param) {
				CheckBoxTableCell<Account, Boolean> checkBoxTbc = new CheckBoxTableCell<>();
				checkBoxTbc.setSelectedStateCallback(new Callback<Integer, ObservableValue<Boolean>>() {

					@Override
					public ObservableValue<Boolean> call(Integer index) {

						return accTable.getItems().get(index).selectedProperty();
					}
				});

				return checkBoxTbc;
			}
		});

		accCheck.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					for (Account account : myList) {
						account.setCheckedBox(true);
					}
				} else {
					for (Account account : myList) {
						account.setCheckedBox(false);
					}
				}
			}
		});

		codeTc.setCellValueFactory(cellData -> cellData.getValue().getAccCodeProperty().asObject());
		nameTc.setCellValueFactory(cellData -> cellData.getValue().getAccNameProperty());
		telTc.setCellValueFactory(cellData -> cellData.getValue().getAccTelProperty());
		levelTc.setCellValueFactory(cellData -> cellData.getValue().getAccLevelProperty());
		admitTc.setCellValueFactory(cellData -> cellData.getValue().getAccAdmitProperty());
		addrTc.setCellValueFactory(cellData -> cellData.getValue().getAccAddrProperty());

		accTable.setItems(myList);
	}
}
