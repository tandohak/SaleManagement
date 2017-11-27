package kr.or.dgit.SaleManagement.controller;

import java.text.Format;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import kr.or.dgit.SaleManagement.dto.Account;
import kr.or.dgit.SaleManagement.dto.Product;
import kr.or.dgit.SaleManagement.dto.Record;
import kr.or.dgit.SaleManagement.dto.Sales;
import kr.or.dgit.SaleManagement.service.AccountService;
import kr.or.dgit.SaleManagement.service.ProductService;
import kr.or.dgit.SaleManagement.service.RecordSerivce;
import kr.or.dgit.SaleManagement.service.SalesService;

public class StateController {
	@FXML private BorderPane pane;
	@FXML private TextField testTf;

	@FXML private TableView<Record> accTable;
	@FXML private TableColumn<Record, Integer> accNo;
	@FXML private TableColumn<Record, Integer> accCode;
	@FXML private TableColumn<Record, String> accName;
	@FXML private TableColumn<Record, Integer> accCount;
	@FXML private TableColumn<Record, Integer> accPrice;
	@FXML private TableColumn<Record, Integer> accCost;
	@FXML private TableColumn<Record, Integer> accDisprice;
	@FXML private TableColumn<Record, Integer> accMargin;
	@FXML private TableColumn<Record, Integer> accProfit;
	@FXML private TableView<Record> saleTable;
	@FXML private TableColumn<Record, Integer> saleNo;
	@FXML private TableColumn<Record, Integer> saleCode;
	@FXML private TableColumn<Record, String> saleName;
	@FXML private TableColumn<Record, Integer> saleCount;
	@FXML private TableColumn<Record, Integer> salePrice;
	@FXML private TableColumn<Record, Integer> saleCost;
	@FXML private TableColumn<Record, Integer> saleDisprice;
	@FXML private TableColumn<Record, Integer> saleMargin;
	@FXML private TableColumn<Record, Integer> saleProfit;	
	
	@FXML private StackedBarChart<String, Integer> stackChartAccount;
	@FXML private StackedBarChart<String, Integer> stackChartSales;
	@FXML private CategoryAxis xAxis = new CategoryAxis();

	//차트용 옵저버
	private ObservableList<String> xAxisName = FXCollections.observableArrayList();
	
	//서비스
	private SalesService salesService;
	private AccountService accountService;
	private ProductService productService;
	private RecordSerivce recordService;
	
	private Record recStandard;
	private ObservableList<Record> standardList = FXCollections.observableArrayList();
	
		
	//통계용 변수선언
	int totalProfit = 0;	//매출이익(마진액)
	int totalMarginPer = 0;	//마진율
	int totalDiscount = 0;	//할인금액
	int totalCost = 0;		//원가
	int totalSales = 0;		//매매가
	int totalCount = 0;		//판매수량
	int totalRecCount = 0;	//거래내역수

