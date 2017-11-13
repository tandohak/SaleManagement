package kr.or.dgit.SaleManagement.dto;

import java.util.Date;

public class Record {
	private int recNo;
	private Product pdtCode;
	private Sales salecode;
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

	public Product getPdtCode() {
		return pdtCode;
	}

	public void setPdtCode(Product pdtCode) {
		this.pdtCode = pdtCode;
	}

	public Sales getSalecode() {
		return salecode;
	}

	public void setSalecode(Sales salecode) {
		this.salecode = salecode;
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
				"Record [recNo=%s, pdtCode=%s, salecode=%s, recDate=%s, recDisprice=%s, recDisrate=%s, recCount=%s]",
				recNo, pdtCode, salecode, recDate, recDisprice, recDisrate, recCount);
	}

}
