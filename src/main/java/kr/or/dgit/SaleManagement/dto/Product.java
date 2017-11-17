package kr.or.dgit.SaleManagement.dto;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class Product {
	private BooleanProperty checkedBox = new SimpleBooleanProperty(false);
	private IntegerProperty pdtCode = new SimpleIntegerProperty();
	private IntegerProperty pdtClass = new SimpleIntegerProperty();
	private StringProperty pdtName = new SimpleStringProperty();
	private IntegerProperty pdtCost = new SimpleIntegerProperty();
	private IntegerProperty pdtPrice = new SimpleIntegerProperty();
	private StringProperty pdtAdmit = new SimpleStringProperty();
	private IntegerProperty accCode = new SimpleIntegerProperty();
	
	
	public BooleanProperty getCheckedBoxProperty() {
		return checkedBox;
	}
	
	public Boolean getCheckedBox() {
		return checkedBox.get();
	}
	
	public void setCheckedBox(Boolean checkedBox) {
		this.checkedBox.set(checkedBox);
	}
	
	public ObservableValue<Boolean> selectedProperty() {
		return checkedBox;
	}
	
	public Product(int pdtCode, int pdtClass, String pdtName, int pdtCost, int pdtPrice, String pdtAdmit,
			int accCode) {
		this.pdtCode.set(pdtCode);
		this.pdtClass.set(pdtClass);
		this.pdtName.set(pdtName);
		this.pdtCost.set(pdtCost);
		this.pdtPrice.set(pdtPrice);
		this.pdtAdmit.set(pdtAdmit);
		this.accCode.set(accCode);
	}

	public Product() {}

	public int getPdtCode() {
		return pdtCode.get();
	}
	
	public IntegerProperty getPdtCodeProperty() {
		return pdtCode;
	}
	
	public void setPdtCode(int pdtCode) {
		this.pdtCode.set(pdtCode);
	}

	public int getPdtClass() {
		return pdtClass.get();
	}
	
	public IntegerProperty getPdtClassProperty() {
		return pdtClass;
	}
	
	public void setPdtClass(int pdtClass) {
		this.pdtClass.set(pdtClass);
	}

	public String getPdtName() {
		return pdtName.get();
	}
	
	public StringProperty getPdtNameProperty() {
		return pdtName;
	}
	
	public void setPdtName(String pdtName) {
		this.pdtName.set(pdtName);
	}

	public int getPdtCost() {
		return pdtCost.get();
	}
	
	public IntegerProperty getPdtCostProperty() {
		return pdtCost;
	}
	
	public void setPdtCost(int pdtCost) {
		this.pdtCost.set(pdtCost);
	}

	public int getPdtPrice() {
		return pdtPrice.get();
	}
	
	public IntegerProperty getPdtPriceProperty() {
		return pdtPrice;
	}
	
	public void setPdtPrice(int pdtPrice) {
		this.pdtPrice.set(pdtPrice);
	}

	public String getPdtAdmit() {
		return pdtAdmit.get();
	}
	
	public StringProperty getPdtAdmitProperty() {
		return pdtAdmit;
	}
	
	public void setPdtAdmit(String pdtAdmit) {
		this.pdtAdmit.set(pdtAdmit);
	}

	public int getAccCode() {
		return accCode.get();
	}
	
	public IntegerProperty getAccCodeProperty() {
		return accCode;
	}

	public void setAccCode(int accCode) {
		this.accCode.set(accCode);
	}

	@Override
	public String toString() {
		return String.format("Product [Code=%s, Class=%s, Name=%s, Cost=%s, Price=%s, Admit=%s, Code=%s]", pdtCode,
				pdtClass, pdtName, pdtCost, pdtPrice, pdtAdmit, accCode);
	}



}
