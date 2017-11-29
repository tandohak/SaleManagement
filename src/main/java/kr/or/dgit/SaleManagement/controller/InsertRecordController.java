package kr.or.dgit.SaleManagement.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kr.or.dgit.SaleManagement.MainApp;
import kr.or.dgit.SaleManagement.controller.dialogController.ProductSearchDialog;
import kr.or.dgit.SaleManagement.controller.dialogController.RecordEditDialogController;
import kr.or.dgit.SaleManagement.controller.dialogController.SalesSearchDialogController;
import kr.or.dgit.SaleManagement.dto.Account;
import kr.or.dgit.SaleManagement.dto.AccountLevel;
import kr.or.dgit.SaleManagement.dto.Product;
import kr.or.dgit.SaleManagement.dto.Record;
import kr.or.dgit.SaleManagement.dto.Sales;
import kr.or.dgit.SaleManagement.dto.SalesLevel;
import kr.or.dgit.SaleManagement.dto.SmallClass;
import kr.or.dgit.SaleManagement.service.AccountLevelService;
import kr.or.dgit.SaleManagement.service.AccountService;
import kr.or.dgit.SaleManagement.service.ProductService;
import kr.or.dgit.SaleManagement.service.RecordSerivce;
import kr.or.dgit.SaleManagement.service.SalesLevelService;
import kr.or.dgit.SaleManagement.service.SalesService;
import kr.or.dgit.SaleManagement.service.SmallClassService;
import kr.or.dgit.SaleManagement.util.TextFieldUtil;

public class InsertRecordController {
	@FXML private BorderPane pane;
    @FXML private TextField searchAllTf;
    @FXML private ComboBox<String> optionCb;
    
    @FXML private TableView<Record> recTable;
    @FXML private TableColumn<Record, Integer> noTc;
    @FXML private TableColumn<Record, String> dateTc;
    @FXML private TableColumn<Record, String> accNameTc;
    @FXML private TableColumn<Record, String> pdtNameTc;
    @FXML private TableColumn<Record, Integer> sumPriceTc;
    @FXML private TableColumn<Record, Integer> dispriceTc;
    @FXML private TableColumn<Record, Integer> disrateTc;
    @FXML private TableColumn<Record, Integer> countTc;
    @FXML private TableColumn<Record, String> saleNameTc;
    
    @FXML private DatePicker dateDP;
    @FXML private TextField accTf;
    @FXML private TextField accLevelTf;
    @FXML private TextField saleTf;
    @FXML private TextField saleLevelTf;
    @FXML private TextField pdtTf;
    @FXML private TextField pdtClassTf;
    @FXML private TextField priceTf;
    @FXML private TextField unitPriceTf;
    @FXML private TextField disPriceTf;	
    @FXML private TextField countTf;    
    @FXML private TextField disrateTf;
    @FXML private TextField sumPriceTf;
    @FXML private Button saleSearchBtn;
    
    private TextFieldUtil tfUtil = new TextFieldUtil();
	private ObservableList<Record> myList = FXCollections.observableArrayList();
	
	private SalesService saleService;
	private AccountService accService;
	private ProductService pdtService;
	private SalesLevelService sLevelService;
	private AccountLevelService accLevelService;
    private RecordSerivce recordSerivce;
	
