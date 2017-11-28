package kr.or.dgit.SaleManagement;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import kr.or.dgit.SaleManagement.controller.ProductController;
import kr.or.dgit.SaleManagement.controller.StateController;
import kr.or.dgit.SaleManagement.service.ProductService;

public class RecordTestMain extends Application {
	private Stage primaryStage;
	private Parent rootLayout;
	private StateController stateController;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("영업관리 프로그램");
		initRootLayout();
	}

//	private static ProductService pdtService;

	public static void main(String[] args) {
		launch(args);
	}
	

	private void initRootLayout() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(RecordTestMain.class.getResource("view/management/StateView.fxml"));
		rootLayout = (BorderPane)loader.load();

		Scene scene = new Scene(rootLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
