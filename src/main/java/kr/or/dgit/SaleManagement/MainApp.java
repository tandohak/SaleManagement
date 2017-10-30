package kr.or.dgit.SaleManagement;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainApp extends Application {
	private Stage primaryStage;
	private Parent rootLayout;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("영업관리 프로그램");
		
//		fontLoad();
		
		initRootLayout();
	}

	private void fontLoad() {
//		Font.loadFont(getClass().getResourceAsStream("view/font/NotoSansCJKkr-Black.otf"),14);		
	}

	private void initRootLayout() throws IOException  {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("view/loginView.fxml"));
		rootLayout = (AnchorPane) loader.load();
		
		Scene scene = new Scene(rootLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
