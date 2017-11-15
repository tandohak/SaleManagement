package kr.or.dgit.SaleManagement.dto;

public class SalesLevel {
	private String salesLevel;
	private int salesDisrate;

	public SalesLevel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SalesLevel(String salesLevel) {
		this.salesLevel = salesLevel;
	}

	public String getSalesLevel() {
		return salesLevel;
	}

	public void setSalesLevel(String salesLevel) {
		this.salesLevel = salesLevel;
	}

	public int getSalesDisrate() {
		return salesDisrate;
	}

	public void setSalesDisrate(int salesDisrate) {
		this.salesDisrate = salesDisrate;
	}

	@Override
	public String toString() {
		return String.format("SalesLevel [salesLevel=%s, salesDisrate=%s]", salesLevel, salesDisrate);
	}

}
