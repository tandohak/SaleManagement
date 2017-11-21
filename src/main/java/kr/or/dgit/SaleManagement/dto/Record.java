package kr.or.dgit.SaleManagement.dto;

import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Record {
	private IntegerProperty recNo = new SimpleIntegerProperty();
	private ObjectProperty<Product> rProductCode = new SimpleObjectProperty<>();
	private ObjectProperty<Sales> rSalecode = new SimpleObjectProperty<>();
	private ObjectProperty<Date> recDate = new SimpleObjectProperty<>();
	private IntegerProperty recDisprice = new SimpleIntegerProperty();
	private IntegerProperty recDisrate = new SimpleIntegerProperty();
	private IntegerProperty recCount = new SimpleIntegerProperty();

	public Record() {}

	public int getRecNo() {
		return recNo.get();
	}
	
	public IntegerProperty getRecNoProperty() {
		return recNo;
	}

	public void setRecNo(int recNo) {
		this.recNo.set(recNo); 
	}

	public Product getrProductCode() {
		return rProductCode.get();
	}
	
	public ObjectProperty<Product> getrProductCodeProperty() {
		return rProductCode;
	}

	public void setrProductCode(Product rProductCode) {
		this.rProductCode.set(rProductCode);
	}

	public Sales getrSalecode() {
		return rSalecode.get();
	}
	
	public ObjectProperty<Sales> getrSalecodeProperty() {
		return rSalecode;
	}

	public void setrSalecode(Sales rSalecode) {
		this.rSalecode.set(rSalecode);
	}

	public Date getRecDate() {
		return recDate.get();
	}
	
	public ObjectProperty<Date> getRecDateProperty() {
		return recDate;
	}

	public void setRecDate(Date recDate) {
		this.recDate.set(recDate);
	}

	public int getRecDisprice() {
		return recDisprice.get();
	}
	
	public IntegerProperty getRecDispriceProperty() {
		return recDisprice;
	}

	public void setRecDisprice(int recDisprice) {
		this.recDisprice.set(recDisprice);
	}

	public int getRecDisrate() {
		return recDisrate.get();
	}
	
	public IntegerProperty getRecDisrateProperty() {
		return recDisrate;
	}

	public void setRecDisrate(int recDisrate) {
		this.recDisrate.set(recDisrate);;
	}

	public int getRecCount() {
		return recCount.get();
	}
	
	public IntegerProperty getRecCountProperty() {
		return recCount;
	}

	public void setRecCount(int recCount) {
		this.recCount.set(recCount);
	}

	@Override
	public String toString() {
		return String.format(
				"Record [recNo=%s, rProductCode=%s, rSalecode=%s, recDate=%s, recDisprice=%s, recDisrate=%s, recCount=%s]",
				recNo, rProductCode, rSalecode, recDate, recDisprice, recDisrate, recCount);
	}

}
