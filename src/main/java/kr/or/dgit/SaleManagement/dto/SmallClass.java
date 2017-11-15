package kr.or.dgit.SaleManagement.dto;

public class SmallClass {
	private int smallClass;
	private String smallName;
	private int sBigClass;

	public SmallClass() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getSmallClass() {
		return smallClass;
	}

	public void setSmallClass(int smallClass) {
		this.smallClass = smallClass;
	}

	public String getSmallName() {
		return smallName;
	}

	public void setSmallName(String smallName) {
		this.smallName = smallName;
	}

	public int getsBigClass() {
		return sBigClass;
	}

	public void setsBigClass(int sBigClass) {
		this.sBigClass = sBigClass;
	}

	@Override
	public String toString() {
		return String.format("SmallClass [smallClass=%s, smallName=%s, sBigClass=%s]", smallClass, smallName,
				sBigClass);
	}

}
