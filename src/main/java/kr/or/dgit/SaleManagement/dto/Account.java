package kr.or.dgit.SaleManagement.dto;

public class Account {
	private int accCode;
	private String accName;
	private String accTel;
	private String accAddr;
	private String accAdmit;
	private String accLevel;
	private String accId;
	private String accPw;

	public Account() {
		super();
		// TODO Auto-generated constructor stub
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



	public String getAccAddr() {
		return accAddr;
	}



	public void setAccAddr(String accAddr) {
		this.accAddr = accAddr;
	}



	public String getAccAdmit() {
		return accAdmit;
	}



	public void setAccAdmit(String accAdmit) {
		this.accAdmit = accAdmit;
	}



	public String getAccLevel() {
		return accLevel;
	}



	public void setAccLevel(String accLevel) {
		this.accLevel = accLevel;
	}



	public String getAccId() {
		return accId;
	}



	public void setAccId(String accId) {
		this.accId = accId;
	}



	public String getAccPw() {
		return accPw;
	}



	public void setAccPw(String accPw) {
		this.accPw = accPw;
	}



	@Override
	public String toString() {
		return String.format(
				"Account [accCode=%s, accName=%s, accTel=%s, accAddr=%s, accAdmit=%s, accLevel=%s, accId=%s, accPw=%s]",
				accCode, accName, accTel, accAddr, accAdmit, accLevel, accId, accPw);
	}

}
