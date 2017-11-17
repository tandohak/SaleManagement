package kr.or.dgit.SaleManagement.controller;

import java.awt.Event;
import java.util.List;
import java.util.Observer;

import javax.swing.text.StyledEditorKit.BoldAction;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import kr.or.dgit.SaleManagement.ProductTestMain;
import kr.or.dgit.SaleManagement.dto.BigClass;
import kr.or.dgit.SaleManagement.dto.Product;
import kr.or.dgit.SaleManagement.service.BigClassService;
import kr.or.dgit.SaleManagement.service.ProductService;

public class ProductController {
	
	@FXML
	private AnchorPane Node;
	
	@FXML
	private TextField searchTf;
	
	@FXML
	private Button searchBtn;
	
	@FXML
	private TextField nameTf;
	
	@FXML
	private TextField codeTf;
	
	@FXML
	private ComboBox admitCb;
	
	@FXML
	private TextField costTf;
	
	@FXML
	private TextField priceTf;
	
	@FXML
	private ComboBox<String> bigCb;
	
	@FXML
	private ComboBox smallCb;
	
	@FXML
	private Button classAddBtn;
	
	@FXML
	private Button deleteBtn;
	
	@FXML
	private Button changeBtn;
	
	@FXML
	private TableView<Product> pdtTable;
	
	@FXML
	private CheckBox pdtCheck;
	@FXML
	private TableColumn<Product, Boolean> chckTc;
	
	@FXML
	private TableColumn<Product, Integer> codeTc;
	
	@FXML
	private TableColumn<Product, String> nameTc;
	
	@FXML
	private TableColumn<Product, Integer> accTc;
	
	@FXML
	private TableColumn<Product, Integer> costTc;
	
	@FXML
	private TableColumn<Product, Integer> priceTc;
	
	@FXML
	private TableColumn<Product, String> admitTc;
	
	private static ProductService pdtService;
	private static BigClassService bigService;
	
	
	private ObservableList<Product> myList = FXCollections.observableArrayList();
	
	private ObservableList<String> biglist = FXCollections.observableArrayList();

	@FXML
	private void initialize() {
		pdtService = ProductService.getInstance();
		List<Product> lists = pdtService.findAll();
		for(Product pdt : lists) {
			myList.add(pdt);
		}
		
		bigService = BigClassService.getInstance();
		List<BigClass> blist = bigService.findAll();
		
		for(BigClass big : blist) {	
			biglist.add(big.getBigName().trim());
			//공백 콤보박스에 공백 들어가서 크기가 커지니까 trim()으로 공백 없앤뒤 입력해야함
		}
		
		/* ********************* *
		 * 테이블 cell안 체크박스 삽입   *
		 * ********************* */
		chckTc.setCellFactory(new Callback<TableColumn<Product,Boolean>,TableCell<Product,Boolean>>(){
	        @Override public
	        TableCell<Product,Boolean> call( TableColumn<Product,Boolean> p ){
	           CheckBoxTableCell<Product, Boolean> checkBoxTbC = new CheckBoxTableCell<>(); 
	           checkBoxTbC.setSelectedStateCallback(new Callback<Integer, ObservableValue<Boolean>>() {
	        	   //checkbox에 callback 함수 달기 
				@Override
				public ObservableValue<Boolean> call(Integer index) {
					
					return pdtTable.getItems().get(index).selectedProperty();
					 //필드의 변동을 확인하기 위해 table에서 item을 받아와 selectedProperty() 함수를 받아 리턴한다.
					 // ObservableValue<T>를 리턴하여 ui에서 변동된 사항을 감지한다.
				}
			});
	           
	           return checkBoxTbC;
	      
	           }
	        });		
		
		/**
		 * 전체 선택 체크 체인지 리스너
		 * */
		pdtCheck.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {
					for(Product pdt : myList) {
						pdt.setCheckedBox(true);
					}
				}else {
					for(Product pdt : myList) {
						pdt.setCheckedBox(false);
					}
				}				
			}
			
		});
		
		codeTc.setCellValueFactory(cellData -> cellData.getValue().getAccCodeProperty().asObject());
		nameTc.setCellValueFactory(cellData -> cellData.getValue().getPdtNameProperty());
		accTc.setCellValueFactory(cellData -> cellData.getValue().getAccCodeProperty().asObject());
		costTc.setCellValueFactory(cellData -> cellData.getValue().getPdtCostProperty().asObject());
		priceTc.setCellValueFactory(cellData -> cellData.getValue().getPdtPriceProperty().asObject());
		admitTc.setCellValueFactory(cellData -> cellData.getValue().getPdtAdmitProperty());
		
		pdtTable.setItems(myList);
		bigCb.setItems(biglist);
		
		
	}
	
	public ProductController() {}
	
	@FXML
	private void deleteSelectedCell(ActionEvent event) {
		
		for(int i=0; myList.size()>i; i++) {
			Product pdt = myList.get(i);
			System.out.println(pdt);
			if(pdt.getCheckedBox()) {
				 myList.remove(pdt);
				 pdtService.deleteProduct(pdt);
				 i = 0;
			};
		}
	}
	
	
	private ProductTestMain mainApp;
	
	public void setMainApp(ProductTestMain mainApp) {
	        this.mainApp = mainApp;
	        
	   }

 
	
	
	
	
}
