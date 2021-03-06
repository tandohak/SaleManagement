package kr.or.dgit.SaleManagement;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainApp extends Application {
	private Stage primaryStage;
	private Parent rootLayout;
	private Boolean resizebottom = false;
	 private double dx;
	private double dy;

	final Delta dragDelta = new Delta();
	   
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	@Override
	public void start( Stage primaryStage) throws IOException {
		primaryStage.initStyle(StageStyle.UNDECORATED);
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("영업관리 프로그램");
		initRootLayout();
		
//		setWindowMove(primaryStage);
		
//		windowResize(primaryStage);

//		fontLoad();
	}

	/*private void windowResize(Stage primaryStage) {
		rootLayout.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.getX() > primaryStage.getX() -10 
				&& event.getX() < primaryStage.getWidth() + 10
		        && event.getY() > primaryStage.getHeight() - 10
		        && event.getY() < primaryStage.getHeight() + 10) {
		                resizebottom = true;
		                dx = primaryStage.getWidth() - event.getX();
		                dy = primaryStage.getHeight() - event.getY();
		         } else {
		                resizebottom = false;
		                dragDelta.x = event.getSceneX();
		                dragDelta.y = event.getSceneY();admin
		         }
			}
			
		});
		
		rootLayout.setOnMouseDragged(new EventHandler<MouseEvent>() {
		        public void handle(MouseEvent event) {
		            if (resizebottom == false) {
		            	primaryStage.setX(event.getScreenX() - dragDelta.x);
		            	primaryStage.setY(event.getScreenY() - dragDelta.y);
		            } else {
		            	primaryStage.setWidth(event.getX() + dx);
		            	primaryStage.setHeight(event.getY() + dy);
		            }
		        }
		});
	}*/

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
