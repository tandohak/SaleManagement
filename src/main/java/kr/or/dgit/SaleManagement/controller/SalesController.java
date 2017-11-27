package kr.or.dgit.SaleManagement.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import kr.or.dgit.SaleManagement.MainApp;
import kr.or.dgit.SaleManagement.controller.dialogController.AddrDialogController;
import kr.or.dgit.SaleManagement.controller.dialogController.SalesEditDialogController;
import kr.or.dgit.SaleManagement.dto.AddrItem;
import kr.or.dgit.SaleManagement.dto.Record;
import kr.or.dgit.SaleManagement.dto.Sales;
import kr.or.dgit.SaleManagement.dto.SalesLevel;
import kr.or.dgit.SaleManagement.service.SalesLevelService;
import kr.or.dgit.SaleManagement.service.SalesService;
import kr.or.dgit.SaleManagement.util.TextFieldUtil;

public class SalesController {
	@FXML private BorderPane pane;
	@FXML private AnchorPane bottomPane;
	@FXML private TextField searchAllTf;

	@FXML private TextField nameTf;	
	@FXML private Label codeLabel;
	@FXML private TextField idTf;	
	@FXML private TextField pwTf;	
	@FXML private TextField pwComfTf;	
	@FXML private TextField picTf;	
	@FXML private TextField addrTf;	
	@FXML private TextField addrZipTf;	
	@FXML private TextField telTf;
	
	@FXML private TableColumn<Sales, Integer> codeTc;
	@FXML private TableColumn<Sales, String> telTc;
	@FXML private TableColumn<Sales, String> nameTc;
	@FXML private TableColumn<Sales, String> levelTc;
	@FXML private TableColumn<Sales, String> leaveTc;
	@FXML private TableColumn<Sales, Boolean> chckTc;
	@FXML private TableColumn<Sales, String> addrTc;
	
	@FXML private ComboBox<SalesLevel> levelCb;
	@FXML private CheckBox saleCheck;
	@FXML private CheckBox dbCheck;
	
	@FXML private TableView<Sales> saleTable;
	
	@FXML private ImageView checkIdIcon;
	@FXML private ImageView checkPwIcon;
	
	@FXML private Button submitBtn;
	
	private TextFieldUtil tfUtil = new TextFieldUtil();
	private ObservableList<Sales> myList = FXCollections.observableArrayList();
	
	private ObservableList<SalesLevel> levellist = FXCollections.observableArrayList();
	
	private SalesService saleSerivce;
	private SalesLevelService slevelService;

	private boolean idCheckOk;

	private boolean pwCheckOk;
	
