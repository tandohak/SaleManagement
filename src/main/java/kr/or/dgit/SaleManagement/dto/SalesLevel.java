package kr.or.dgit.SaleManagement.dto;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SalesLevel {
	private StringProperty salesLevel = new SimpleStringProperty();
	private IntegerProperty salesDisrate = new SimpleIntegerProperty();

	public SalesLevel() {
		
	}
	
	public SalesLevel(String salesLevel) {
		this.salesLevel.set(salesLevel);
	}

	public String getSalesLevel() {
		return salesLevel.get();
	}
	
	public StringProperty getSalesLevelProperty() {
		return salesLevel;
	}

	public void setSalesLevel(String salesLevel) {
		this.salesLevel.set(salesLevel);
	}

	public int getSalesDisrate() {
		return salesDisrate.get();
	}
	
	public IntegerProperty getSalesDisrateProperty() {
		return salesDisrate;
	}

	public void setSalesDisrate(int salesDisrate) {
		this.salesDisrate.set(salesDisrate);
	}

	@Override
	public String toString() {
		return String.format("SalesLevel [salesLevel=%s, salesDisrate=%s]", salesLevel, salesDisrate);
	}

}
