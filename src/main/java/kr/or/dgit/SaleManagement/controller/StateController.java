package kr.or.dgit.SaleManagement.controller;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.sun.javafx.scene.control.skin.TableColumnHeader;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
	@FXML private TableColumn<Record, String> accCount;
	@FXML private TableColumn<Record, String> accPrice;
	@FXML private TableColumn<Record, String> accCost;
	@FXML private TableColumn<Record, String> accDisprice;
	@FXML private TableColumn<Record, String> accMargin;
	@FXML private TableColumn<Record, String> accProfit;
	@FXML private TableView<Record> saleTable;
	@FXML private TableColumn<Record, Integer> saleNo;
	@FXML private TableColumn<Record, Integer> saleCode;
	@FXML private TableColumn<Record, String> saleName;
	@FXML private TableColumn<Record, String> saleCount;
	@FXML private TableColumn<Record, String> salePrice;
	@FXML private TableColumn<Record, String> saleCost;
	@FXML private TableColumn<Record, String> saleDisprice;
	@FXML private TableColumn<Record, String> saleMargin;
	@FXML private TableColumn<Record, String> saleProfit;
	
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
	private ObservableList<Record> standardAccList = FXCollections.observableArrayList();
	private SortedList<Record> sortedAccList;
	private ObservableList<Record> standardSaleList = FXCollections.observableArrayList();
	private SortedList<Record> sortedSaleList;
	
		
	//통계용 변수선언
	int	rank = 1;			//정렬용 번호
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
			recStandard.setRank(rank);
			
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
					
					standardAccList.add(recStandard);
					rank++;	//DATA번호 컨트롤
				}
			}	
		}

		//차트 DATA
		int index = 0;
		String[] accountNames = new String[standardAccList.size()];
		for(Record name : standardAccList) {
			accountNames[index] = name.getAccName();
			index++;
		}
		xAxisName.addAll(Arrays.asList(accountNames));
		xAxis.setCategories(xAxisName);
		
		//차트용 DATA 세팅
		setData(standardAccList, xAxisName);
		
		//테이블 DATA
		accNo.setCellValueFactory(cellData -> cellData.getValue().getRank().asObject());
		accCode.setCellValueFactory(cellData -> cellData.getValue().getaccCodeProperty().asObject());
		accName.setCellValueFactory(cellData -> cellData.getValue().getAccNameProperty());
		accCount.setCellValueFactory(cellData -> cellData.getValue().getFormatCount());
		accCost.setCellValueFactory(cellData -> cellData.getValue().getFormatCost());
		accPrice.setCellValueFactory(cellData -> cellData.getValue().getFormatPrice());
		accDisprice.setCellValueFactory(cellData -> cellData.getValue().getFormatDisprice());
		accMargin.setCellValueFactory(cellData -> cellData.getValue().getFormatMargin());
		accProfit.setCellValueFactory(cellData -> cellData.getValue().getFormatProfit());
		
		sortedAccList = standardAccList.sorted(new Comparator<Record>() {
            @Override
            public int compare(Record o1, Record o2) {
               if(o1.getRank().get() > o2.getRank().get()){
                  return 1;
               }else if(o1.getRank().get() < o2.getRank().get()){
                  return -1;
               }else{
                  return 0;
               }
            }
        });
		
		accTable.setItems(sortedAccList);
		
		//SALES DATA&CHART 로드
		salesChart();
	}
	
	//ACCOUNT CHART DATA 세팅
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
		rank = 1;	//DATA번호 리셋
		List<Sales> salesAllList = salesService.findSaleAll();
		//사원을 기준으로 거래내역 검색
		for(Sales findSalesCode : salesAllList){
			recStandard = new Record();
			setClearTotal();
			
			//통계저장용 사원명 SET
			recStandard.setrSalecode(findSalesCode.getSaleCode());
			recStandard.setSaleName(findSalesCode.getSaleName());
			recStandard.setRank(rank);
			
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
				
				standardSaleList.add(recStandard);
				rank++;	//DATA번호 컨트롤
			}
		}

		//차트 DATA
		xAxis = new CategoryAxis();
		xAxisName = FXCollections.observableArrayList();
		int index = 0;
		String[] salesNames = new String[standardSaleList.size()];
		for(Record name : standardSaleList) {
			Sales finder = new Sales();
			finder.setSaleCode(name.getrSalecode());
			finder = salesService.findSalesByCode(finder);
			salesNames[index] = finder.getSaleName();
			index++;
		}
		xAxisName.addAll(Arrays.asList(salesNames));
		xAxis.setCategories(xAxisName);
		
		//차트용 DATA 세팅
		setDataSales(standardSaleList, xAxisName);
		
		//테이블 DATA
		saleNo.setCellValueFactory(cellData -> cellData.getValue().getRank().asObject());
		saleCode.setCellValueFactory(cellData -> cellData.getValue().getrSalecodeProperty().asObject());
		saleName.setCellValueFactory(cellData -> cellData.getValue().getSaleNameProperty());
		saleCount.setCellValueFactory(cellData -> cellData.getValue().getFormatCount());
		salePrice.setCellValueFactory(cellData -> cellData.getValue().getFormatPrice());
		saleCost.setCellValueFactory(cellData -> cellData.getValue().getFormatCost());
		saleDisprice.setCellValueFactory(cellData -> cellData.getValue().getFormatDisprice());
		saleMargin.setCellValueFactory(cellData -> cellData.getValue().getFormatMargin());
		saleProfit.setCellValueFactory(cellData -> cellData.getValue().getFormatProfit());	

		sortedSaleList = standardSaleList.sorted(new Comparator<Record>() {
            @Override
            public int compare(Record o1, Record o2) {
               if(o1.getRank().get() > o2.getRank().get()){
                  return 1;
               }else if(o1.getRank().get() < o2.getRank().get()){
                  return -1;
               }else{
                  return 0;
               }
            }
        });
		
		saleTable.setItems(sortedSaleList);
	}
	
	//SALES CHART DATA 세팅
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
	
	@FXML
	private void tableSort(MouseEvent mouse) {
		TableColumnHeader columnHeader = null;
		try{
			columnHeader = (TableColumnHeader)mouse.getTarget();
			String findTable = columnHeader.getParent().getParent().getParent().getId(); //이걸로 테이블 찾아야함
			if(columnHeader != null) {
				switch (columnHeader.getId()) {
					case "accProfit":
						sortedAccList = standardAccList.sorted(new Comparator<Record>() {
				            @Override
				            public int compare(Record o1, Record o2) {
				               if(o1.getProfit().get() < o2.getProfit().get()){
				                  return 1;
				               }else if(o1.getProfit().get() > o2.getProfit().get()){
				                  return -1;
				               }else{
				                  return 0;
				               }
				            }
				        });
						break;
					case "accMargin":
						sortedAccList = standardAccList.sorted(new Comparator<Record>() {
				            @Override
				            public int compare(Record o1, Record o2) {
				               if(o1.getMarginPer() < o2.getMarginPer()){
				                  return 1;
				               }else if(o1.getMarginPer() > o2.getMarginPer()){
				                  return -1;
				               }else{
				                  return 0;
				               }
				            }
				        });
						break;
					case "accDisprice":
						sortedAccList = standardAccList.sorted(new Comparator<Record>() {
				            @Override
				            public int compare(Record o1, Record o2) {
				               if(o1.getRecDisprice() < o2.getRecDisprice()){
				                  return 1;
				               }else if(o1.getRecDisprice() > o2.getRecDisprice()){
				                  return -1;
				               }else{
				                  return 0;
				               }
				            }
				        });
						break;
					case "accCost":
						sortedAccList = standardAccList.sorted(new Comparator<Record>() {
				            @Override
				            public int compare(Record o1, Record o2) {
				               if(o1.getRecCost() < o2.getRecCost()){
				                  return 1;
				               }else if(o1.getRecCost() > o2.getRecCost()){
				                  return -1;
				               }else{
				                  return 0;
				               }
				            }
				        });
						break;
					case "accPrice":
						sortedAccList = standardAccList.sorted(new Comparator<Record>() {
				            @Override
				            public int compare(Record o1, Record o2) {
				               if(o1.getRecPrice() < o2.getRecPrice()){
				                  return 1;
				               }else if(o1.getRecPrice() > o2.getRecPrice()){
				                  return -1;
				               }else{
				                  return 0;
				               }
				            }
				        });
						break;
					case "accCount":
						sortedAccList = standardAccList.sorted(new Comparator<Record>() {
				            @Override
				            public int compare(Record o1, Record o2) {
				               if(o1.getRecCount() < o2.getRecCount()){
				                  return 1;
				               }else if(o1.getRecCount() > o2.getRecCount()){
				                  return -1;
				               }else{
				                  return 0;
				               }
				            }
				        });
						break;
					case "saleCount":
						sortedSaleList = standardSaleList.sorted(new Comparator<Record>() {
				            @Override
				            public int compare(Record o1, Record o2) {
				               if(o1.getRecCount() < o2.getRecCount()){
				                  return 1;
				               }else if(o1.getRecCount() > o2.getRecCount()){
				                  return -1;
				               }else{
				                  return 0;
				               }
				            }
				        });
						break;
					case "salePrice":
						sortedSaleList = standardSaleList.sorted(new Comparator<Record>() {
				            @Override
				            public int compare(Record o1, Record o2) {
				               if(o1.getRecPrice() < o2.getRecPrice()){
				                  return 1;
				               }else if(o1.getRecPrice() > o2.getRecPrice()){
				                  return -1;
				               }else{
				                  return 0;
				               }
				            }
				        });
						break;
					case "saleCost":
						sortedSaleList = standardSaleList.sorted(new Comparator<Record>() {
				            @Override
				            public int compare(Record o1, Record o2) {
				               if(o1.getRecCost() < o2.getRecCost()){
				                  return 1;
				               }else if(o1.getRecCost() > o2.getRecCost()){
				                  return -1;
				               }else{
				                  return 0;
				               }
				            }
				        });
						break;
					case "saleDisprice":
						sortedSaleList = standardSaleList.sorted(new Comparator<Record>() {
				            @Override
				            public int compare(Record o1, Record o2) {
				               if(o1.getRecDisprice() < o2.getRecDisprice()){
				                  return 1;
				               }else if(o1.getRecDisprice() > o2.getRecDisprice()){
				                  return -1;
				               }else{
				                  return 0;
				               }
				            }
				        });
						break;
					case "saleMargin":
						sortedSaleList = standardSaleList.sorted(new Comparator<Record>() {
				            @Override
				            public int compare(Record o1, Record o2) {
				               if(o1.getMarginPer() < o2.getMarginPer()){
				                  return 1;
				               }else if(o1.getMarginPer() > o2.getMarginPer()){
				                  return -1;
				               }else{
				                  return 0;
				               }
				            }
				        });
						break;
					case "saleProfit":
						sortedSaleList = standardSaleList.sorted(new Comparator<Record>() {
				            @Override
				            public int compare(Record o1, Record o2) {
				               if(o1.getProfit().get() < o2.getProfit().get()){
				                  return 1;
				               }else if(o1.getProfit().get() > o2.getProfit().get()){
				                  return -1;
				               }else{
				                  return 0;
				               }
				            }
				        });
						break;
					default:
						System.out.println("ID검색 실패");
						break;		
				}
				if(findTable.equals("accTable")) {
					setRank(sortedAccList);
					accTable.setItems(sortedAccList);
				}
				if(findTable.equals("saleTable")) {
					setRank(sortedSaleList);
					saleTable.setItems(sortedSaleList);
				}
			}
		}
		catch (Exception e) {
			
		}
	}
	
	public void setRank(SortedList<Record> sortedList) {
		int rank = 1;
		for(Record rec : sortedList) {
			rec.setRank(rank);
			rank++;
		}
	}
}
