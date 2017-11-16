package kr.or.dgit.SaleManagement.dto;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BigClass {
	private IntegerProperty bigClass  = new SimpleIntegerProperty();;
	private StringProperty bigName = new SimpleStringProperty();

	public BigClass() {}

	public BigClass(int bigClass, String bigName) {
		this.bigClass.set(bigClass);
		this.bigName.set(bigName);
	}
	
	public BigClass(int bigClass) {
		this.bigClass.set(bigClass);
	}

	public BigClass(String bigName) {
		this.bigName.set(bigName);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bigName == null) ? 0 : bigName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BigClass other = (BigClass) obj;
		if (bigName == null) {
			if (other.bigName != null)
				return false;
		} else if (!bigName.equals(other.bigName))
			return false;
		return true;
	}

	public int getBigClass() {
		return bigClass.get();
	}
	
	public IntegerProperty getBigClassProperty() {
		return bigClass;
	}	

	public void setBigClass(int bigClass) {
		this.bigClass.set(bigClass);
	}

	public String getBigName() {
		return bigName.get();
	}
	
	public StringProperty getBigNameProperty() {
		return bigName;
	}

	public void setBigName(String bigName) {
		this.bigName.set(bigName);
	}

	@Override
	public String toString() {
		return String.format("BigClass [bigClass=%s, bigName=%s]", bigClass, bigName);
	}

}
