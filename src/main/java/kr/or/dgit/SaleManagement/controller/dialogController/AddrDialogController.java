package kr.or.dgit.SaleManagement.controller.dialogController;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import kr.or.dgit.SaleManagement.dto.Addr;
import kr.or.dgit.SaleManagement.dto.AddrItem;
import kr.or.dgit.SaleManagement.dto.Sales;
import kr.or.dgit.SaleManagement.service.AddrService;

public class AddrDialogController {
	@FXML private BorderPane pane;
	@FXML private TableColumn<AddrItem, String> addrZipTc;
	@FXML private TableColumn<AddrItem, String> addrTc;
	@FXML private TableView<AddrItem> addrTb;
	@FXML private TextField searchTf;
	
	private AddrService addrService=AddrService.getInstance();
	
	private Stage dialogStage;
	private AddrItem addrItem;
	private boolean okClicked = false;
	private ObservableList<AddrItem> myList;
	
	@FXML
	private void initaialize() {		
	
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
	private void searchAddrAction() {
		myList = FXCollections.observableArrayList();

		String text[] = searchTf.getText().split(" ");
		
		Addr addr = new Addr();
		if(text.length == 1) {			
			addr.setDoro(text[0]+"%");	
		}
		
		if(text.length == 2) {
			addr.setSido(text[0]+"%");
			if(text[1].lastIndexOf("구") > 0) {
				addr.setSigungu(text[1]+"%");
			}else {
				addr.setDoro(text[1]+"%");	
			}
		}
		
		if(text.length == 3) {
			addr.setSido(text[0]+"%");
			addr.setSigungu(text[1]+"%");
			addr.setDoro(text[2]+"%");
		}
		
		List<Addr> res = addrService.findAddrSearch(addr, new RowBounds(0,50));
		
		for(Addr add : res) {
			AddrItem addrIt = new AddrItem();
	
			addrIt.setAddr(add.getAddrs());
			addrIt.setAddrZip(add.getZipCode());
			myList.add(addrIt);
		}
		
		addrZipTc.setCellValueFactory(cellData -> cellData.getValue().getAddrZipProperty());
		addrTc.setCellValueFactory(cellData -> cellData.getValue().getAddrProperty());
	
		addrTb.setItems(myList);
	}
	
	@FXML
	private void handleCancel() {
	      dialogStage.close();
	}
	
	@FXML
	private void handleOk() {
		addrItem = addrTb.getSelectionModel().getSelectedItem();		
		
		okClicked = true;	
		dialogStage.close();
	}
}