	@FXML
	private void initialize() {				
		//서비스연결
		salesService = SalesService.getInstance();
		accountService = AccountService.getInstance();
		productService = ProductService.getInstance();
		recordService = RecordSerivce.getInstance();
		
		//거래처별 통계
		//거래처목록 전체로드		
		List<Account> accAllList = accountService.findAllAccount();
		//거래처목록을 기준으로 제품 검색
		for(Account findAccCode : accAllList){			
			//통계용 변수 초기화
			setClearTotal();
			recStandard = new Record();
			
			//통계저장용 업체명 SET
			recStandard.setAccCode(findAccCode.getAccCode());
			recStandard.setAccName(findAccCode.getAccName());
			
			//거래처별 제품목록 검색
			Product findPdtByAccCode = new Product();
			findPdtByAccCode.setAccCode(findAccCode.getAccCode());
			List<Product> findPdtCodeByAcc = productService.findByAllItem(findPdtByAccCode);
			//예외처리 : 거래처에 대한 제품이 존재할 경우
			if(findPdtCodeByAcc.size() != 0) {
				//제품별 거래목록 검색
				for(Product findPdtCode : findPdtCodeByAcc) {
					Record findRecByPdtCode = new Record();
					findRecByPdtCode.setrProductCode(findPdtCode.getPdtCode());
					List<Record> findRecordByPdt = recordService.findRecordBySearch(findRecByPdtCode);
					
					//거래내역 합산
					for(Record rec : findRecordByPdt) {
						totalDiscount += ((findPdtCode.getPdtPrice()*rec.getRecCount())*(rec.getRecDisrate()*0.01));
						totalCost += (findPdtCode.getPdtCost()*rec.getRecCount());
						totalSales += (findPdtCode.getPdtPrice()*rec.getRecCount());
						totalCount += rec.getRecCount();
						totalRecCount++;
					}
				}
			
				//예외처리 : 제품에 대한 거래내역이 존재할 경우
				if(totalRecCount != 0) {
					//거래처별 합산
					totalProfit = totalSales - totalCost - totalDiscount;
					double transPer = (double)totalProfit / (double)totalSales;
					totalMarginPer = (int)(transPer * 100);
					
					recStandard.setProfit(totalProfit);			//통계저장용 매출이익 SET
					recStandard.setMarginPer(totalMarginPer);	//통계저장용 마진율 SET
					recStandard.setRecDisprice(totalDiscount);	//통계저장용 할인금액 SET
					recStandard.setRecCost(totalCost);			//통계저장용 원가총액 SET
					recStandard.setRecPrice(totalSales);		//통계저장용 매매총액 SET
					recStandard.setRecCount(totalCount);		//통계저장용 판매수량 SET
					recStandard.setRecNo(totalRecCount);		//통계저장용 거래내역수 SET
					
					standardList.add(recStandard);
				}
			}
		}

		//차트 DATA
		int index = 0;
		String[] accountNames = new String[standardList.size()];
		for(Record name : standardList) {
			accountNames[index] = name.getAccName();
			index++;
		}
		xAxisName.addAll(Arrays.asList(accountNames));
		xAxis.setCategories(xAxisName);
		
		setData(standardList, xAxisName);
		
		//테이블 DATA
		accCode.setCellValueFactory(cellData -> cellData.getValue().getaccCodeProperty().asObject());
		accName.setCellValueFactory(cellData -> cellData.getValue().getAccNameProperty());
		accCount.setCellValueFactory(cellData -> cellData.getValue().getRecCountProperty().asObject());
		accCost.setCellValueFactory(cellData -> cellData.getValue().getRecCostProperty().asObject());
		accPrice.setCellValueFactory(cellData -> cellData.getValue().getRecPriceProperty().asObject());
		accDisprice.setCellValueFactory(cellData -> cellData.getValue().getRecDispriceProperty().asObject());
		accMargin.setCellValueFactory(cellData -> cellData.getValue().getMarginPerProperty().asObject());
		accProfit.setCellValueFactory(cellData -> cellData.getValue().getProfitProperty().asObject());

		accTable.setItems(standardList);
		
		//먼저 로드를 해놓는 부분
		salesChart();
	}
	
