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
	
	
	@FXML
	private void initialize() {
		bigService = BigClassService.getInstance();
		List<BigClass> blist = bigService.findAll();
		
		
		for(BigClass big : blist) {	
			biglist.add(big);
			//공백 콤보박스에 공백 들어가서 크기가 커지니까 trim()으로 공백 없앤뒤 입력해야함
		}
		bigCb.setItems(biglist);
	}
	
	@FXML
    private void bigAddAction() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(null);
		alert.setHeaderText(null);
		alert.setContentText("대분류추가");
		alert.showAndWait();

	}
	
	@FXML
    private void smallAddAction() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(null);
		alert.setHeaderText(null);
		alert.setContentText("소분류추가");
		alert.showAndWait();
	}
}
