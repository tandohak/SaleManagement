package kr.or.dgit.SaleManagement.dto;

public class UserAddr {
	private int addrNo;
	private String zipCode;
	private String sido;
	private String sigungu;
	private String doro;
	private int building1;
	private int building2;
	public UserAddr(int addrNo, String zipCode, String sido, String sigungu, String doro, int building1,
			int building2) {
		super();
		this.addrNo = addrNo;
		this.zipCode = zipCode;
		this.sido = sido;
		this.sigungu = sigungu;
		this.doro = doro;
		this.building1 = building1;
		this.building2 = building2;
	}
	public UserAddr() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return String.format(
				"UserAddr [addrNo=%s, zipCode=%s, sido=%s, sigungu=%s, doro=%s, building1=%s, building2=%s]", addrNo,
				zipCode, sido, sigungu, doro, building1, building2);
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
	
	
}
