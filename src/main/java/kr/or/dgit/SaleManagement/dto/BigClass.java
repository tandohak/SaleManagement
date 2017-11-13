package kr.or.dgit.SaleManagement.dto;

public class BigClass {
	private int bigClass;
	private String bigName;

	public BigClass() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getBigClass() {
		return bigClass;
	}

	public BigClass(int bigClass, String bigName) {
		super();
		this.bigClass = bigClass;
		this.bigName = bigName;
	}

	public void setBigClass(int bigClass) {
		this.bigClass = bigClass;
	}

	public String getBigName() {
		return bigName;
	}

	public void setBigName(String bigName) {
		this.bigName = bigName;
	}

	@Override
	public String toString() {
		return String.format("BigClass [bigClass=%s, bigName=%s]", bigClass, bigName);
	}

}
