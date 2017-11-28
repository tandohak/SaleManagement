package kr.or.dgit.SaleManagement.controller.state;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.sun.javafx.scene.control.skin.TableColumnHeader;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import kr.or.dgit.SaleManagement.dto.Product;
import kr.or.dgit.SaleManagement.dto.ProductState;
import kr.or.dgit.SaleManagement.dto.Record;
import kr.or.dgit.SaleManagement.service.ProductStateService;

public class ProductStateController {
	@FXML private TableColumn<ProductState, Integer> no;
	@FXML private TableColumn<ProductState, Integer> pdtCode;
	@FXML private TableColumn<ProductState, String> pdtName;
	@FXML private TableColumn<ProductState, String> pdtAmount;
	@FXML private TableColumn<ProductState, String> pdtPrice;
	@FXML private TableColumn<ProductState, String> pdtCost;
	@FXML private TableColumn<ProductState, String> pdtDisprice;
	@FXML private TableColumn<ProductState, String> pdtMargin;
	@FXML private TableColumn<ProductState, String> pdtProfit;
	@FXML private TableView<ProductState> viewTable;
	
	@FXML private StackedBarChart<String, Integer> stackChart;
	
	// 테이블용 옵저버리스트
	private ObservableList<ProductState> myList = FXCollections.observableArrayList();
	
	// 차트용 옵저버리스트
	private ObservableList<String> xAxisName = FXCollections.observableArrayList();
	private CategoryAxis xAxis = new CategoryAxis();
	
	private ProductStateService service;
	private SortedList<ProductState> sortedList;

	@FXML
	private void initialize() {	
		service = ProductStateService.getInstance();
		
		List<ProductState> pdtStateLists = service.findProductStateAll();
		
		for(ProductState pdtState : pdtStateLists) {
			myList.add(pdtState);			
		}
		
		no.setCellValueFactory(cellData -> cellData.getValue().getNoProperty().asObject());
		pdtCode.setCellValueFactory(cellData -> cellData.getValue().getCodeProperty().asObject());
		pdtName.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		pdtAmount.setCellValueFactory(cellData -> cellData.getValue().getFormatAmount());
		pdtPrice.setCellValueFactory(cellData -> cellData.getValue().getFormatPriceAll());
		pdtCost.setCellValueFactory(cellData -> cellData.getValue().getFormatCostAll());
		pdtDisprice.setCellValueFactory(cellData -> cellData.getValue().getFormatDispriceAll());
		pdtMargin.setCellValueFactory(cellData -> cellData.getValue().getFormatMargin());
		pdtProfit.setCellValueFactory(cellData -> cellData.getValue().getFormatProfit());
		
		int index = 0;
		String[] accountNames = new String[myList.size()];
		for(ProductState name : myList) {
			accountNames[index] = name.getName();
			index++;
		}
		xAxisName.addAll(Arrays.asList(accountNames));
		
		setData(myList, xAxisName);
		
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
	
	public void setData(List<ProductState> standardList, ObservableList<String> xAxisName) {
		int index = 0;
		int[][] chartBar = new int[standardList.size()][3];
		for(ProductState rec : standardList) {
			chartBar[index][0] = rec.getCostAll();
			chartBar[index][1] = rec.getDispriceAll();
			chartBar[index][2] = rec.getProfit();
			index++;
		}
		
		XYChart.Series<String, Integer> seriesCost = new XYChart.Series<>();
		XYChart.Series<String, Integer> seriesPrice = new XYChart.Series<>();
		XYChart.Series<String, Integer> seriesProfit = new XYChart.Series<>();
		
		seriesCost.setName("원가총액");
		seriesPrice.setName("할인총액");
		seriesProfit.setName("총매출이익");
		
		index = 0;
		for(int i=0;i<chartBar.length;i++) {
			seriesCost.getData().add(new XYChart.Data<>(xAxisName.get(i), chartBar[i][0]));
			seriesPrice.getData().add(new XYChart.Data<>(xAxisName.get(i), chartBar[i][1]));
			seriesProfit.getData().add(new XYChart.Data<>(xAxisName.get(i), chartBar[i][2]));
		}
		stackChart.getData().addAll(seriesCost, seriesPrice, seriesProfit);
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