	private Account acc;
	private Product pdt;
	private Sales sales;
	private int no;
	private boolean isUserLogin = false;
	
	
	@FXML
	private void initialize() {
		saleService = SalesService.getInstance();
		accService = AccountService.getInstance();
		pdtService = ProductService.getInstance();
		sLevelService = SalesLevelService.getInstance();
		accLevelService= AccountLevelService.getInstance();
		recordSerivce = RecordSerivce.getInstance();
		
		noTc.setCellValueFactory(cellData -> cellData.getValue().getRecNoProperty().asObject());
		accNameTc.setCellValueFactory(cellData -> cellData.getValue().getAccNameProperty());
		pdtNameTc.setCellValueFactory(cellData -> cellData.getValue().getPdtNameProperty());
		sumPriceTc.setCellValueFactory(cellData -> cellData.getValue().getSumPriceProperty().asObject());
		dispriceTc.setCellValueFactory(cellData -> cellData.getValue().getRecDispriceProperty().asObject());;
		disrateTc.setCellValueFactory(cellData -> cellData.getValue().getRecDisrateProperty().asObject());;
		countTc.setCellValueFactory(cellData -> cellData.getValue().getRecCountProperty().asObject());;
		saleNameTc.setCellValueFactory(cellData -> cellData.getValue().getSaleNameProperty());		
				
		dateTc.setCellValueFactory(cellData -> {
              SimpleStringProperty property = new SimpleStringProperty();
              DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
              property.setValue(formatter.format(cellData.getValue().getRecDate()));
              return property;
           });
		LocalDate nowDate = LocalDate.now();
		dateDP.setValue(nowDate);
		dateDP.valueProperty().addListener(new ChangeListener<LocalDate>() {

			@Override
			public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue,
					LocalDate newValue) {
				if(nowDate.isAfter(newValue)) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle(null);
					alert.setHeaderText(null);
					alert.setContentText("이전 날짜를 선택 하였습니다.");
					alert.showAndWait();
					newValue = oldValue;
				}
			}
		});		
		
		recTable.setItems(myList);
		
		recTable.setRowFactory(tv -> {
			 TableRow<Record> row = new TableRow<>();
			    row.setOnMouseClicked(event -> {
			        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
			             && event.getClickCount() == 2) {

			        	Record clickedRow = row.getItem();
			            showEditDialog(clickedRow);
			        }
			    });
			    return row ;
		});
	}
	
	@FXML
	private void searchAccountAction() {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/dialog/AccountSearchDialog.fxml"));
        BorderPane page;
		try {
			page = (BorderPane) loader.load();
			
			Stage dialogStage = new Stage();
	        dialogStage.setTitle("Product");
	        dialogStage.initModality(Modality.WINDOW_MODAL);		        
	        dialogStage.initOwner(pane.getScene().getWindow());
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
	       
	        ProductSearchAccountController controller = loader.getController(); 
	        controller.setDialogStage(dialogStage);	        
	        dialogStage.showAndWait();
	        
	        if(controller.isOkClicked()) {
	        	acc = controller.getGetAcc();
	        	accTf.setText(acc.getAccName());	
	        	accLevelTf.setText(acc.getAccLevel());
	        	
	        	SalesLevel saleDis = sLevelService.findOneSalesLevel(new SalesLevel(saleLevelTf.getText()));	        	
	        	AccountLevel accDis = accLevelService.findOneAccount(new AccountLevel(accLevelTf.getText()));	
	        	
	        	if(saleLevelTf.getText().equals("")){
	        		disrateTf.setText(accDis.getAccDisrate() + "%");
	        	}else{
	        		int sumDisrate = saleDis.getSalDisrate() + accDis.getAccDisrate(); 
		        	disrateTf.setText(sumDisrate + "%");
	        	}

	    	    pdtTf.setText("");
	    	    pdtClassTf.setText("");	    	    
	    	    unitPriceTf.setText("");
	    	    priceTf.setText("");
	    	    disPriceTf.setText("");
	    	    sumPriceTf.setText("");
	    	    countTf.setText("1");
	        }

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@FXML
	private void searchSalesAction() {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/dialog/SalesSearchDialog.fxml"));
        BorderPane page;
		try {
			page = (BorderPane) loader.load();
			
			Stage dialogStage = new Stage();
	        dialogStage.setTitle("Sales");
	        dialogStage.initModality(Modality.WINDOW_MODAL);		        
	        dialogStage.initOwner(pane.getScene().getWindow());
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
	        
	       
	        SalesSearchDialogController controller = loader.getController(); 
	        controller.setDialogStage(dialogStage);	 	        
	        dialogStage.showAndWait();

	       
	        
	        if(controller.isOkClicked()) {
	        	sales = controller.getItem();
	        	saleTf.setText(sales.getSaleName());
	        	saleLevelTf.setText(sales.getSaleLevel());
	        	
	        	SalesLevel saleDis = sLevelService.findOneSalesLevel(new SalesLevel(saleLevelTf.getText()));	        	
	        	AccountLevel accDis = accLevelService.findOneAccount(new AccountLevel(accLevelTf.getText()));	        	
	        	
	        	int sumDisrate = 0;
	        	
	        	if(accLevelTf.getText().equals("")){
	        		disrateTf.setText(saleDis.getSalDisrate() + "%");
	        	}else{
	        		sumDisrate = saleDis.getSalDisrate() + accDis.getAccDisrate(); 
		        	disrateTf.setText(sumDisrate + "%");	
	        	}
	        	
	        	if(!pdtTf.getText().equals("")) {      	
		        	int dis = sumDisrate;
		        	int unitDisPrice = (pdt.getPdtPrice()/100)*dis;
		        	int unitPrice = pdt.getPdtPrice() - unitDisPrice;
		        	unitPriceTf.setText(unitPrice+"");	
		    	    disPriceTf.setText(unitDisPrice+"");
	        	
		    	    refrashSumPrice(unitPrice);
	        	}
	        }

		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}
	
	public void setSaleUserSetting(Sales saleUser) {
		sales = saleUser;
		saleTf.setText(saleUser.getSaleName());
		saleSearchBtn.setOnAction(null);
		saleTf.setOnMouseClicked(event -> {
			saleMosueClickAlert();
		});
		
		saleSearchBtn.setOnMouseClicked(event -> {
			saleMosueClickAlert();
		});
		saleLevelTf.setText(saleUser.getSaleLevel());
		SalesLevel saleDis = sLevelService.findOneSalesLevel(new SalesLevel(saleUser.getSaleLevel()));	
		disrateTf.setText(saleDis.getSalDisrate()+"%");
		isUserLogin = true;
		
	}

	private void saleMosueClickAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(null);
		alert.setHeaderText(null);
		alert.setContentText("사원 검색은 관리자만 가능합니다.");
		alert.showAndWait();
	}
	
	@FXML
	private void searchProductAction() {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/dialog/ProductSearchDialog.fxml"));
        BorderPane page;
		try {
			page = (BorderPane) loader.load();
			
			Stage dialogStage = new Stage();
	        dialogStage.setTitle("Product");
	        dialogStage.initModality(Modality.WINDOW_MODAL);		        
	        dialogStage.initOwner(pane.getScene().getWindow());
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
	        System.out.println(acc);
	        ProductSearchDialog controller = loader.getController(); 
	        controller.setAccounnt(acc);
	        controller.setDialogStage(dialogStage);	      
	   
	        dialogStage.showAndWait();

	        if(controller.isOkClicked()) {
	        	pdt = controller.getItem();
	        	SmallClassService sClassService = SmallClassService.getInstance();
	        	pdtTf.setText(pdt.getPdtName());
	        	SmallClass sClass = sClassService.findBySmallClass(new SmallClass(pdt.getPdtClass()));
	        	pdtClassTf.setText(sClass.getSmallName());
	        	priceTf.setText(pdt.getPdtPrice()+"");
	        	
	        	int dis = Integer.parseInt(disrateTf.getText().replace("%", ""));
	        	int unitDisPrice = (pdt.getPdtPrice()/100)*dis;
	        	int unitPrice = pdt.getPdtPrice() - unitDisPrice;
	        	unitPriceTf.setText(unitPrice+"");	
	    	    disPriceTf.setText(unitDisPrice+"");
	    	    
	    	    refrashSumPrice(unitPrice);
	        }

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@FXML
	private void changCountNumAction(KeyEvent event) {	
		if(countTf.getText().equals("")){
			
		}
		int count = Integer.parseInt(countTf.getText());
		int unitPrice = Integer.parseInt(unitPriceTf.getText());
	    int sumPrice = unitPrice*count;
	    sumPriceTf.setText(sumPrice+"");
	}
	
	private void refrashSumPrice(int unitPrice) {
		if(!countTf.getText().equals("")) {
			int count = Integer.parseInt(countTf.getText().replace("%", ""));
			int sumPrice = unitPrice*count;
			sumPriceTf.setText(sumPrice+"");
		}else {
			int sumPrice = unitPrice*1;
			sumPriceTf.setText(sumPrice+"");
		}
	}
	
	@FXML
	private void insertRecordAction(){				
		 for(Record rec : myList) {	    
	    	 rec.setRecNo(recordSerivce.findMaxCode()+1);
	    	 recordSerivce.insertRecord(rec);
	     }
		 myList.clear();
	     recTable.refresh();
	}
	
	@FXML
	private void deleteSelectedAction() {
		int index = recTable.getSelectionModel().getSelectedIndex();
		myList.remove(index);
	}
	
	
	@FXML
	private void getCellMenuAction() {		
		Record rec = recTable.getSelectionModel().getSelectedItem();
		
		try {
			checkAlert(rec==null ? false : true,"수정할 열을 선택 해주세요.");
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			e.printStackTrace();
			e.printStackTrace();
			return ;
		}

		showEditDialog(rec);		  
	}

	private void showEditDialog(Record rec) {
		try {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/dialog/RecordEditDialog.fxml"));
	        BorderPane page = (BorderPane) loader.load();

	        Stage dialogStage = new Stage();
	        dialogStage.setTitle(null);
	        dialogStage.initModality(Modality.WINDOW_MODAL);		        
	        dialogStage.initOwner(pane.getScene().getWindow());
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
	        
	        RecordEditDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setRecord(rec);
	        if(isUserLogin) {
	        	controller.setSaleUserSetting();
	        }
	        dialogStage.showAndWait();

	        if(controller.isOkClicked()) {
	        	Record record = controller.getRecord();
	        	recTable.refresh();
	        }
	   } catch (IOException e) {
	        e.printStackTrace();
	   }
	}
	
	private void checkAlert(boolean isOk,String pwck) throws Exception {
		if(!isOk) {
			throw new Exception(pwck);
		}
	}

	@FXML
	private void addTableAction() {	
		if(tfComfrimField()) {
			Record rec = new Record();	
			no += 1;
			rec.setRecNo(no);
			rec.setRecDate(dateDP.getValue());
			rec.setAccName(acc.getAccName());
			rec.setPdtName(pdt.getPdtName());
			rec.setrProductCode(pdt.getPdtCode());
			rec.setSumPrice(Integer.parseInt(sumPriceTf.getText()));
			rec.setrSalecode(sales.getSaleCode());
			rec.setSaleName(sales.getSaleName());
			rec.setRecDisprice(Integer.parseInt(disPriceTf.getText()));
			rec.setRecDisrate(Integer.parseInt(disrateTf.getText().replaceAll("%", "")));
			rec.setRecCount(Integer.parseInt(countTf.getText()));
			
			myList.add(rec);
			recTable.refresh();
			
			tfAllClear();
		}
	}

	private void tfAllClear() {
		LocalDate nowDate = LocalDate.now();
		dateDP.setValue(nowDate);
		tfUtil.tfClear(pdtTf);
		tfUtil.tfClear(pdtClassTf);
		countTf.setText("1");
		tfUtil.tfClear(priceTf);
		tfUtil.tfClear(disPriceTf);
		tfUtil.tfClear(sumPriceTf);			
		tfUtil.tfClear(unitPriceTf);
	}
	
	private boolean tfComfrimField() {
		try {				
			tfUtil.tfComfrim(accTf);
			tfUtil.tfComfrim(saleTf);			
			tfUtil.tfComfrim(pdtTf);
			tfUtil.tfComfrim(countTf);	
			tfUtil.tfComfrim(priceTf);
			tfUtil.tfComfrim(disrateTf);
			tfUtil.tfComfrim(disPriceTf);
			tfUtil.tfComfrim(sumPriceTf);			
			tfUtil.tfComfrim(unitPriceTf);
			if(Integer.parseInt(countTf.getText()) <= 0) {
				countTf.requestFocus();
				throw new Exception("수량은 최소 1이상의 숫자를 입력해야합니다.");				
			}
			tfUtil.regexTfComfirmNumber(countTf);
			return true;
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			e.printStackTrace();
			return false;
		}		
	}

	
	
	
}	
