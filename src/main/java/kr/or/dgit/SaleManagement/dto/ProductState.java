package kr.or.dgit.SaleManagement.dto;

import java.text.DecimalFormat;

import javax.script.Compilable;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProductState {
	private IntegerProperty no = new SimpleIntegerProperty();
	private IntegerProperty code = new SimpleIntegerProperty();
	private StringProperty name = new SimpleStringProperty();
	private IntegerProperty amount = new SimpleIntegerProperty();
	private IntegerProperty priceAll = new SimpleIntegerProperty();
	private IntegerProperty costAll = new SimpleIntegerProperty();
	private IntegerProperty dispriceAll = new SimpleIntegerProperty();
	private IntegerProperty profit = new SimpleIntegerProperty();
	private IntegerProperty margin = new SimpleIntegerProperty();
	
	
	
	public int getNo() {
		return no.get();
	}
	public IntegerProperty getNoProperty() {
		return no;
	}
	public void setNo(int no) {
		this.no.set(no);;
	}
	public IntegerProperty getCodeProperty() {
		return code;
	}
	public int getCode() {
		return code.get();
	}
	public void setCode(int code) {
		this.code.set(code);;
	}
	public StringProperty getNameProperty() {
		return name;
	}
	public String getName() {
		return name.get().trim();
	}
	public void setName(String name) {
		this.name.set(name.trim());;
	}
	public IntegerProperty getAmountProperty() {
		return amount;
	}
	public int getAmount() {
		return amount.get();
	}
	public void setAmount(int amount) {
		this.amount.set(amount);
	}
	public IntegerProperty getPriceAllProperty() {
		return priceAll;
	}
	public int getPriceAll() {
		return priceAll.get();
	}
	public void setPriceAll(int priceAll) {
		this.priceAll.set(priceAll);;
	}
	public IntegerProperty getCostAllProperty() {
		return costAll;
	}
	public int getCostAll() {
		return costAll.get();
	}
	public void setCostAll(int costAll) {
		this.costAll.set(costAll);
	}
	public IntegerProperty getDispriceAllProperty() {
		return dispriceAll;
	}
	public int getDispriceAll() {
		return dispriceAll.get();
	}
	public void setDispriceAll(int dispriceAll) {
		this.dispriceAll.set(dispriceAll);
	}
	public IntegerProperty getProfitProperty() {
		return profit;
	}
	public int getProfit() {
		return profit.get();
	}
	public void setProfit(int profit) {
		this.profit.set(profit);
	}
	public int getMargin() {
		return margin.get();
	}
	public IntegerProperty getMarginProperty() {
		return margin;
	}
	public void setMargin(int margin) {
		this.margin.set(margin);;
	}
	
	//포맷타입 변환 메소드
	public StringProperty getFormatAmount() {
	   return ChangeFormat(amount.get(), "comma");
	}
	   
	public StringProperty getFormatPriceAll() {
	   return ChangeFormat(priceAll.get(), "comma");
	}
	   
	public StringProperty getFormatCostAll() {
	   return ChangeFormat(costAll.get(), "comma");
	}
	   
	public StringProperty getFormatDispriceAll() {
	   return ChangeFormat(dispriceAll.get(), "comma");
	}
	   
	public StringProperty getFormatMargin() {
	   return ChangeFormat(margin.get(), "per");
	}
	   
	public StringProperty getFormatProfit() {
	   return ChangeFormat(profit.get(), "comma");
	}
	
	public StringProperty ChangeFormat(int number, String type) {
	      StringProperty strReturn;
	      String str;
	      DecimalFormat commaFormat = new DecimalFormat("#,###");
	      if(type.equals("per")) {
	         str = number + " %";
	         strReturn = new SimpleStringProperty(str);
	         return strReturn;
	      }
	      if(type.equals("comma")) {
	         str = commaFormat.format(number);
	         strReturn = new SimpleStringProperty(str);
	         return strReturn;
	      }
	      return null;
	}
	@Override
	public String toString() {
		return String.format(
				"ProductState [code=%s, name=%s, amount=%s, priceAll=%s, costAll=%s, dispriceAll=%s, profit=%s, margin=%s]",
				code, name, amount, priceAll, costAll, dispriceAll, profit, margin);
	}
	
}
