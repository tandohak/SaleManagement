package kr.or.dgit.SaleManagement.dto;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SalesLevel {
	private StringProperty salLevel = new SimpleStringProperty();
	private IntegerProperty salDisrate = new SimpleIntegerProperty();	
	
	public int getSalDisrate() {
		return salDisrate.get();
	}
	public void setSalDisrate(int salDisrate) {
		this.salDisrate.set(salDisrate);
	}
	public String getSalLevel() {
		return salLevel.get();
	}
	public void setSalLevel(String salLevel) {
		this.salLevel.set(salLevel);;
	}
	
	public IntegerProperty getSalDisrateProperty() {
		return salDisrate;
	}
	
	public StringProperty getSalLevelProperty() {
		return salLevel;
	}
	@Override
	public String toString() {
		return String.format("%s", salLevel.getValue());
	}
	
	
	
	
}
