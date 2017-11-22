package kr.or.dgit.SaleManagement.controller.dialogController;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import kr.or.dgit.SaleManagement.dto.Sales;
import kr.or.dgit.SaleManagement.service.SalesService;

public class SalesSearchDialogController {
	@FXML private BorderPane pane;
	@FXML private TextField searchAllTf;

	@FXML private TableColumn<Sales, Integer> codeTc;
	@FXML private TableColumn<Sales, String> telTc;
	@FXML private TableColumn<Sales, String> nameTc;
	@FXML private TableColumn<Sales, String> levelTc;
	@FXML private TableColumn<Sales, String> leaveTc;
	@FXML private TableColumn<Sales, Boolean> chckTc;
	@FXML private TableColumn<Sales, String> addrTc;
	
	
	@FXML private TableView<Sales> saleTable;
	
	@FXML private CheckBox saleCheck;

	private ObservableList<Sales> myList = FXCollections.observableArrayList();
	
	private SalesService saleSerivce;
	
	private Stage dialogStage;
	private Sales getItem;
	private boolean okClicked = false;
	
	
	public boolean isOkClicked() {
		 return okClicked;
	}
	
	public Sales getItem() {
		return getItem;
	}

	public void setItem(Sales getItem) {
		this.getItem = getItem;
	}

	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	@FXML
	private void initialize() {
		Sales leaveSales = new Sales();
		leaveSales.setSaleLeave("true");
		
		saleSerivce = SalesService.getInstance();
		List<Sales> lists = saleSerivce.findSalesByLeave(leaveSales);
		for(Sales sale : lists) {
			myList.add(sale);
		}
		
		codeTc.setCellValueFactory(cellData -> cellData.getValue().getSaleCodeProperty().asObject());
		nameTc.setCellValueFactory(cellData -> cellData.getValue().getSaleNameProperty());
		telTc.setCellValueFactory(cellData -> cellData.getValue().getSaleTelProperty());
		levelTc.setCellValueFactory(cellData -> cellData.getValue().getSaleLevelProperty());
		leaveTc.setCellValueFactory(cellData -> cellData.getValue().getSaleLeaveProperty());
		addrTc.setCellValueFactory(cellData -> cellData.getValue().getSaleAddrProperty());;

		saleTable.setItems(myList);
	}
	
	private void setTableModel(List<Sales> lists) {
		myList = FXCollections.observableArrayList();
		for(Sales sales : lists) {
			myList.add(sales);
		}
		saleTable.setItems(myList);
	}
	
	
	@FXML
	private void searchAction() {
		Sales findItem = new Sales();
		List<Sales> lists;
		findItem.setSaleName("%" + searchAllTf.getText() + "%");
			
		findItem.setSaleLeave("true");
		lists = saleSerivce.findSalesSearch(findItem);
		setTableModel(lists);;
	}
	
	private void checkAlert(boolean isOk,String pwck) throws Exception {
		if(!isOk) {
			throw new Exception(pwck);
		}
	}
	
	@FXML
	private void handleOk() {
		Sales selectItem = saleTable.getSelectionModel().getSelectedItem();
		getItem = selectItem;

		try {
			checkAlert(selectItem==null ? false : true,"사원을 선택 해주세요.");
		}catch(Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
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
