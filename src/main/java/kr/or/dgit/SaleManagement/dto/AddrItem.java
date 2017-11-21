package kr.or.dgit.SaleManagement.dto;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AddrItem {
	private StringProperty addrZip = new SimpleStringProperty();
	private StringProperty addr = new SimpleStringProperty();
	
	public StringProperty getAddrZipProperty() {
		return addrZip;
	}
	
	public String getAddrZip() {
		return addrZip.get();
	}
	public void setAddrZip(String addrZip) {
		this.addrZip.set(addrZip);
	}
	public StringProperty getAddrProperty() {
		return addr;
	}
	
	public String getAddr() {
		return addr.get();
	}
	
	public void setAddr(String addr) {
		this.addr.set(addr);;
	}

	@Override
	public String toString() {
		return String.format("AddrItem [addrZip=%s, addr=%s]", addrZip, addr);
	}
	
	
	
}
