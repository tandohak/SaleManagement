package kr.or.dgit.SaleManagement.dao;

import java.util.List;

import kr.or.dgit.SaleManagement.dto.Login;

public interface LoginDao {
	
	public List<Login> selectAll();
	public Login selectSalesId(Login login);
}
