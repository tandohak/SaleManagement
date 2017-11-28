package kr.or.dgit.SaleManagement.controller.state;

import java.util.Comparator;
import java.util.List;

import com.sun.javafx.scene.control.skin.TableColumnHeader;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import kr.or.dgit.SaleManagement.dto.Product;
import kr.or.dgit.SaleManagement.dto.ProductState;
import kr.or.dgit.SaleManagement.service.ProductStateService;

public class BigClassStateController {
	@FXML private TableColumn<ProductState, Integer> no;
	@FXML private TableColumn<ProductState, String> pdtName;
	@FXML private TableColumn<ProductState, String> pdtAmount;
	@FXML private TableColumn<ProductState, String> pdtPrice;
	@FXML private TableColumn<ProductState, String> pdtCost;
	@FXML private TableColumn<ProductState, String> pdtDisprice;
	@FXML private TableColumn<ProductState, String> pdtMargin;
	@FXML private TableColumn<ProductState, String> pdtProfit;
	@FXML private TableView<ProductState> viewTable;
	
	@FXML private PieChart pieChart;
	private SortedList<ProductState> sortedList;
	// 테이블용 옵저버리스트
	private ObservableList<ProductState> myList = FXCollections.observableArrayList();
	
	// 차트용 옵저버리스트
	private ObservableList<String> xAxisName = FXCollections.observableArrayList();
	private ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
	
	private CategoryAxis xAxis = new CategoryAxis();
	
	private ProductStateService service;
	
	@FXML
	private void initialize() {	
		service = ProductStateService.getInstance();
		
		List<ProductState> pdtStateLists = service.findBigClassStateAll();
		
		for(ProductState pdtState : pdtStateLists) {
			myList.add(pdtState);			
		}
		
		no.setCellValueFactory(cellData -> cellData.getValue().getNoProperty().asObject());
		pdtName.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		pdtAmount.setCellValueFactory(cellData -> cellData.getValue().getFormatAmount());
		pdtPrice.setCellValueFactory(cellData -> cellData.getValue().getFormatPriceAll());
		pdtCost.setCellValueFactory(cellData -> cellData.getValue().getFormatCostAll());
		pdtDisprice.setCellValueFactory(cellData -> cellData.getValue().getFormatDispriceAll());
		pdtMargin.setCellValueFactory(cellData -> cellData.getValue().getFormatMargin());
		pdtProfit.setCellValueFactory(cellData -> cellData.getValue().getFormatProfit());

		for(ProductState pdtState : myList) {
			pieChartData.add(new Data(pdtState.getName().trim(), pdtState.getPriceAll()));
		}
		
		pieChart.setData(pieChartData);
		
		sortedList= myList.sorted(new Comparator<ProductState>() {
			@Override
			public int compare(ProductState o1, ProductState o2) {
				if(o1.getPriceAll() < o2.getPriceAll()){
					return 1;
				}else if(o1.getPriceAll() > o2.getPriceAll()){
					return -1;
				}else {
					return 0;
				}
			}
		});
		
		setRank(sortedList);
		viewTable.setItems(sortedList);
	}
	
	@FXML 
	private void tableSortEvent(MouseEvent mouse) {
		
		TableColumnHeader columnHeader = (TableColumnHeader)mouse.getTarget();
       
	    switch (columnHeader.getId()) {
		case "pdtAmount":
			sortedList = myList.sorted(new Comparator<ProductState>() {
				@Override
				public int compare(ProductState o1, ProductState o2) {
					if(o1.getAmount() < o2.getAmount()){
						return 1;
					}else if(o1.getAmount() > o2.getAmount()){
						return -1;
					}else {
						return 0;
					}
				}
			});
			
			setRank(sortedList);
			
			viewTable.setItems(sortedList);
			viewTable.refresh();
			break;
		case "pdtPrice":
			sortedList = myList.sorted(new Comparator<ProductState>() {
				@Override
				public int compare(ProductState o1, ProductState o2) {
					if(o1.getPriceAll() < o2.getPriceAll()){
						return 1;
					}else if(o1.getPriceAll() > o2.getPriceAll()){
						return -1;
					}else {
						return 0;
					}
				}
			});
			
			setRank(sortedList);
			
			viewTable.setItems(sortedList);
			viewTable.refresh();
			break;
		case "pdtCost":
			sortedList = myList.sorted(new Comparator<ProductState>() {
				@Override
				public int compare(ProductState o1, ProductState o2) {
					if(o1.getCostAll() < o2.getCostAll()){
						return 1;
					}else if(o1.getCostAll() > o2.getCostAll()){
						return -1;
					}else {
						return 0;
					}
				}
			});
			
			setRank(sortedList);
			
			viewTable.setItems(sortedList);
			viewTable.refresh();
			break;
		case "pdtDisprice":
			sortedList = myList.sorted(new Comparator<ProductState>() {
				@Override
				public int compare(ProductState o1, ProductState o2) {
					if(o1.getDispriceAll() < o2.getDispriceAll()){
						return 1;
					}else if(o1.getDispriceAll() > o2.getDispriceAll()){
						return -1;
					}else {
						return 0;
					}
				}
			});
			
			setRank(sortedList);
			
			viewTable.setItems(sortedList);
			viewTable.refresh();
			break;
		case "pdtMargin":
			sortedList = myList.sorted(new Comparator<ProductState>() {
				@Override
				public int compare(ProductState o1, ProductState o2) {
					if(o1.getMargin() < o2.getMargin()){
						return 1;
					}else if(o1.getMargin() > o2.getMargin()){
						return -1;
					}else {
						return 0;
					}
				}
			});
			
			setRank(sortedList);
			
			viewTable.setItems(sortedList);
			viewTable.refresh();
			break;
		case "pdtProfit":
			sortedList = myList.sorted(new Comparator<ProductState>() {
				@Override
				public int compare(ProductState o1, ProductState o2) {
					if(o1.getProfit() < o2.getProfit()){
						return 1;
					}else if(o1.getProfit() > o2.getProfit()){
						return -1;
					}else {
						return 0;
					}
				}
			});
			
			setRank(sortedList);
			
			viewTable.setItems(sortedList);
			viewTable.refresh();
			break;
		}
	}
	
	private void setRank(SortedList<ProductState> sortedList) {
		int rank = 1;
		for(ProductState pdtState : sortedList) {
			pdtState.setNo(rank);
			rank++;
		}
	}
}
