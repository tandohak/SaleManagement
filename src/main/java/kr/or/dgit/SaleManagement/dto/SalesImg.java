package kr.or.dgit.SaleManagement.dto;

import java.util.Arrays;

public class SalesImg {
	private int saleCode;
	private byte[] saleImg;

	public int getSaleCode() {
		return saleCode;
	}

	public SalesImg(int saleCode, byte[] saleImg) {
		super();
		this.saleCode = saleCode;
		this.saleImg = saleImg;
	}

	public void setSaleCode(int saleCode) {
		this.saleCode = saleCode;
	}

	public byte[] getSaleImg() {
		return saleImg;
	}

	public void setSaleImg(byte[] saleImg) {
		this.saleImg = saleImg;
	}

	public SalesImg() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return String.format("SalesImg [saleCode=%s, saleImg=%s]", saleCode, Arrays.toString(saleImg));
	}

}
