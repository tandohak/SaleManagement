package kr.or.dgit.SaleManagement.controller;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import kr.or.dgit.SaleManagement.dto.BigClass;
import kr.or.dgit.SaleManagement.dto.Product;
import kr.or.dgit.SaleManagement.dto.SalesLevel;
import kr.or.dgit.SaleManagement.dto.SmallClass;
import kr.or.dgit.SaleManagement.service.BigClassService;
import kr.or.dgit.SaleManagement.service.ProductService;
import kr.or.dgit.SaleManagement.service.SmallClassService;
import kr.or.dgit.SaleManagement.util.TextFieldUtil;

public class ProductDialogController {
	@FXML private TextField nameTf;

	@FXML
	private ComboBox<String> admitCb;
	
	@FXML
	private TextField costTf;
	
	@FXML
	private TextField priceTf;
	@FXML
	private TextField accTf;
	@FXML
	private ComboBox<BigClass> bigCb;
	
	@FXML
	private ComboBox<SmallClass> smallCb;
	
	@FXML
	private TextField codeTf;
	
	private ObservableList<Product> levellist = FXCollections.observableArrayList();
	
	private Stage dialogStage;
	private Product pdt;
	private boolean okClicked = false;
	private TextFieldUtil tfUtil = new TextFieldUtil();
	
	public void setLevellist(ObservableList<Product> levellist) {
		this.levellist = levellist;	
		System.out.println(levellist);
		
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	private ObservableList<BigClass> biglist = FXCollections.observableArrayList();
	private ObservableList<SmallClass> smalllist = FXCollections.observableArrayList();
	private static ProductService pdtService;
	private static BigClassService bigService;
	private static SmallClassService smallService;
	private ObservableList<String> abminlist = FXCollections.observableArrayList();
	
	public void setProduct(Product pdt) {
		this.pdt = pdt;
		
		abminlist.add("true");
		abminlist.add("false");
		
		smallService = SmallClassService.getInstance();
		bigService = BigClassService.getInstance();
		SmallClass s = new SmallClass();
		
		s.setSmallClass(pdt.getPdtClass());
		
		s = smallService.findBySmallClass(s);
		
		BigClass b = new BigClass(s.getsBigClass());
		
		
		b = bigService.findBigClassByBigClass(b);
		
		bigService = BigClassService.getInstance();
		List<BigClass> blist = bigService.findAll();
		for(BigClass big : blist) {	
			biglist.add(big);
			//공백 콤보박스에 공백 들어가서 크기가 커지니까 trim()으로 공백 없앤뒤 입력해야함
		}
		
		bigCb.setItems(biglist);
		admitCb.setItems(abminlist);
		codeTf.setText(pdt.getPdtCode()+"");
		nameTf.setText(pdt.getPdtName());
		costTf.setText(pdt.getPdtCost()+"");
		priceTf.setText(pdt.getPdtPrice()+"");
		bigCb.setValue(b);
		smallCb.setValue(s);
		admitCb.setValue(pdt.getPdtAdmit());
		accTf.setText(pdt.getAccCode()+"");
		
		codeTf.setDisable(true);
		
	}

	public boolean isOkClicked() {
		 return okClicked;
	}

	public Product getProduct() {
		return pdt;
	}
	
	@FXML
    private void handleOk() {
        if (tfComfrimField()) {
        	//pdt.setAccCode(Integer.parseInt(accTf.getText()));
        	pdt.setPdtName(nameTf.getText());
        	pdt.setPdtClass(smallCb.getValue().getSmallClass());
			pdt.setPdtAdmit(admitCb.getValue().toString());
			pdt.setPdtCost(Integer.parseInt(costTf.getText().trim()));
			pdt.setPdtPrice(Integer.parseInt(priceTf.getText().trim()));
			//pdt.setAccCode(21722051);
			
			okClicked = true;		
			dialogStage.close();
        }
    }
	
	@FXML
	public void comboboxChange() {

		int big = bigCb.getValue().getBigClass();
		SmallClass sm = new SmallClass();
		sm.setsBigClass(big);
		
		smalllist = FXCollections.observableArrayList();
		
		smallService = SmallClassService.getInstance();
		List<SmallClass> slist = smallService.findByBigClass(sm);
		for(SmallClass small: slist) {
			smalllist.add(small);
		}
		smallCb.setItems(smalllist);
	}
	
	@FXML
	private void handleCancel() {
	      dialogStage.close();
	}
	
	private Boolean tfComfrimField() {
		try {
			tfUtil.regexTfComfirmAccProductName(nameTf);
			tfUtil.cbComfrim(bigCb);
			tfUtil.cbComfrim(smallCb);
			tfUtil.cbComfrim(admitCb);
			tfUtil.regexTfComfirmNumber(priceTf);
			tfUtil.regexTfComfirmNumber(costTf);
			if(!tfUtil.regexTfComfirmCost(priceTf, costTf)) {
				return false;
			}
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
}
