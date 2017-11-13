package kr.or.dgit.SaleManagement.dto;

public class UserAddress {
	private int addrNo;
	private String zipCode;
	private String sido;
	private String sigungu;
	private String doro;
	private int building1;
	private int building2;
	public UserAddress() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getAddrNo() {
		return addrNo;
	}
	public void setAddrNo(int addrNo) {
		this.addrNo = addrNo;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getSido() {
		return sido;
	}
	public void setSido(String sido) {
		this.sido = sido;
	}
	public String getSigungu() {
		return sigungu;
	}
	public void setSigungu(String sigungu) {
		this.sigungu = sigungu;
	}
	public String getDoro() {
		return doro;
	}
	public void setDoro(String doro) {
		this.doro = doro;
	}
	public int getBuilding1() {
		return building1;
	}
	public void setBuilding1(int building1) {
		this.building1 = building1;
	}
	public int getBuilding2() {
		return building2;
	}
	public void setBuilding2(int building2) {
		this.building2 = building2;
	}
	@Override
	public String toString() {
		return String.format("Address [No=%s, %s, %s, %s, %s, %s, %s]", addrNo, zipCode, sido, sigungu, doro, building1, building2);
	}
	
	
}
