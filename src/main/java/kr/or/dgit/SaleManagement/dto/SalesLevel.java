package kr.or.dgit.SaleManagement.dto;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SalesLevel {
	private StringProperty sLevel = new SimpleStringProperty();
	private IntegerProperty sDisrate = new SimpleIntegerProperty();

	public SalesLevel() {
		
	}
	
	public SalesLevel(String salesLevel) {
		this.sLevel.set(salesLevel);
	}

	public String getSalesLevel() {
		return sLevel.get();
	}
	
	public StringProperty getSalesLevelProperty() {
		return sLevel;
	}

	public void setSalesLevel(String salesLevel) {
		this.sLevel.set(salesLevel);
	}

	public int getSalesDisrate() {
		return sDisrate.get();
	}
	
	public IntegerProperty getSalesDisrateProperty() {
		return sDisrate;
	}

	public void setSalesDisrate(int salesDisrate) {
		this.sDisrate.set(salesDisrate);
	}

	@Override
	public String toString() {
		return String.format("SalesLevel [salesLevel=%s, salesDisrate=%s]", sLevel, sDisrate);
	}

}
