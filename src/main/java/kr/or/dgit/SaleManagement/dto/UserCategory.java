package kr.or.dgit.SaleManagement.dto;

public class UserCategory {
	private int categoryNo;
	private String categoryName;

	public UserCategory() {
		super();
	}
		
	public UserCategory(int categoryNo) {
		this.categoryNo = categoryNo;
	}

	public int getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return String.format("UserCategory [%s, %s]", categoryNo, categoryName);
	}

}
