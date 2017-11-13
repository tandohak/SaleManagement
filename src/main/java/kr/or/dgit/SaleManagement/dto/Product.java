package kr.or.dgit.SaleManagement.dto;

public class Product {
	private int pdtCode;
	private RowClass pdtClass;
	private String pdtName;
	private int pdtCost;
	private int pdtPrice;
	private boolean pdtAdmit;
	private Account accCode;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getPdtCode() {
		return pdtCode;
	}

	public void setPdtCode(int pdtCode) {
		this.pdtCode = pdtCode;
	}

	public RowClass getPdtClass() {
		return pdtClass;
	}

	public void setPdtClass(RowClass pdtClass) {
		this.pdtClass = pdtClass;
	}

	public String getPdtName() {
		return pdtName;
	}

	public void setPdtName(String pdtName) {
		this.pdtName = pdtName;
	}

	public int getPdtCost() {
		return pdtCost;
	}

	public void setPdtCost(int pdtCost) {
		this.pdtCost = pdtCost;
	}

	public int getPdtPrice() {
		return pdtPrice;
	}

	public void setPdtPrice(int pdtPrice) {
		this.pdtPrice = pdtPrice;
	}

	public boolean isPdtAdmit() {
		return pdtAdmit;
	}

	public void setPdtAdmit(boolean pdtAdmit) {
		this.pdtAdmit = pdtAdmit;
	}

	public Account getAccCode() {
		return accCode;
	}

	public void setAccCode(Account accCode) {
		this.accCode = accCode;
	}

	@Override
	public String toString() {
		return String.format("Product [Code=%s, Class=%s, Name=%s, Cost=%s, Price=%s, Admit=%s, Code=%s]", pdtCode,
				pdtClass, pdtName, pdtCost, pdtPrice, pdtAdmit, accCode);
	}

}
