package kr.or.dgit.SaleManagement.dto;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Sales {
	private IntegerProperty saleCode;
	private StringProperty saleName;
	private StringProperty saleTel;
	private StringProperty saleAddr;
	private StringProperty saleLeave;
	private StringProperty saleId;
	private StringProperty salePw;
	private StringProperty saleLevel;

	public Sales() {
		saleCode = new SimpleIntegerProperty();	
		saleName = new SimpleStringProperty();
		saleTel = new SimpleStringProperty();
		saleAddr = new SimpleStringProperty();
		saleLeave = new SimpleStringProperty();
		saleId = new SimpleStringProperty();
		salePw = new SimpleStringProperty();
		saleLevel = new SimpleStringProperty();
	}

	public int getSaleCode() {
		return saleCode.get();
		
	}

	public void setSaleCode(int saleCode) {
		this.saleCode.set(saleCode);
	}

	public String getSaleName() {
		return saleName.get();
	}

	public void setSaleName(String saleName) {
		this.saleName.set(saleName);
	}

	public String getSaleTel() {
		return saleTel.get();
	}

	public void setSaleTel(String saleTel) {
		this.saleTel.set(saleTel);
	}

	public String getSaleAddr() {
		return saleAddr.get();
	}

	public void setSaleAddr(String saleAddr) {
		this.saleAddr.set(saleAddr);
	}

	public String getSaleLeave() {
		return saleLeave.get();
	}

	public void setSaleLeave(String saleLeave) {
		this.saleLeave.set(saleLeave);
	}

	public String getSaleId() {
		return saleId.get();
	}

	public void setSaleId(String saleId) {
		this.saleId.set(saleId);;
	}

	public String getSalePw() {
		return salePw.get();
	}

	public void setSalePw(String salePw) {
		this.salePw.set(salePw);;
	}

	public String getSaleLevel() {
		return saleLevel.get();
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
