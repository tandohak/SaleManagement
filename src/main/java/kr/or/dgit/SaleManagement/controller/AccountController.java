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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import kr.or.dgit.SaleManagement.MainApp;
import kr.or.dgit.SaleManagement.controller.dialogController.AccountEditDialogController;
import kr.or.dgit.SaleManagement.controller.dialogController.AddrDialogController;
import kr.or.dgit.SaleManagement.dto.Account;
import kr.or.dgit.SaleManagement.dto.AccountLevel;
import kr.or.dgit.SaleManagement.dto.AddrItem;
import kr.or.dgit.SaleManagement.service.AccountLevelService;
import kr.or.dgit.SaleManagement.service.AccountService;
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
	@FXML private CheckBox allAccount;
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

		Account admitTrue = new Account();
		admitTrue.setAccAdmit("true");
		accountService = AccountService.getInstance();
		List<Account> lists = accountService.findAllAdmitAccount(admitTrue);
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
	
	public void setSaleUserSetting() {
		AnchorPane anchorBotton = new AnchorPane();
		anchorBotton.setPrefHeight(73);
		AnchorPane anchorTop = (AnchorPane)pane.getTop();
		anchorTop.setPrefHeight(80);
		pane.setBottom(anchorBotton);
		allAccount.setVisible(false);
	}
	
	@FXML
	private boolean allAccountCheck() {
		return allAccount.isSelected();
	}
	
	@FXML
	private void searchAccount() {
		Account findAccount = new Account();
		List<Account> lists;
		findAccount.setAccName("%" + searchAllTf.getText() + "%");
		if(findAccount.getAccName().equals("%%")) {
			if(allAccountCheck()) {
				refreshTable();
			}
			else {
				findAccount.setAccName(null);
				findAccount.setAccAdmit("true");
				lists = accountService.findAllAdmitAccount(findAccount);
				setAccountModel(lists);
			}
			return;
		}
		if(!allAccountCheck()) {
			findAccount.setAccAdmit("true");
			lists = accountService.findAccountLikeName(findAccount);
		}
		else {
			lists = accountService.findAccountLikeName(findAccount);
		}
		setAccountModel(lists);
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
	private void getCellMenuAction() {		
		Account account = accTable.getSelectionModel().getSelectedItem();
		
		try {
			checkAlert(account==null ? false : true,"수정할 열을 선택 해주세요.");
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			e.printStackTrace();
			e.printStackTrace();
			return ;
		}

		try {
		        FXMLLoader loader = new FXMLLoader();
		        loader.setLocation(MainApp.class.getResource("view/dialog/AccountEditDialog.fxml"));
		        BorderPane page = (BorderPane) loader.load();
		        Stage dialogStage = new Stage();
		        
		        dialogStage.setTitle("거래처 수정");
		        dialogStage.initModality(Modality.WINDOW_MODAL);		        
		        dialogStage.initOwner(pane.getScene().getWindow());
		        Scene scene = new Scene(page);
		        dialogStage.setScene(scene);
		        
		        AccountEditDialogController controller = loader.getController();
		        controller.setDialogStage(dialogStage);
		        controller.setLevellist(levellist);
		        controller.setAccount(account);
		        
		        dialogStage.showAndWait();

		        if(controller.isOkClicked()) {
		        	System.out.println(controller.getAccount());
		        	accountService.updateAccount(controller.getAccount());
		        	refreshTable();
		        }
		   } catch (IOException e) {
		        e.printStackTrace();
		   }	
	}
	
	@FXML
	private void submitClickAction() {		
		if(tfComfrimField()) {
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle(null);			
			alert.setHeaderText(null);
			alert.setContentText("정말 등록하시겠습니까?");
			
			ButtonType clickType= alert.showAndWait().get();
			
			if(clickType.getText().equals("취소")) {
				return;
			}
			
			Account account = new Account();
			account.setAccName(nameTf.getText());
			account.setAccId(idTf.getText());
			account.setAccPw(pwTf.getText());
			account.setAccTel(telTf.getText());
			account.setAccAddr("["+ addrZipTf.getText() + "]" + addrTf.getText());
			account.setAccLevel(levelCb.getValue().getAccLevel());
			account.setAccAdmit("true");
			
			String year = "2" + (new java.text.SimpleDateFormat("yy").format(new java.util.Date()));
			int maxCode = accountService.findMaxCode();
			if(maxCode/100000 < Integer.valueOf(year)) {
				year += "00001";
				maxCode = Integer.valueOf(year);
			}
			else {
				maxCode += 1;
			}
			
			account.setAccCode(maxCode);
			accountService.insertAccount(account);
			refreshTable();
		}
	}

	private void checkAlert(boolean isOk,String pwck) throws Exception {
		if(!isOk) {
			throw new Exception(pwck);
		}
	}
	
	@FXML
	private void idTypeCheck(KeyEvent event) {
		idCheckOk = false;
		checkIdIcon.setVisible(false);		
	}
	
		
	@FXML
	private void idTypeHandler() {
		String path = System.getProperty("user.dir");
		
		try {
			tfUtil.regexTfComfirmId(idTf);
		} catch (Exception e) {		
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			e.printStackTrace();
			return;
		}
		
		idCheckOk = tfUtil.idOverlapCheck(idTf);
		
		if(idCheckOk) {
			File file = new File(path + "/DataFile/ic_check_circle_black_48dp_1x.png" );
			Image image = new Image(file.toURI().toString());
			checkIdIcon.setImage(image);
			checkIdIcon.setVisible(true);
		}else {
			checkIdIcon.setVisible(false);
		}
	}
	
	private void refreshTable() {
		myList = FXCollections.observableArrayList();
		List<Account> lists = accountService.findAllAccount();
		for(Account acc : lists) {
			myList.add(acc);
		}
		accTable.setItems(myList);
	}
	
	@FXML
	private void pwTypeHandler(KeyEvent event) {
		String path = System.getProperty("user.dir");
		String pwVal =pwTf.getText();
		String pwComVal = pwComfTf.getText();
	
		if(pwVal.equals("") || pwComVal.equals("")) {
			checkPwIcon.setVisible(false);
		}else if(pwVal.equals(pwComVal)) {
			File file = new File(path + "/DataFile/ic_check_circle_black_48dp_1x.png" );
			Image image = new Image(file.toURI().toString());
			checkPwIcon.setImage(image);
			checkPwIcon.setVisible(true);
			pwCheckOk = true;
		}else {
			File file = new File(path + "/DataFile/ic_block_black_48dp_1x.png" );
			Image image = new Image(file.toURI().toString());
			checkPwIcon.setImage(image);
			checkPwIcon.setVisible(true);
			pwCheckOk = false;
		}
	}

	private Boolean tfComfrimField() {
		try {			
			tfUtil.tfComfrim(nameTf);
			tfUtil.cbComfrim(levelCb);
			tfUtil.tfComfrim(idTf);
			tfUtil.tfComfrim(pwTf);
			tfUtil.tfComfrim(pwComfTf);
			tfUtil.tfComfrim(telTf);
			tfUtil.tfComfrim(addrTf);
			tfUtil.tfComfrim(addrZipTf);
			checkAlert(idCheckOk,"아이디 중복 체크를 해주세요.");
			checkAlert(pwCheckOk,"비밀번호가 같지 않습니다.");				
			tfUtil.regexTfComfirmAccountName(nameTf);
			tfUtil.regexTfComfirmPw(pwTf);
			tfUtil.regexTfComfirmTel(telTf);
			return true;
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			e.printStackTrace();
			return false;
		}		
	}
	
	@FXML
	private void deleteCellMenuAction() {
		int index = accTable.getSelectionModel().getSelectedIndex();
		Account account = accTable.getSelectionModel().getSelectedItem();
		account.setAccAdmit("false");
		accountService.updateAccount(account);
		accTable.getItems().remove(index);
	}
	
	@FXML
	private void deleteSelectedCell(ActionEvent event) {
		for(int i=0; myList.size()>i; i++) {
			Account account = myList.get(i);
			
			if(account.getCheckedBox()) {
				 myList.remove(account);
				 accountService.updateAccount(account);
				 i = 0;
			};
		}
	}
	
	@FXML
	private void searchAddrAction() {
		try {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/dialog/AddrZipSearchDialog.fxml"));
	        BorderPane page = (BorderPane) loader.load();

	        Stage dialogStage = new Stage();
	        dialogStage.setTitle(null);
	        dialogStage.initModality(Modality.WINDOW_MODAL);		        
	        dialogStage.initOwner(pane.getScene().getWindow());
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
	        
	        AddrDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	     
	        
	        dialogStage.showAndWait();

	        if(controller.isOkClicked()) {
	          AddrItem addrItem  = controller.getAddrItem();
	          addrTf.setText(addrItem.getAddr());
	          addrZipTf.setText(addrItem.getAddrZip());
	        }
	   } catch (IOException e) {
	        e.printStackTrace();
	   }
	}
}
