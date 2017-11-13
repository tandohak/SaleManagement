package kr.or.dgit.SaleManagement.dto;

import java.util.Arrays;

public class ProductImg {
	private int pdtCode;
	private byte[] pdtImg;

	public ProductImg() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductImg(int pdtCode, byte[] pdtImg) {
		super();
		this.pdtCode = pdtCode;
		this.pdtImg = pdtImg;
	}

	public int getPdtCode() {
		return pdtCode;
	}

	public void setPdtCode(int pdtCode) {
		this.pdtCode = pdtCode;
	}

	public byte[] getPdtImg() {
		return pdtImg;
	}

	public void setPdtImg(byte[] pdtImg) {
		this.pdtImg = pdtImg;
	}

	@Override
	public String toString() {
		return String.format("ProductImg [pdtCode=%s, pdtImg=%s]", pdtCode, Arrays.toString(pdtImg));
	}

}
