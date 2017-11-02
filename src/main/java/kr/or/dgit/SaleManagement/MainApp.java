package kr.or.dgit.SaleManagement;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.dgit.SaleManagement.view.RootLayoutController;

public class MainApp extends Application {
	private Stage primaryStage;
	private Parent rootLayout;
	private RootLayoutController controller;
	
	final Delta dragDelta = new Delta();
	   
	@Override
	public void start( Stage primaryStage) throws IOException {
		primaryStage.initStyle(StageStyle.UNDECORATED);
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("영업관리 프로그램");
		initRootLayout();
		rootLayout.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				dragDelta.x = primaryStage.getX() - event.getScreenX() ;
				dragDelta.y = primaryStage.getY() - event.getScreenY() ;
			}
		});
		
		rootLayout.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {	
				if(event.getButton() == MouseButton.PRIMARY) {
				primaryStage.setX(event.getScreenX() + dragDelta.x);
				primaryStage.setY(event.getScreenY() + dragDelta.y);
				}
			}
		});
		
		controller = new RootLayoutController();
		controller.setMainApp(this);
		
//		fontLoad();
		
		
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
	
	class Delta { double x, y; } 
}
