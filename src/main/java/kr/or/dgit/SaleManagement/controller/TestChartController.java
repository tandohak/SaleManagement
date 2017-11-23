package kr.or.dgit.SaleManagement.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.BorderPane;

public class TestChartController {
	@FXML private BorderPane pane;
	@FXML private PieChart testChart = new PieChart();
	
	
	private ObservableList<PieChart.Data> testObserver = FXCollections.observableArrayList();
	
	@FXML
	private void initialize() {
		testObserver.add(new PieChart.Data("Iphone 5S", 13));
		testObserver.add(new PieChart.Data("Samsung Grand", 25));
		testObserver.add(new PieChart.Data("MOTO G", 10));
		testObserver.add(new PieChart.Data("Nokia Lumia", 22));
		
		testChart.setData(testObserver);
	}
}
