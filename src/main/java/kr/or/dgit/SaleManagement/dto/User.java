package kr.or.dgit.SaleManagement.dto;

public class User {
	private String userId;
	private String userPw;
	private UserCategory categoryNo;

	public User() {

	}
	
	public User(String userId) {
		this.userId = userId;
	}

	public User(String userId, String userPw, UserCategory categoryNo) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.categoryNo = categoryNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public UserCategory getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(UserCategory categoryNo) {
		this.categoryNo = categoryNo;
	}

	@Override
	public String toString() {
		return String.format("User [ID:%s, PW:%s, %s]", userId, userPw, categoryNo);
	}

}
