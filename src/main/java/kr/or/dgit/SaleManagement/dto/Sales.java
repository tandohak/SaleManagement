package kr.or.dgit.SaleManagement.dto;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Sales {
	private IntegerProperty saleCode = new SimpleIntegerProperty();	
	private StringProperty saleName = new SimpleStringProperty();
	private StringProperty saleTel = new SimpleStringProperty();
	private StringProperty saleAddr = new SimpleStringProperty();
	private StringProperty saleLeave = new SimpleStringProperty();
	private StringProperty saleId = new SimpleStringProperty();
	private StringProperty salePw = new SimpleStringProperty();
	private StringProperty saleLevel = new SimpleStringProperty();;

	public Sales() {}

	public int getSaleCode() {
		return saleCode.get();	
	}
	
	public IntegerProperty getSaleCodeProperty() {
		return saleCode;	
	}
	
	public void setSaleCode(int saleCode) {
		this.saleCode.set(saleCode);
	}

	public String getSaleName() {
		return saleName.get();
	}
	
	public StringProperty getSaleNameProperty() {
		return saleName;
	}
	
	public void setSaleName(String saleName) {
		this.saleName.set(saleName);
	}

	public String getSaleTel() {
		return saleTel.get();
	}
	
	public StringProperty getSaleTelProperty() {
		return saleTel;
	}
	
	public void setSaleTel(String saleTel) {
		this.saleTel.set(saleTel);
	}

	public String getSaleAddr() {
		return saleAddr.get();
	}
	public StringProperty getSaleAddrProperty() {
		return saleAddr;
	}
	public void setSaleAddr(String saleAddr) {
		this.saleAddr.set(saleAddr);
	}

	public String getSaleLeave() {
		return saleLeave.get();
	}
	
	public StringProperty getSaleLeaveProperty() {
		return saleLeave;
	}
	
	public void setSaleLeave(String saleLeave) {
		this.saleLeave.set(saleLeave);
	}

	public String getSaleId() {
		return saleId.get();
	}
	
	public StringProperty getSaleIdProperty() {
		return saleId;
	}
	
	public void setSaleId(String saleId) {
		this.saleId.set(saleId);;
	}

	public String getSalePw() {
		return salePw.get();
	}
	
	public StringProperty getSalePwProperty() {
		return salePw;
	}
	
	public void setSalePw(String salePw) {
		this.salePw.set(salePw);;
	}

	public String getSaleLevel() {
		return saleLevel.get();
	}
	
	public StringProperty getSaleLevelProperty() {
		return saleLevel;
	}
	
	public void setSaleLevel(String saleLevel) {
		this.saleLevel.set(saleLevel);;
	}

	@Override
	public String toString() {
		return String.format(
				"Sales [saleCode=%s, saleName=%s, saleTel=%s, saleAddr=%s, saleLeave=%s, saleId=%s, salePw=%s, saleLevel=%s]",
				saleCode, saleName, saleTel, saleAddr, saleLeave, saleId, salePw, saleLevel);
	}
	
	
	
	

}
