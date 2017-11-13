package kr.or.dgit.SaleManagement.dto;

public class RowClass {
	private int rowClass;
	private String rowName;
	private BigClass bigClass;

	public RowClass() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getRowClass() {
		return rowClass;
	}

	public void setRowClass(int rowClass) {
		this.rowClass = rowClass;
	}

	public String getRowName() {
		return rowName;
	}

	public void setRowName(String rowName) {
		this.rowName = rowName;
	}

	public BigClass getBigClass() {
		return bigClass;
	}

	public void setBigClass(BigClass bigClass) {
		this.bigClass = bigClass;
	}

	@Override
	public String toString() {
		return String.format("RowClass [rowClass=%s, rowName=%s, bigClass=%s]", rowClass, rowName, bigClass);
	}

}
