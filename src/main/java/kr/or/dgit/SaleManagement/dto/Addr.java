package kr.or.dgit.SaleManagement.dto;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Addr {
	private StringProperty zipCode = new SimpleStringProperty();
	private StringProperty sido = new SimpleStringProperty();
	private StringProperty sigungu = new SimpleStringProperty();
	private StringProperty doro = new SimpleStringProperty();
	private IntegerProperty building1 = new SimpleIntegerProperty();
	private IntegerProperty building2 = new SimpleIntegerProperty();	

	public Addr(String zipCode, String sido, String sigungu, String doro, int building1, int building2) {
		this.zipCode.set(zipCode);
		this.sido.set(sido);
		this.sigungu.set(sigungu);
		this.doro.set(doro);
		this.building1.set(building1);
		this.building2.set(building2);
	}

	public Addr() {}
	
	@Override
	public String toString() {
		return String.format("%s %s %s", sido, sigungu, doro);
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
