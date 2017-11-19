package kr.or.dgit.SaleManagement.dto;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SalesLevel {
	private StringProperty salLevel = new SimpleStringProperty();
	private IntegerProperty salDisrate = new SimpleIntegerProperty();	
	
	
	
	public SalesLevel() {}

	
	
	public SalesLevel(String salLevel) {
		this.salLevel.set(salLevel);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((salLevel.get() == null) ? 0 : salLevel.get().hashCode());
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
		SalesLevel other = (SalesLevel) obj;
		if (salLevel == null) {
			if (other.salLevel != null)
				return false;
		} else if (!salLevel.equals(other.salLevel))
			return false;
		return true;
	}



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
