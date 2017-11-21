package kr.or.dgit.SaleManagement.controller;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import kr.or.dgit.SaleManagement.dto.BigClass;
import kr.or.dgit.SaleManagement.dto.SmallClass;
import kr.or.dgit.SaleManagement.service.BigClassService;
import kr.or.dgit.SaleManagement.service.SmallClassService;

public class AddClassDialogController {
	@FXML
	private TextField bigTf;
	
	@FXML
	private TextField smallTf;
	
	@FXML
	private ComboBox<BigClass> bigCb;
	
	private static BigClassService bigService;
	private static SmallClassService smallService;
	
	private ObservableList<BigClass> biglist = FXCollections.observableArrayList();
	
	private boolean okClicked = false;

	
	@FXML
	private void initialize() {
		bigService = BigClassService.getInstance();
		resetBigCb();
	}



	private void resetBigCb() {
		smallService = SmallClassService.getInstance();
		
		
		List<BigClass> blist = bigService.findAll();
		for(BigClass big : blist) {	
			biglist.add(big);
			//공백 콤보박스에 공백 들어가서 크기가 커지니까 trim()으로 공백 없앤뒤 입력해야함
		}
		bigCb.setItems(biglist);
	}
	
	
	
	@FXML
    private void bigAddAction() {
		
		String name = bigTf.getText();
		
		
		
		BigClass big = new BigClass();
		int maxCode = bigService.findMaxCode();
		
		big.setBigClass(maxCode+10);
		big.setBigName(name);
		
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(null);
		alert.setHeaderText(null);
		alert.setContentText(big.toString()+"추가되었습니다.");
		alert.showAndWait();
				
		bigService.insertbigClass(big);
		
		resetBigCb();
		okClicked = true;	
		
	}
	
	@FXML
    private void smallAddAction() {
		int big = bigCb.getValue().getBigClass();
		String name = smallTf.getText();
		
		SmallClass small = new SmallClass();
		int maxCode = smallService.findMaxCode();
		
		
		small.setsBigClass(big);
		small.setSmallName(name);
		small.setSmallClass(maxCode+1);
		
		smallService.insertSmallClass(small);
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(null);
		alert.setHeaderText(null);
		alert.setContentText(name + "소분류추가");
		alert.showAndWait();
		
		okClicked = true;	
	}
	
	public boolean isOkClicked() {
		 return okClicked;
	}
	
}