	@FXML
	private void initialize() {
		slevelService = SalesLevelService.getInstance();
		List<SalesLevel> sLevelLists = slevelService.findAllSalesLevel();
		for(SalesLevel saleLevel : sLevelLists) {
			levellist.add(saleLevel);
		}	
		levelCb.setItems(levellist);

			
		saleSerivce = SalesService.getInstance();
		List<Sales> lists = saleSerivce.findSaleAll();
		for(Sales sale : lists) {
			myList.add(sale);
		}		
				
		chckTc.setCellFactory(new Callback<TableColumn<Sales,Boolean>, TableCell<Sales,Boolean>>() {
				@Override
				public TableCell<Sales, Boolean> call(TableColumn<Sales, Boolean> param) {
					CheckBoxTableCell<Sales, Boolean> checkBoxTbc = new CheckBoxTableCell<>();
					checkBoxTbc.setSelectedStateCallback(new Callback<Integer, ObservableValue<Boolean>>() {
						
						@Override
						public ObservableValue<Boolean> call(Integer index) {
							
							return saleTable.getItems().get(index).selectedProperty();
						}
					});
					
					return checkBoxTbc;
				}
		});
		
		saleCheck.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {
					for(Sales sale : myList) {
						sale.setCheckedBox(true);
					}
				}else {
					for(Sales sale : myList) {
						sale.setCheckedBox(false);
					}
				}							
			}
		
		});
		
				
		codeTc.setCellValueFactory(cellData -> cellData.getValue().getSaleCodeProperty().asObject());
		nameTc.setCellValueFactory(cellData -> cellData.getValue().getSaleNameProperty());
		telTc.setCellValueFactory(cellData -> cellData.getValue().getSaleTelProperty());
		levelTc.setCellValueFactory(cellData -> cellData.getValue().getSaleLevelProperty());
		leaveTc.setCellValueFactory(cellData -> cellData.getValue().getSaleLeaveProperty());
		addrTc.setCellValueFactory(cellData -> cellData.getValue().getSaleAddrProperty());;
	    
		checkTable(false);
	}
	
	@FXML
	public void checkboxChange() {
		checkTable(dbCheck.isSelected());
		saleTable.refresh();
	}
	
	private void checkTable(boolean selected) {
		FilteredList<Sales> filterData = new FilteredList<>(myList, s -> true);
		if(selected) {
			searchAllTf.textProperty().addListener((observable, oldValue, newValue)->{
				filterData.setPredicate(sales ->{
					 if (newValue == null || newValue.isEmpty()) {
		                    return true;
		                }
					 
					 //대문자 -> 소문자로 변경
					 String lowerCaseFilter = newValue.toLowerCase();				
					 String saleName = sales.getSaleName().toLowerCase();
					 String SaleLevel = sales.getSaleLevel().toLowerCase();
					 String saleCode = sales.getSaleCode()+"";
					 if(saleName.contains(lowerCaseFilter)) {
						 return true;
					 }
					 
					 if(SaleLevel.contains(lowerCaseFilter)) {
						 return true;
					 }
					 
					 if(saleCode.contains(lowerCaseFilter)) {
						 return true;
					 }
					 
					return false;
				});
			});
		}else {
			filterData.setPredicate(sales ->{	
				if(sales.getSaleLeave().contains("true")) {
					 return true;
				 }
				return false;
			});
			
			searchAllTf.textProperty().addListener((observable, oldValue, newValue)->{
				filterData.setPredicate(sales ->{
					 if (newValue == null || newValue.isEmpty() && sales.getSaleLeave().contains("true")) {
		                    return true;
		                }
					 
					 //대문자 -> 소문자로 변경
					 String lowerCaseFilter = newValue.toLowerCase();				
					 String saleName = sales.getSaleName().toLowerCase();
					 String SaleLevel = sales.getSaleLevel().toLowerCase();
					 String saleCode = sales.getSaleCode()+"";
					 if(saleName.contains(lowerCaseFilter) && sales.getSaleLeave().contains("true")) {
						 return true;
					 }
					 
					 if(SaleLevel.contains(lowerCaseFilter) && sales.getSaleLeave().contains("true")) {
						 return true;
					 }
					 
					 if(saleCode.contains(lowerCaseFilter) && sales.getSaleLeave().contains("true")) {
						 return true;
					 }
					 
					return false;
				});
			});
		}

		// 필터리스트를 sorted리스트에 넣는다
		SortedList<Sales> sortedData = new SortedList<>(filterData);
		
		sortedData.comparatorProperty().bind(saleTable.comparatorProperty());
		
		saleTable.setItems(sortedData);	
		
	}
	
	@FXML
	public void setSaleUserSetting() {
		AnchorPane anchorBotton = new AnchorPane();
		anchorBotton.setPrefHeight(73);
		AnchorPane anchorTop = (AnchorPane)pane.getTop();
		anchorTop.setPrefHeight(80);
		pane.setBottom(anchorBotton);
		dbCheck.setVisible(false);
	}
	
	@FXML
	private void deleteSelectedCell(ActionEvent event) {
		for(int i=0; myList.size()>i; i++) {
			Sales sales = myList.get(i);
			
			if(sales.getCheckedBox()) {
				 sales.setSaleLeave("false");
				 saleSerivce.updateSales(sales);
			};
			
			sales.setCheckedBox(false);
		}

		checkTable(dbCheck.isSelected());
		saleTable.refresh();
	}
	
	@FXML
	private void getCellMenuAction() {		
		Sales sales = saleTable.getSelectionModel().getSelectedItem();
		
		try {
			checkAlert(sales==null ? false : true,"수정할 열을 선택 해주세요.");
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

		try {
		        FXMLLoader loader = new FXMLLoader();
		        loader.setLocation(MainApp.class.getResource("view/dialog/SalesEditDialog.fxml"));
		        BorderPane page = (BorderPane) loader.load();

		        Stage dialogStage = new Stage();
		        dialogStage.setTitle(null);
		        dialogStage.initModality(Modality.WINDOW_MODAL);		        
		        dialogStage.initOwner(pane.getScene().getWindow());
		        Scene scene = new Scene(page);
		        dialogStage.setScene(scene);
		        
		        SalesEditDialogController controller = loader.getController();
		        controller.setDialogStage(dialogStage);
		        controller.setLevellist(levellist);
		       
		        controller.setSales(sales);
		        
		        dialogStage.showAndWait();

		        if(controller.isOkClicked()) {
		        	System.out.println(controller.getSales());
		        	saleSerivce.updateSales(controller.getSales());
		        	refreshTable();
		        }
		   } catch (IOException e) {
		        e.printStackTrace();
		   }	
	}
	
	
	@FXML
	private void submitClickAction() {		
		if(tfComfrimField()) {
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle(null);			
			alert.setHeaderText(null);
			alert.setContentText("정말 등록하시겠습니까?");
			
			ButtonType clickType= alert.showAndWait().get();
			
			if(clickType.getText().equals("취소")) {
				return;
			}
			
			Sales sales = new Sales();
			sales.setSaleName(nameTf.getText());
			sales.setSaleId(idTf.getText());
			String pw = changeKorean(pwTf.getText());
			sales.setSalePw(pw);
			sales.setSaleTel(telTf.getText());
			sales.setSaleLeave("true");
			sales.setSaleAddr("["+ addrZipTf.getText() + "]" + addrTf.getText());
			sales.setSaleLevel(levelCb.getValue().getSalLevel());
			String code = "";
			String year   = new java.text.SimpleDateFormat("yy").format(new java.util.Date());
			
			int codeNum = Integer.parseInt(year+00001);
			int maxCode = saleSerivce.findMaxCode();
			
			if(maxCode >= codeNum) {				
				maxCode++;
				String maxCodeStr = maxCode+"";
				code += year+ maxCodeStr.substring(2, maxCodeStr.length());
				codeNum = Integer.parseInt(code);
			}
			sales.setSaleCode(codeNum);
			saleSerivce.insertSales(sales);
			
			myList.add(sales);
			saleTable.refresh();
		}
	}

	private void checkAlert(boolean isOk,String pwck) throws Exception {
		if(!isOk) {
			throw new Exception(pwck);
		}
	}
	
	@FXML
	private void idTypeCheck(KeyEvent event) {
		idCheckOk = false;
		checkIdIcon.setVisible(false);		
	}
	
	


	@FXML
	private void idTypeHandler() {
		String path = System.getProperty("user.dir");
		
		try {
			tfUtil.regexTfComfirmId(idTf);
		} catch (Exception e) {		
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			e.printStackTrace();
			return;
		}
		
		idCheckOk = tfUtil.idOverlapCheck(idTf);
		
		if(idCheckOk) {
			File file = new File(path + "/DataFile/ic_check_circle_black_48dp_1x.png" );
			Image image = new Image(file.toURI().toString());
			checkIdIcon.setImage(image);
			checkIdIcon.setVisible(true);
		}else {
			checkIdIcon.setVisible(false);
		}	
	}
	
	private void refreshTable() {
		myList = FXCollections.observableArrayList();
		saleSerivce = SalesService.getInstance();
		List<Sales> lists = saleSerivce.findSaleAll();
		for(Sales sale : lists) {
			myList.add(sale);
		}
		saleTable.setItems(myList);
	}
	
	private void refreshTableAdmitTrue() {
		myList = FXCollections.observableArrayList();
		saleSerivce = SalesService.getInstance();
		Sales s = new Sales();
		s.setSaleLeave("true");
		List<Sales> lists = saleSerivce.findSalesSearch(s);
		for(Sales sale : lists) {
			myList.add(sale);
		}
		saleTable.setItems(myList);
	}
	
	@FXML
	private void pwTypeHandler(KeyEvent event) {
		String path = System.getProperty("user.dir");
		String pwVal =pwTf.getText();
		String pwComVal = pwComfTf.getText();
	
		if(pwVal.equals("") || pwComVal.equals("")) {
			checkPwIcon.setVisible(false);
		}else if(pwVal.equals(pwComVal)) {
			File file = new File(path + "/DataFile/ic_check_circle_black_48dp_1x.png" );
			Image image = new Image(file.toURI().toString());
			checkPwIcon.setImage(image);
			checkPwIcon.setVisible(true);
			pwCheckOk = true;
		}else {
			File file = new File(path + "/DataFile/ic_block_black_48dp_1x.png" );
			Image image = new Image(file.toURI().toString());
			checkPwIcon.setImage(image);
			checkPwIcon.setVisible(true);
			pwCheckOk = false;
		}
	}

	private Boolean tfComfrimField() {
		try {
			tfUtil.tfComfrim(nameTf);
			tfUtil.cbComfrim(levelCb);
			tfUtil.tfComfrim(idTf);
			tfUtil.tfComfrim(pwTf);
			tfUtil.tfComfrim(pwComfTf);
			tfUtil.tfComfrim(telTf);
			tfUtil.tfComfrim(addrTf);
			tfUtil.tfComfrim(addrZipTf);
			checkAlert(idCheckOk,"아이디 중복 체크를 해주세요.");
			checkAlert(pwCheckOk,"비밀번호가 같지 않습니다.");	
			tfUtil.regexTfComfirmSaleName(nameTf);
			tfUtil.regexTfComfirmPw(pwTf);
			tfUtil.regexTfComfirmTel(telTf);
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
	
	@FXML
	private void setSalesModel(List<Sales> lists) {
		myList = FXCollections.observableArrayList();
		for(Sales sales : lists) {
			myList.add(sales);
		}
		saleTable.setItems(myList);
	}
	
	@FXML
	private void searchAddrAction() {
		try {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/dialog/AddrZipSearchDialog.fxml"));
	        BorderPane page = (BorderPane) loader.load();

	        Stage dialogStage = new Stage();
	        dialogStage.setTitle(null);
	        dialogStage.initModality(Modality.WINDOW_MODAL);		        
	        dialogStage.initOwner(pane.getScene().getWindow());
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
	        
	        AddrDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	     
	        
	        dialogStage.showAndWait();

	        if(controller.isOkClicked()) {
	          AddrItem addrItem  = controller.getAddrItem();
	          addrTf.setText(addrItem.getAddr());
	          addrZipTf.setText(addrItem.getAddrZip());
	        }
	   } catch (IOException e) {
	        e.printStackTrace();
	   }
	}
	
	public static String changeKorean(String word) {
		// 분리할 단어
		String result = "";
		// 결과 저장할 변수
		String resultEng = "";
		// 알파벳으로
		char[] arrChoSung = { 0x3131, 0x3132, 0x3134, 0x3137, 0x3138, 0x3139, 0x3141, 0x3142, 0x3143, 0x3145, 0x3146,
				0x3147, 0x3148, 0x3149, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e };

		char[] arrJungSung = { 0x314f, 0x3150, 0x3151, 0x3152, 0x3153, 0x3154, 0x3155, 0x3156, 0x3157, 0x3158, 0x3159,
				0x315a, 0x315b, 0x315c, 0x315d, 0x315e, 0x315f, 0x3160, 0x3161, 0x3162, 0x3163 };

		char[] arrJongSung = { 0x0000, 0x3131, 0x3132, 0x3133, 0x3134, 0x3135, 0x3136, 0x3137, 0x3139, 0x313a, 0x313b,
				0x313c, 0x313d, 0x313e, 0x313f, 0x3140, 0x3141, 0x3142, 0x3144, 0x3145, 0x3146, 0x3147, 0x3148, 0x314a,
				0x314b, 0x314c, 0x314d, 0x314e };

		String[] arrChoSungEng = { "r", "R", "s", "e", "E", "f", "a", "q", "Q", "t", "T", "d", "w", "W", "c", "z", "x",
				"v", "g" };

		String[] arrJungSungEng = { "k", "o", "i", "O", "j", "p", "u", "P", "h", "hk", "ho", "hl", "y", "n", "nj", "np",
				"nl", "b", "m", "ml", "l" };

		String[] arrJongSungEng = { "", "r", "R", "rt", "s", "sw", "sg", "e", "f", "fr", "fa", "fq", "ft", "fx", "fv",
				"fg", "a", "q", "qt", "t", "T", "d", "w", "c", "z", "x", "v", "g" };

		String[] arrSingleJaumEng = { "r", "R", "rt", "s", "sw", "sg", "e", "E", "f", "fr", "fa", "fq", "ft", "fx",
				"fv", "fg", "a", "q", "Q", "qt", "t", "T", "d", "w", "W", "c", "z", "x", "v", "g" };

		for (int i = 0; i < word.length(); i++) { /* 한글자씩 읽어들인다. */
			char chars = (char) (word.charAt(i) - 0xAC00);
			if (chars >= 0 && chars <= 11172) {
				/* A. 자음과 모음이 합쳐진 글자인경우 */ /* A-1. 초/중/종성 분리 */
				int chosung = chars / (21 * 28);
				int jungsung = chars % (21 * 28) / 28;
				int jongsung = chars % (21 * 28) % 28;
				/* A-2. result에 담기 */
				result = result + arrChoSung[chosung] + arrJungSung[jungsung];
				/* 자음분리 */
				if (jongsung != 0x0000) { /* A-3. 종성이 존재할경우 result에 담는다 */
					result = result + arrJongSung[jongsung];
				} /* 알파벳으로 */
				resultEng = resultEng + arrChoSungEng[chosung] + arrJungSungEng[jungsung];
				if (jongsung != 0x0000) { /* A-3. 종성이 존재할경우 result에 담는다 */
					resultEng = resultEng + arrJongSungEng[jongsung];
				}
			} else { /* B. 한글이 아니거나 자음만 있을경우 */
				/* 자음분리 */
				result = result + ((char) (chars + 0xAC00)); /* 알파벳으로 */
				if (chars >= 34097 && chars <= 34126) { /* 단일자음인 경우 */
					int jaum = (chars - 34097);
					resultEng = resultEng + arrSingleJaumEng[jaum];
				} else if (chars >= 34127 && chars <= 34147) { /* 단일모음인 경우 */
					int moum = (chars - 34127);
					resultEng = resultEng + arrJungSungEng[moum];
				} else { /* 알파벳인 경우 */
					resultEng = resultEng + ((char) (chars + 0xAC00));
				}
			}
		}
		return resultEng;
	}
}
