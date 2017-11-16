package kr.or.dgit.SaleManagement.dto;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Account {
	private IntegerProperty accCode = new SimpleIntegerProperty();
	private StringProperty accName = new SimpleStringProperty();
	private StringProperty accTel = new SimpleStringProperty();
	private StringProperty accAddr = new SimpleStringProperty();
	private StringProperty accAdmit = new SimpleStringProperty();
	private StringProperty accLevel = new SimpleStringProperty();
	private StringProperty accId = new SimpleStringProperty();
	private StringProperty accPw = new SimpleStringProperty();

	public Account() {}

	

	public int getAccCode() {
		return accCode.get();
	}

	public IntegerProperty getAccCodeProperty() {
		return accCode;
	}

	public void setAccCode(int accCode) {
		this.accCode.set(accCode);
	}

	public String getAccName() {
		return accName.get();
	}

	public StringProperty getAccNameProperty() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName.set(accName);;
	}

	public String getAccTel() {
		return accTel.get();
	}

	public StringProperty getAccTelProperty() {
		return accTel;
	}

	public void setAccTel(String accTel) {
		this.accTel.set(accTel);
	}



	public String getAccAddr() {
		return accAddr.get();
	}

	public StringProperty getAccAddrProperty() {
		return accAddr;
	}

	public void setAccAddr(String accAddr) {
		this.accAddr.set(accAddr);
	}



	public String getAccAdmit() {
		return accAdmit.get();
	}

	public StringProperty getAccAdmitProperty() {
		return accAdmit;
	}


	public void setAccAdmit(String accAdmit) {
		this.accAdmit.set(accAdmit);;
	}
	


	public String getAccLevel() {
		return accLevel.get();
	}

	public StringProperty getAccLevelProperty() {
		return accLevel;
	}
	
	public void setAccLevel(String accLevel) {
		this.accLevel.set(accLevel);
	}



	public String getAccId() {
		return accId.get();
	}

	public StringProperty getAccIdProperty() {
		return accId;
	}

	public void setAccId(String accId) {
		this.accId.set(accId);
	}



	public String getAccPw() {
		return accPw.get();
	}

	public StringProperty getAccPwProperty() {
		return accPw;
	}
	
	public void setAccPw(String accPw) {
		this.accPw.set(accPw);;
	}



	@Override
	public String toString() {
		return String.format(
				"Account [accCode=%s, accName=%s, accTel=%s, accAddr=%s, accAdmit=%s, accLevel=%s, accId=%s, accPw=%s]",
				accCode, accName, accTel, accAddr, accAdmit, accLevel, accId, accPw);
	}

}
