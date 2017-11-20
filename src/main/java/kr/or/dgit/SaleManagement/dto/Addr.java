package kr.or.dgit.SaleManagement.dto;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Addr {
	private StringProperty zipCode;
	private StringProperty sido;
	private StringProperty sigungu;
	private StringProperty doro;
	private IntegerProperty building1;
	private IntegerProperty building2;

	public Addr(String zipCode, String sido, String sigungu, String doro, int building1, int building2) {
		this.zipCode.set(zipCode);
		this.sido.set(sido);;
		this.sigungu.set(sigungu);;
		this.doro.set(doro);
		this.building1.set(building1);;
		this.building2.set(building2);;
	}

	public Addr() {}
	
	public String getAddrs() {
		String addr = sido.get() + " " + sigungu.get() + " " + doro.get() + " " + building1.get();
		if(building2.get() != 0) {
			addr += "-" + building2.get();
		}
		return addr;		
	}
	
	@Override
	public String toString() {
		return String.format("UserAddr [zipCode=%s, sido=%s, sigungu=%s, doro=%s, building1=%s, building2=%s]", zipCode,
				sido, sigungu, doro, building1, building2);
	}

	public String getZipCode() {
		return zipCode.get();
	}

	public void setZipCode(String zipCode) {
		this.zipCode.set(zipCode);
	}

	public String getSido() {
		return sido.get();
	}

	public void setSido(String sido) {
		this.sido.set(sido);
	}

	public String getSigungu() {
		return sigungu.get();
	}

	public void setSigungu(String sigungu) {
		this.sigungu.set(sigungu);
	}

	public String getDoro() {
		return doro.get();
	}

	public void setDoro(String doro) {
		this.doro.set(doro);
	}

	public int getBuilding1() {
		return building1.get();
	}

	public void setBuilding1(int building1) {
		this.building1.set(building1);
	}

	public int getBuilding2() {
		return building2.get();
	}

	public void setBuilding2(int building2) {
		this.building2.set(building2);
	}

}
