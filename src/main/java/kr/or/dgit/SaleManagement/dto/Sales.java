package kr.or.dgit.SaleManagement.dto;

public class Sales {
	private int saleCode;
	private String saleName;
	private String saleTel;
	private String saleAddr;
	private String saleLeave;
	private String saleId;
	private String salePw;
	private SalesLevel saleLevel;

	public Sales() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getSaleCode() {
		return saleCode;
	}

	public void setSaleCode(int saleCode) {
		this.saleCode = saleCode;
	}

	public String getSaleName() {
		return saleName;
	}

	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}

	public String getSaleTel() {
		return saleTel;
	}

	public void setSaleTel(String saleTel) {
		this.saleTel = saleTel;
	}

	public String getSaleAddr() {
		return saleAddr;
	}

	public void setSaleAddr(String saleAddr) {
		this.saleAddr = saleAddr;
	}

	public String getSaleLeave() {
		return saleLeave;
	}

	public void setSaleLeave(String saleLeave) {
		this.saleLeave = saleLeave;
	}

	public String getSaleId() {
		return saleId;
	}

	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}

	public String getSalePw() {
		return salePw;
	}

	public void setSalePw(String salePw) {
		this.salePw = salePw;
	}

	public SalesLevel getSaleLevel() {
		return saleLevel;
	}

	public void setSaleLevel(SalesLevel saleLevel) {
		this.saleLevel = saleLevel;
	}

	@Override
	public String toString() {
		return String.format(
				"Sales [saleCode=%s, saleName=%s, saleTel=%s, saleAddr=%s, saleLeave=%s, saleId=%s, salePw=%s, saleLevel=%s]",
				saleCode, saleName, saleTel, saleAddr, saleLeave, saleId, salePw, saleLevel);
	}

}
