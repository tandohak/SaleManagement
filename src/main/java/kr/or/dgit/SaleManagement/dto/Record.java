package kr.or.dgit.SaleManagement.dto;

import java.time.LocalDate;
import java.util.Date;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class Record {
	private BooleanProperty checkedBox = new SimpleBooleanProperty(false);
	private IntegerProperty recNo = new SimpleIntegerProperty();
	private IntegerProperty rProductCode = new SimpleIntegerProperty();
	private IntegerProperty rSalecode = new SimpleIntegerProperty();
	private ObjectProperty<LocalDate> recDate = new SimpleObjectProperty<>();
	private IntegerProperty recDisprice = new SimpleIntegerProperty();
	private IntegerProperty recDisrate = new SimpleIntegerProperty();
	private IntegerProperty recCount = new SimpleIntegerProperty();
	
	private IntegerProperty recCost = new SimpleIntegerProperty();
	private IntegerProperty accCode = new SimpleIntegerProperty();
	private IntegerProperty recPrice = new SimpleIntegerProperty();
	private StringProperty accName = new SimpleStringProperty();
	private StringProperty pdtName = new SimpleStringProperty();
	private StringProperty saleName = new SimpleStringProperty();
	private IntegerProperty profit = new SimpleIntegerProperty();
	private IntegerProperty margin = new SimpleIntegerProperty();
	
	
	
	public IntegerProperty getMargin() {
		return margin;
	}
	public void setMargin(int margin) {
		this.margin.set(margin);
	}
	public IntegerProperty getMarginProperty() {
		return margin;
	}
	public IntegerProperty getProfit() {
		return profit;
	}
	public IntegerProperty getProfitProperty() {
		return profit;
	}

	public void setProfit(int profit) {
		this.profit.set(profit);
	}

	public IntegerProperty getaccCodeProperty() {
		return accCode;
	}

	public IntegerProperty getAccCode() {
		return accCode;
	}

	

	public Record() {}
	
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
	
	public String getSaleNamey() {
		return saleName.get();
	}
	public StringProperty getSaleNameProperty() {
		return saleName;
	}

	public void setSaleName(String saleName) {
		this.saleName.set(saleName);
	}
	
	public IntegerProperty getRecCostProperty() {
		return recCost;
	}
	public int getRecCost() {
		return recCost.get();
	}

	public void setRecCost(int recCost) {
		this.recCost.set(recCost);
	}
	
	public void setAccCode(int accCode) {
		this.accCode.set(accCode);
	}
	
	public int getRecPrice() {
		return recPrice.get();
	}
	
	public IntegerProperty getRecPriceProperty() {
		return recPrice;
	}

	public void setRecPrice(int recPrice) {
		this.recPrice.set(recPrice);
	}

	public String getAccName() {
		return accName.get();
	}
	
	public StringProperty getAccNameProperty() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName.set(accName);
	}

	public String getPdtName() {
		return pdtName.get();
	}
	
	public StringProperty getPdtNameProperty() {
		return pdtName;
	}

	public void setPdtName(String pdtName) {
		this.pdtName.set(pdtName); ;
	}

	public int getRecNo() {
		return recNo.get();
	}
	
	public IntegerProperty getRecNoProperty() {
		return recNo;
	}

	public void setRecNo(int recNo) {
		this.recNo.set(recNo); 
	}

	public int getrProductCode() {
		return rProductCode.get();
	}
	
	public IntegerProperty getrProductCodeProperty() {
		return rProductCode;
	}

	public void setrProductCode(int rProductCode) {
		this.rProductCode.set(rProductCode);
	}

	public int getrSalecode() {
		return rSalecode.get();
	}
	
	public IntegerProperty getrSalecodeProperty() {
		return rSalecode;
	}

	public void setrSalecode(int rSalecode) {
		this.rSalecode.set(rSalecode);
	}

	public LocalDate getRecDate() {
		return recDate.get();
	}
	
	public ObjectProperty<LocalDate> getRecDateProperty() {
		return recDate;
	}

	public void setRecDate(LocalDate recDate) {
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
