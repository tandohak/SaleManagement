package kr.or.dgit.SaleManagement.dto;

public class BigClass {
	private int bigClass;
	private String bigName;

	public BigClass() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bigName == null) ? 0 : bigName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BigClass other = (BigClass) obj;
		if (bigName == null) {
			if (other.bigName != null)
				return false;
		} else if (!bigName.equals(other.bigName))
			return false;
		return true;
	}

	public int getBigClass() {
		return bigClass;
	}

	public BigClass(int bigClass, String bigName) {
		super();
		this.bigClass = bigClass;
		this.bigName = bigName;
	}

	public BigClass(int bigClass) {
		super();
		this.bigClass = bigClass;
	}

	public BigClass(String bigName) {
		super();
		this.bigName = bigName;
	}

	public void setBigClass(int bigClass) {
		this.bigClass = bigClass;
	}

	public String getBigName() {
		return bigName;
	}

	public void setBigName(String bigName) {
		this.bigName = bigName;
	}

	@Override
	public String toString() {
		return String.format("BigClass [bigClass=%s, bigName=%s]", bigClass, bigName);
	}

}
