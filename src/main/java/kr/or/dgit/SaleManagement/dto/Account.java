package kr.or.dgit.SaleManagement.dto;

public class Account {
	private int accCode;
	private String accName;
	private String accTel;
	private UserAddr accAddr;
	private boolean accAdmit;
	private User accId;
	private AccountLevel accLevel;

	public Account() {
		super();
	}

	public int getAccCode() {
		return accCode;
	}

	public void setAccCode(int accCode) {
		this.accCode = accCode;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public String getAccTel() {
		return accTel;
	}

	public void setAccTel(String accTel) {
		this.accTel = accTel;
	}

	public boolean isAccAdmit() {
		return accAdmit;
	}

	public void setAccAdmit(boolean accAdmit) {
		this.accAdmit = accAdmit;
	}

	public UserAddr getAccAddr() {
		return accAddr;
	}

	public void setAccAddr(UserAddr accAddr) {
		this.accAddr = accAddr;
	}

	public User getAccId() {
		return accId;
	}

	public void setAccId(User accId) {
		this.accId = accId;
	}

	public AccountLevel getAccLevel() {
		return accLevel;
	}

	public void setAccLevel(AccountLevel accLevel) {
		this.accLevel = accLevel;
	}

	@Override
	public String toString() {
		return String.format("Account [Code=%s, Name=%s, Tel=%s, Addr=%s, Admit=%s, Id=%s, Level=%s]", accCode, accName,
				accTel, accAddr, accAdmit, accId, accLevel);
	}

}
