package kr.or.dgit.SaleManagement.dto;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SmallClass {
	private IntegerProperty smallClass = new SimpleIntegerProperty();
	private StringProperty smallName = new SimpleStringProperty();
	private IntegerProperty sBigClass = new SimpleIntegerProperty();
	private StringProperty bigName = new SimpleStringProperty();

	
	public StringProperty getBigNameProperty() {
		return bigName;
	}

	public String bigName() {
		return bigName.get();
	}
	
	public void setBigName(String bigName) {
		this.bigName.set(bigName);
	}
	


	public SmallClass(int smallClass, String smallName, int sBigClass) {
		this.smallClass.set(smallClass);
		this.smallName.set(smallName);
		this.sBigClass.set(sBigClass);
	}

	

	public SmallClass(int smallClass) {
		this.smallClass.set(smallClass);
	}



	public SmallClass() {}

	
	
	public SmallClass(int smallClass, String smallName) {
		this.smallClass.set(smallClass);
		this.smallName.set(smallName);
	}



	public int getSmallClass() {
		return smallClass.get();
	}

	public IntegerProperty getSmallClassProperty() {
		return smallClass;
	}
	
	public void setSmallClass(int smallClass) {
		this.smallClass.set(smallClass);
	}

	public String getSmallName() {
		return smallName.get();
	}
	
	public StringProperty getSmallNameProperty() {
		return smallName;
	}

	public void setSmallName(String smallName) {
		this.smallName.set(smallName);
	}
	
	public Integer getsBigClass() {
		return sBigClass.get();
	}
	
	public IntegerProperty getsBigClassProperty() {
		return sBigClass;
	}

	public void setsBigClass(int sBigClass) {
		this.sBigClass.set(sBigClass);
	}

	@Override
	public String toString() {
		return String.format("%s",  smallName.get());
	}

}
