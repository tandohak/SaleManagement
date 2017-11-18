package kr.or.dgit.SaleManagement.controller;

import java.awt.Checkbox;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.util.Callback;
import kr.or.dgit.SaleManagement.dto.Product;
import kr.or.dgit.SaleManagement.dto.Sales;
import kr.or.dgit.SaleManagement.service.SalesService;

public class SalesController {
	@FXML private TextField searchAllTf;

	@FXML private TextField nameTf;	
	@FXML private TextField codeTf;	
	@FXML private TextField idTf;	
	@FXML private TextField pwTf;	
	@FXML private TextField pwComfTf;	
	@FXML private TextField picTf;	
	@FXML private TextField addrTf;	
	@FXML private TextField addrZipTf;	
	@FXML private TextField telTf;
	
	@FXML private TableColumn<Sales, Integer> codeTc;
	@FXML private TableColumn<Sales, String> telTc;
	@FXML private TableColumn<Sales, String> nameTc;
	@FXML private TableColumn<Sales, String> levelTc;
	@FXML private TableColumn<Sales, String> leaveTc;
	@FXML private TableColumn<Sales, Boolean> chckTc;
	@FXML private TableColumn<Sales, String> addrTc;
	
	@FXML private ComboBox<String> levelCb;
	@FXML private CheckBox saleCheck;
	
	@FXML private TableView<Sales> saleTable;
	private ObservableList<Sales> myList = FXCollections.observableArrayList();
	
	private ObservableList<String> levellist = FXCollections.observableArrayList();
	
	SalesService saleSerivce;
	
	@FXML
	private void initialize() {
		saleSerivce = SalesService.getInstance();
		List<Sales> lists = saleSerivce.findSaleAll();
		for(Sales sale : lists) {
			myList.add(sale);
		}		
				
		chckTc.setCellFactory(new Callback<TableColumn<Sales,Boolean>, TableCell<Sales,Boolean>>() {
				@Override
				public TableCell<Sales, Boolean> call(TableColumn<Sales, Boolean> param) {
					CheckBoxTableCell<Sales, Boolean> checkBoxTbc = new CheckBoxTableCell<>();
					checkBoxTbc.setSelectedStateCallback(new Callback<Integer, ObservableValue<Boolean>>() {
						
						@Override
						public ObservableValue<Boolean> call(Integer index) {
							
							return saleTable.getItems().get(index).selectedProperty();
						}
					});
					
					return checkBoxTbc;
				}
		});
		
		saleCheck.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {
					for(Sales sale : myList) {
						sale.setCheckedBox(true);
					}
				}else {
					for(Sales sale : myList) {
						sale.setCheckedBox(false);
					}
				}							
			}
			
		});
		
				
		codeTc.setCellValueFactory(cellData -> cellData.getValue().getSaleCodeProperty().asObject());
		nameTc.setCellValueFactory(cellData -> cellData.getValue().getSaleNameProperty());
		telTc.setCellValueFactory(cellData -> cellData.getValue().getSaleTelProperty());
		levelTc.setCellValueFactory(cellData -> cellData.getValue().getSaleLevelProperty());
		leaveTc.setCellValueFactory(cellData -> cellData.getValue().getSaleLeaveProperty());
		addrTc.setCellValueFactory(cellData -> cellData.getValue().getSaleAddrProperty());;
		
		saleTable.setItems(myList);
	}
	
	@FXML
	private void deleteSelectedCell(ActionEvent event) {
		
		for(int i=0; myList.size()>i; i++) {
			Sales Sales = myList.get(i);
			System.out.println(Sales);
			if(Sales.getCheckedBox()) {
				 myList.remove(Sales);
//				 saleSerivce.deleteSales(Sales);
				 i = 0;
			};
		}
	}
}
