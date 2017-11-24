package kr.or.dgit.SaleManagement.controller;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kr.or.dgit.SaleManagement.MainApp;
import kr.or.dgit.SaleManagement.controller.dialogController.SalesSearchDialogController;
import kr.or.dgit.SaleManagement.dto.Account;
import kr.or.dgit.SaleManagement.dto.AccountLevel;
import kr.or.dgit.SaleManagement.dto.Product;
import kr.or.dgit.SaleManagement.dto.Record;
import kr.or.dgit.SaleManagement.dto.Sales;
import kr.or.dgit.SaleManagement.dto.SalesLevel;
import kr.or.dgit.SaleManagement.service.AccountService;
import kr.or.dgit.SaleManagement.service.BigClassService;
import kr.or.dgit.SaleManagement.service.ProductService;
import kr.or.dgit.SaleManagement.service.RecordSerivce;
import kr.or.dgit.SaleManagement.service.SalesService;
import kr.or.dgit.SaleManagement.service.SmallClassService;

public class StateController {
	@FXML
	private BorderPane pane;

	@FXML
	private TextField testTf;

	@FXML
	private TableView<Record> accTable;
	@FXML
	private TableColumn<Record, Integer> accNo;
	@FXML
	private TableColumn<Record, Integer> accCode;
	@FXML
	private TableColumn<Record, String> accName;
	@FXML
	private TableColumn<Record, Integer> accCount;
	@FXML
	private TableColumn<Record, Integer> accPrice;
	@FXML
	private TableColumn<Record, Integer> accCost;
	@FXML
	private TableColumn<Record, Integer> accDisprice;
	@FXML
	private TableColumn<Record, Integer> accMargin;
	@FXML
	private TableColumn<Record, Integer> accProfit;

	private ObservableList<Record> acclist = FXCollections.observableArrayList();
	// private static ProductService pdtService;
	// private static BigClassService bigService;
	// private static SmallClassService smallService;
	// private static AccountService accService;
	// private static RecordSerivce recordService;
	private SalesService saleService;
	private AccountService accService;
	private ProductService pdtService;
	private RecordSerivce recService;
	private ObservableList<Record> myList = FXCollections.observableArrayList();

	private ObservableList<Record> accList = FXCollections.observableArrayList();

	@FXML
	private void initialize() {

		/*
		 * saleService = SalesService.getInstance(); pdtService =
		 * ProductService.getInstance(); accService = AccountService.getInstance();
		 * recService = RecordSerivce.getInstance();
		 * 
		 * List<Record> lists = recService.findRecordByAll(); Record record = new
		 * Record();
		 * 
		 * for(Record r : lists) { Account acc = new Account(); Product pdt = new
		 * Product(); Sales sal = new Sales(); Sales sal1 = new Sales();
		 * sal.setSaleCode(r.getAccCode().get()); Account acc1 = new Account();
		 * 
		 * pdt = pdtService.findBypdtProduct(new Product(r.getrProductCode()));
		 * acc.setAccCode(pdt.getAccCode()); acc1 = accService.findAccountByCode(acc);
		 * //sal1 = saleService.findSalesByCode(sal);
		 * 
		 * //System.out.println(sal);
		 * 
		 * int num = r.getRecCount(); int price = pdt.getPdtPrice()*num; int cost =
		 * pdt.getPdtCost()*num; int disPrice = r.getRecDisprice();
		 * 
		 * int profit = price - cost-disPrice; int margin = profit*100/price;
		 * 
		 * // if(acc1.getAccCode() == 20503005) { // price += pdt.getPdtPrice()*num; //
		 * }
		 * 
		 * 
		 * 
		 * r.setAccName(acc1.getAccName()); r.setRecPrice(price); r.setRecCost(cost);
		 * r.setAccCode(acc1.getAccCode()); r.setProfit(profit); r.setMargin(margin);
		 * acclist.add(r); }
		 */

		accTableSet();

		// refreshTable();
		accCode.setCellValueFactory(cellData -> cellData.getValue().getaccCodeProperty().asObject());
		accName.setCellValueFactory(cellData -> cellData.getValue().getAccNameProperty());
		accCount.setCellValueFactory(cellData -> cellData.getValue().getRecCountProperty().asObject());
		accCost.setCellValueFactory(cellData -> cellData.getValue().getRecCostProperty().asObject());
		accPrice.setCellValueFactory(cellData -> cellData.getValue().getRecPriceProperty().asObject());
		accDisprice.setCellValueFactory(cellData -> cellData.getValue().getRecDispriceProperty().asObject());
		accMargin.setCellValueFactory(cellData -> cellData.getValue().getMarginProperty().asObject());
		accProfit.setCellValueFactory(cellData -> cellData.getValue().getProfitProperty().asObject());

		// accName.setCellValueFactory(cellData ->
		// cellData.getValue().getAccNameProperty());

		accTable.setItems(acclist);

	}

