package kr.or.dgit.SaleManagement.controller.dialogController;

import java.io.File;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kr.or.dgit.SaleManagement.MainApp;
import kr.or.dgit.SaleManagement.dto.Account;
import kr.or.dgit.SaleManagement.dto.AccountLevel;
import kr.or.dgit.SaleManagement.dto.AddrItem;
import kr.or.dgit.SaleManagement.util.TextFieldUtil;

public class AccountEditDialogController {
	@FXML private BorderPane pane;
	@FXML private TextField nameTf;	
	@FXML private Label codeLabel;
	@FXML private Label idLabel;	
	@FXML private Label headLabel;
	@FXML private TextField pwTf;	
	@FXML private TextField pwComfTf;	
	@FXML private TextField picTf;	
	@FXML private TextField addrTf;	
	@FXML private TextField addrZipTf;	
	@FXML private TextField telTf;
	@FXML private ImageView checkPwIcon;
	@FXML private ComboBox<AccountLevel> levelCb;
	
	private ObservableList<AccountLevel> levellist = FXCollections.observableArrayList();
	
	private Stage dialogStage;
	private Account account;
	private boolean okClicked = false;
	private TextFieldUtil tfUtil = new TextFieldUtil();
	
	private boolean pwCheckOk;
	
	@FXML
	private void initialize() {
		
	}

	public void setLevellist(ObservableList<AccountLevel> levellist) {
		this.levellist = levellist;	
		System.out.println(levellist);
		levelCb.setItems(levellist);
		
	}

	public void setAccount(Account account) {
		this.account = account;
		System.out.println(account);
		codeLabel.setText(account.getAccCode()+"");
		nameTf.setText(account.getAccName());
		idLabel.setText(account.getAccId());	
		levelCb.setValue(new AccountLevel(account.getAccLevel()));
		telTf.setText(account.getAccTel());		
		String addrs =  account.getAccAddr();
		addrZipTf.setText(addrs.substring(addrs.indexOf("[")+1, addrs.indexOf("]")));
		addrTf.setText(addrs.substring(addrs.indexOf("]")+1, addrs.length()));		
	}

	public Account getAccount() {
		return account;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}	
	
	public boolean isOkClicked() {
        return okClicked;
    }

	@FXML
    private void handleOk() {
        if (tfComfrimField()) {
			account.setAccName(nameTf.getText());
			
			String pwVal =pwTf.getText();
			if(pwCheckOk || !pwVal.equals("")) {
				account.setAccPw(pwTf.getText());
			}
			
			account.setAccTel(telTf.getText());
			account.setAccAddr("["+ addrZipTf.getText() + "]" + addrTf.getText());
			account.setAccCode(Integer.parseInt(codeLabel.getText()));
			account.setAccLevel(levelCb.getValue().getAccLevel());
			okClicked = true;		
			dialogStage.close();
        }
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
	
	@FXML
	private void handleCancel() {
	      dialogStage.close();
	}
	
	private boolean tfComfrimField() {
		try {
			tfUtil.regexTfComfirmAccName(nameTf);
			if(!pwTf.getText().equals("")) {
				if(!pwCheckOk) {
					throw new Exception("비밀번호가 틀립니다.");
				}
				tfUtil.regexTfComfirmPw(pwTf);
			}			
			tfUtil.regexTfComfirmTel(telTf);
			tfUtil.tfComfrim(nameTf);
			tfUtil.cbComfrim(levelCb);
			tfUtil.tfComfrim(telTf);
			tfUtil.tfComfrim(addrTf);
			tfUtil.tfComfrim(addrZipTf);
			return true;
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("공백존재");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			e.printStackTrace();
			return false;
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

	public void changeHeader() {
		headLabel.setText("회원 정보 수정");		
	}
}
