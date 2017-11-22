package kr.or.dgit.SaleManagement.controller.dialogController;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import kr.or.dgit.SaleManagement.dto.Addr;
import kr.or.dgit.SaleManagement.dto.AddrItem;
import kr.or.dgit.SaleManagement.service.AddrService;

public class AddrDialogController {
	@FXML private BorderPane pane;
	@FXML private TableColumn<AddrItem, String> addrZipTc;
	@FXML private TableColumn<AddrItem, String> addrTc;
	@FXML private TableView<AddrItem> addrTb;
	@FXML private TextField searchDoro;
	@FXML private ComboBox<String> searchSido;
	@FXML private ComboBox<String> searchSigungu;

	private AddrService addrService = AddrService.getInstance();
	private Stage dialogStage;
	private AddrItem addrItem = new AddrItem();
	private boolean okClicked = false;
	private ObservableList<AddrItem> myList;
	private ObservableList<String> sidoList = FXCollections.observableArrayList();
	private ObservableList<String> sigunguList;
	
	@FXML
	private void initialize() {
		List<Addr> listSido = addrService.findAddrBySido();
		for(Addr sido : listSido)
			sidoList.add(sido.getSido());
		searchSido.setItems(sidoList);
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public AddrItem getAddrItem() {
		return addrItem;
	}

	public boolean isOkClicked() {
		return okClicked;
	}
	
	@FXML
	public void changeSigungu() {
		sigunguList = FXCollections.observableArrayList();
		String sido = searchSido.getValue().toString();
		Addr addrSido = new Addr();
		addrSido.setSido(sido);
		List<Addr> listSigungu = addrService.findAddrBySigungu(addrSido);
		for(Addr sigungu : listSigungu)
			sigunguList.add(sigungu.getSigungu());
		searchSigungu.setItems(sigunguList);
	}
	
	@FXML
	public void findAddressList() {
		myList = FXCollections.observableArrayList();
		String sido = searchSido.getValue().toString();
		String sigungu = searchSigungu.getValue().toString();
		String doro = "%"+searchDoro.getText()+"%";
		
		Addr findAddress = new Addr();
		findAddress.setSido(sido);
		findAddress.setSigungu(sigungu);
		findAddress.setDoro(doro);
		
		List<Addr> lists = addrService.findAddrByDoro(findAddress);
		for(Addr findAddr : lists) {
			AddrItem addr = new AddrItem();
			addr.setAddrZip(findAddr.getZipCode().toString());
			String sumAddr = findAddr.getSido() + " " + findAddr.getSigungu() + " " + findAddr.getDoro();
			addr.setAddr(sumAddr);
			myList.add(addr);
		}
		addrZipTc.setCellValueFactory(cellData -> cellData.getValue().getAddrZipProperty());
		addrTc.setCellValueFactory(cellData -> cellData.getValue().getAddrProperty());
		
		addrTb.setItems(myList);
	}
	
	@FXML
	private void handleOk() {
		addrItem = addrTb.getSelectionModel().getSelectedItem();
		okClicked = true;
		dialogStage.close();
	}
	
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
}
