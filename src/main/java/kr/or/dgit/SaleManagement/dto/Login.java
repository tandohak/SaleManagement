package kr.or.dgit.SaleManagement.dto;

public class Login {
	private String id;
	private String pw;
	private boolean admit;
	private int code;
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Login(String id, String pw, boolean admit, int code) {
		super();
		this.id = id;
		this.pw = pw;
		this.admit = admit;
		this.code = code;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public boolean isAdmit() {
		return admit;
	}
	public void setAdmit(boolean admit) {
		this.admit = admit;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return String.format("%s %s %s %s", id, pw, admit, code);
	}
	
	
	
}
