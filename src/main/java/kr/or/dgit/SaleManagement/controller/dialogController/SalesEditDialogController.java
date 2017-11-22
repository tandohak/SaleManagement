package kr.or.dgit.SaleManagement.controller.dialogController;

import java.io.File;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import kr.or.dgit.SaleManagement.dto.Sales;
import kr.or.dgit.SaleManagement.dto.SalesLevel;
import kr.or.dgit.SaleManagement.util.TextFieldUtil;

public class SalesEditDialogController {
	@FXML private TextField nameTf;	
	@FXML private Label codeLabel;
	@FXML private Label idLabel;	
	@FXML private TextField pwTf;	
	@FXML private TextField pwComfTf;	
	@FXML private TextField picTf;	
	@FXML private TextField addrTf;	
	@FXML private TextField addrZipTf;	
	@FXML private TextField telTf;
	@FXML private ImageView checkPwIcon;
	@FXML private ComboBox<SalesLevel> levelCb;
	
	private ObservableList<SalesLevel> levellist = FXCollections.observableArrayList();
	
	private Stage dialogStage;
	private Sales sales;
	private boolean okClicked = false;
	private TextFieldUtil tfUtil = new TextFieldUtil();
	
	private boolean pwCheckOk;
	
	@FXML
	private void initialize() {
		
	}

	public void setLevellist(ObservableList<SalesLevel> levellist) {
		this.levellist = levellist;
		System.out.println(levellist);
		levelCb.setItems(levellist);
	}

	public void setSales(Sales sales) {
		this.sales = sales;
		System.out.println(sales);
		codeLabel.setText(sales.getSaleCode()+"");
		nameTf.setText(sales.getSaleName());
		idLabel.setText(sales.getSaleId());	
		levelCb.setValue(new SalesLevel(sales.getSaleLevel()));
		telTf.setText(sales.getSaleTel());
		String addrs =  sales.getSaleAddr();
		addrZipTf.setText(addrs.substring(addrs.indexOf("[")+1, addrs.indexOf("]")));
		addrTf.setText(addrs.substring(addrs.indexOf("]")+1, addrs.length()));		
	}

	public Sales getSales() {
		return sales;
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
			sales.setSaleName(nameTf.getText());
			
			String pwVal =pwTf.getText();
			if(pwCheckOk || pwVal.equals("")) {
				sales.setSalePw(pwTf.getText());
			}
			
			sales.setSaleTel(telTf.getText());
			sales.setSaleAddr("["+ addrZipTf.getText() + "]" + addrTf.getText());
			sales.setSaleCode(Integer.parseInt(codeLabel.getText()));
			sales.setSaleLevel(levelCb.getValue().getSalLevel());
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
			tfUtil.regexTfComfirmSaleName(nameTf);
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
}