	private void accTableSet() {
		saleService = SalesService.getInstance();
		pdtService = ProductService.getInstance();
		accService = AccountService.getInstance();
		recService = RecordSerivce.getInstance();

		List<Record> lists = recService.findRecordByAll();
		Record record = new Record();

		for (Record r : lists) {
			Account acc = new Account();
			Product pdt = new Product();

			pdt = pdtService.findBypdtProduct(new Product(r.getrProductCode()));
			acc.setAccCode(pdt.getAccCode());

			Account acc1 = new Account();
			acc1 = accService.findAccountByCode(acc);

			Sales sale = new Sales();
			sale.setSaleCode(r.getrSalecode());
			sale = saleService.findSalesByCode(sale);

			int num = r.getRecCount();
			int price = pdt.getPdtPrice() * num;
			int cost = pdt.getPdtCost() * num;
			int disPrice = r.getRecDisprice();

			int profit = price - cost - disPrice;
			int margin = profit * 100 / price;

			r.setAccName(acc1.getAccName());
			r.setRecPrice(price);
			r.setRecCost(cost);
			r.setAccCode(acc1.getAccCode());
			r.setProfit(profit);
			r.setMargin(margin);
			acclist.add(r);
		}
	}

	// 사원검색
	@FXML
	public void SearchSalesAction() {
		System.out.println("사원검색");
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

			System.out.println("사원검색");
			// if(controller.isOkClicked()) {
			// sales = controller.getItem();
			// saleTf.setText(sales.getSaleName());
			// saleLevelTf.setText(sales.getSaleLevel());
			//
			// SalesLevel saleDis = sLevelService.findOneSalesLevel(new
			// SalesLevel(saleLevelTf.getText()));
			// AccountLevel accDis = accLevelService.findOneAccount(new
			// AccountLevel(accLevelTf.getText()));
			// int sumDisrate = saleDis.getSalDisrate() + accDis.getAccDisrate();
			// disrateTf.setText(sumDisrate + "%");
			// }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 제품검색
	@FXML
	public void SearchProductAction() {
		System.out.println("제품별검색");
	}

	// 거래처검색
	@FXML
	public void SearchAccountAction() {
		System.out.println("거래처별검색");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("view/dialog/AccountSearchDialog.fxml"));
		BorderPane page;
		try {
			page = (BorderPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("거래처별");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(pane.getScene().getWindow());
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			ProductSearchAccountController controller = loader.getController();
			controller.setDialogStage(dialogStage);

			dialogStage.showAndWait();

			// if(controller.isOkClicked()) {
			// accCodeTf.setText(controller.getGetAcc().getAccCode()+"");
			// }

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void refreshTable() {
		saleService = SalesService.getInstance();
		accService = AccountService.getInstance();
		pdtService = ProductService.getInstance();
		recService = RecordSerivce.getInstance();

		List<Record> reclists = recService.findRecordByAll();

		for (Record rec : reclists) {
			Product pdt = new Product();
			System.out.println(rec.getrProductCode());
			pdt.setPdtCode(rec.getrProductCode());
			pdt = pdtService.findBypdtProduct(pdt);
			System.out.println(pdt);
			Account acc = new Account();
			acc.setAccCode(pdt.getAccCode());
			acc = accService.findAccountByCode(acc);
			Sales sale = new Sales();
			sale.setSaleCode(rec.getrSalecode());
			sale = saleService.findSalesByCode(sale);

			rec.setAccName(acc.getAccName());
			rec.setPdtName(pdt.getPdtName());
			rec.setSaleName(sale.getSaleName());
			rec.setRecCost(pdt.getPdtCost());
			rec.setRecPrice(pdt.getPdtPrice());

			myList.add(rec);
		}
	}

}
