package kr.or.dgit.SaleManagement;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kr.or.dgit.SaleManagement.view.RootLayoutController;

public class MainApp extends Application {
	private Stage primaryStage;
	private Parent rootLayout;
	private RootLayoutController controller;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("영업관리 프로그램");
		
		controller = new RootLayoutController();
		controller.setMainApp(this);
		
//		fontLoad();
		
		initRootLayout();
	}

	private void fontLoad() {
//		Font.loadFont(getClass().getResourceAsStream("view/font/NotoSansCJKkr-Black.otf"),14);		
	}

	private void initRootLayout() throws IOException  {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
		rootLayout = loader.load();
		
		Scene scene = new Scene(rootLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
