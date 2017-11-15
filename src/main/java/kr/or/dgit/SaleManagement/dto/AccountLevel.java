package kr.or.dgit.SaleManagement.dto;

public class AccountLevel {
	private String accLevel;
	private int accDisrate;

	public AccountLevel() {
		super();
	}
	
	public AccountLevel(String accLevel) {
		this.accLevel = accLevel;
	}

	public String getAccLevel() {
		return accLevel;
	}

	public void setAccLevel(String accLevel) {
		this.accLevel = accLevel;
	}

	public int getAccDisrate() {
		return accDisrate;
	}

	public void setAccDisrate(int accDisrate) {
		this.accDisrate = accDisrate;
	}

	@Override
	public String toString() {
		return String.format("AccountLevel [accLevel=%s, accDisrate=%s]", accLevel, accDisrate);
	}

}