	public void setData(List<Record> standardList, ObservableList<String> xAxisName) {
		int index = 0;
		int[][] chartBar = new int[standardList.size()][3];
		for(Record rec : standardList) {
			chartBar[index][0] = rec.getRecCost();
			chartBar[index][1] = rec.getRecDisprice();
			chartBar[index][2] = rec.getProfitByInt();
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
		stackChartAccount.getData().addAll(seriesCost, seriesPrice, seriesProfit);
	}
	
	public void setClearTotal() {
		totalProfit = 0;	//매출이익
		totalMarginPer = 0;	//마진율
		totalDiscount = 0;	//할인금액
		totalCost = 0;		//원가
		totalSales = 0;		//매매가
		totalCount = 0;		//판매수량
		totalRecCount = 0;	//거래내역수
	}
	
	//영업사원별 통계
	public void salesChart() {
		
		standardList = FXCollections.observableArrayList();
		List<Sales> salesAllList = salesService.findSaleAll();
		//사원을 기준으로 거래내역 검색
		for(Sales findSalesCode : salesAllList){			
			recStandard = new Record();
			
			//통계저장용 사원명 SET
			recStandard.setrSalecode(findSalesCode.getSaleCode());
			recStandard.setSaleName(findSalesCode.getSaleName());
			
			//사원별 거래내역 검색
			Record findRecBySaleCode = new Record();
			findRecBySaleCode.setrSalecode(findSalesCode.getSaleCode());
			List<Record> findRecBySales = recordService.findRecordBySearch(findRecBySaleCode);
			//예외처리 : 사원에 대한 거래내역이 존재할 경우
			if(findRecBySales.size() != 0) {
				//물품정보 검색 
				for(Record rec : findRecBySales) {
					Product pdtInfo = new Product();
					pdtInfo.setPdtCode(rec.getrProductCode());
					pdtInfo = productService.findBypdtProduct(pdtInfo);
					//사원 거래내역 결과 합산
					totalDiscount += ((pdtInfo.getPdtPrice()*rec.getRecCount())*(rec.getRecDisrate()*0.01));
					totalCost += pdtInfo.getPdtCost()*rec.getRecCount();
					totalSales += pdtInfo.getPdtPrice()*rec.getRecCount();
					totalCount += rec.getRecCount();
					totalRecCount++;
				}
				totalProfit = totalSales - totalCost - totalDiscount;
				double transPer = (double)totalProfit / (double)totalSales;
				totalMarginPer = (int)(transPer * 100);
				
				recStandard.setProfit(totalProfit);			//통계저장용 매출이익 SET
				recStandard.setMarginPer(totalMarginPer);	//통계저장용 마진율 SET
				recStandard.setRecDisprice(totalDiscount);	//통계저장용 할인금액 SET
				recStandard.setRecCost(totalCost);			//통계저장용 원가총액 SET
				recStandard.setRecPrice(totalSales);		//통계저장용 매매총액 SET
				recStandard.setRecCount(totalCount);		//통계저장용 판매수량 SET
				recStandard.setRecNo(totalRecCount);		//통계저장용 거래내역수 SET
				
				System.out.println(recStandard.getProfit());
				System.out.println(recStandard.getMarginPer());
				System.out.println(recStandard.getRecDisprice());
				System.out.println(recStandard.getRecCost());
				System.out.println(recStandard.getRecPrice());
				System.out.println(recStandard.getRecCount());
				System.out.println(recStandard.getRecNo());
				
				standardList.add(recStandard);
			}
		}

		//차트 DATA
		xAxis = new CategoryAxis();
		xAxisName = FXCollections.observableArrayList();
		int index = 0;
		String[] salesNames = new String[standardList.size()];
		for(Record name : standardList) {
			Sales finder = new Sales();
			finder.setSaleCode(name.getrSalecode());
			finder = salesService.findSalesByCode(finder);
			salesNames[index] = finder.getSaleName();
			index++;
		}
		xAxisName.addAll(Arrays.asList(salesNames));
		xAxis.setCategories(xAxisName);
		
		setDataSales(standardList, xAxisName);
		
		//테이블 DATA
		saleCode.setCellValueFactory(cellData -> cellData.getValue().getrSalecodeProperty().asObject());
		saleName.setCellValueFactory(cellData -> cellData.getValue().getSaleNameProperty());
		saleCount.setCellValueFactory(cellData -> cellData.getValue().getRecCountProperty().asObject());
		salePrice.setCellValueFactory(cellData -> cellData.getValue().getRecCostProperty().asObject());
		saleCost.setCellValueFactory(cellData -> cellData.getValue().getRecPriceProperty().asObject());
		saleDisprice.setCellValueFactory(cellData -> cellData.getValue().getRecDispriceProperty().asObject());
		saleMargin.setCellValueFactory(cellData -> cellData.getValue().getMarginPerProperty().asObject());
		saleProfit.setCellValueFactory(cellData -> cellData.getValue().getProfitProperty().asObject());

		saleTable.setItems(standardList);
	}
	
	public void setDataSales(List<Record> standardList, ObservableList<String> xAxisName) {
		int index = 0;
		int[][] chartBar = new int[standardList.size()][3];
		for(Record rec : standardList) {
			chartBar[index][0] = rec.getRecCost();
			chartBar[index][1] = rec.getRecDisprice();
			chartBar[index][2] = rec.getProfitByInt();
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
		stackChartSales.getData().addAll(seriesCost, seriesPrice, seriesProfit);
	}
}
