package kr.or.dgit.SaleManagement;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import kr.or.dgit.SaleManagement.controller.TestChartController;

public class ChartTestMain extends Application {
	private Stage primaryStage;
	private Parent rootLayout;
	private TestChartController controller;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("영업관리 프로그램");
		initRootLayout();
	}

	public static void main(String[] args) {
		launch(args);
	}
	

	private void initRootLayout() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ChartTestMain.class.getResource("view/management/TestChart.fxml"));
		rootLayout = (BorderPane)loader.load();

		Scene scene = new Scene(rootLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
