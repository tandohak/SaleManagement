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
	private AddrItem addrItem;
	private boolean okClicked = false;
	private ObservableList<Addr> myList = FXCollections.observableArrayList();
	private ObservableList<String> sidoList = FXCollections.observableArrayList();
	private ObservableList<String> sigunguList = FXCollections.observableArrayList();
	
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
		String sido = searchSido.getValue().toString();
		Addr addrSido = new Addr();
		addrSido.setSido(sido);
		List<Addr> listSigungu = addrService.findAddrBySigungu(addrSido);
		for(Addr sigungu : listSigungu)
			sigunguList.add(sigungu.getSigungu());
		searchSigungu.setItems(sigunguList);
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
