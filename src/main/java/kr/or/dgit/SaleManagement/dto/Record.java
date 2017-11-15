package kr.or.dgit.SaleManagement.dto;

import java.util.Date;

public class Record {
	private int recNo;
	private Product rProductCode;
	private Sales rSalecode;
	private Date recDate;
	private int recDisprice;
	private int recDisrate;
	private int recCount;

	public Record() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getRecNo() {
		return recNo;
	}

	public void setRecNo(int recNo) {
		this.recNo = recNo;
	}

	public Product getrProductCode() {
		return rProductCode;
	}

	public void setrProductCode(Product rProductCode) {
		this.rProductCode = rProductCode;
	}

	public Sales getrSalecode() {
		return rSalecode;
	}

	public void setrSalecode(Sales rSalecode) {
		this.rSalecode = rSalecode;
	}

	public Date getRecDate() {
		return recDate;
	}

	public void setRecDate(Date recDate) {
		this.recDate = recDate;
	}

	public int getRecDisprice() {
		return recDisprice;
	}

	public void setRecDisprice(int recDisprice) {
		this.recDisprice = recDisprice;
	}

	public int getRecDisrate() {
		return recDisrate;
	}

	public void setRecDisrate(int recDisrate) {
		this.recDisrate = recDisrate;
	}

	public int getRecCount() {
		return recCount;
	}

	public void setRecCount(int recCount) {
		this.recCount = recCount;
	}

	@Override
	public String toString() {
		return String.format(
				"Record [recNo=%s, rProductCode=%s, rSalecode=%s, recDate=%s, recDisprice=%s, recDisrate=%s, recCount=%s]",
				recNo, rProductCode, rSalecode, recDate, recDisprice, recDisrate, recCount);
	}

}
