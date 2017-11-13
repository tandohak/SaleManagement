package kr.or.dgit.SaleManagement.dto;

public class Sales {
	private int saleCode;
	private String saleName;
	private String saleTel;
	private UserAddr saleAddr;
	private boolean saleLeave;
	private User saleId;
	private SalesLevel saleLevel;

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

	public UserAddr getSaleAddr() {
		return saleAddr;
	}

	public void setSaleAddr(UserAddr saleAddr) {
		this.saleAddr = saleAddr;
	}

	public boolean isSaleLeave() {
		return saleLeave;
	}

	public void setSaleLeave(boolean saleLeave) {
		this.saleLeave = saleLeave;
	}

	public User getSaleId() {
		return saleId;
	}

	public void setSaleId(User saleId) {
		this.saleId = saleId;
	}

	public SalesLevel getSaleLevel() {
		return saleLevel;
	}

	public void setSaleLevel(SalesLevel saleLevel) {
		this.saleLevel = saleLevel;
	}

	@Override
	public String toString() {
		return String.format("Sales [Code=%s, Name=%s, Tel=%s, Addr=%s, Leave=%s, Id=%s, Level=%s]", saleCode, saleName,
				saleTel, saleAddr, saleLeave, saleId, saleLevel);
	}

}
