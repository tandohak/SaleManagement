package kr.or.dgit.SaleManagement.dao;

import java.util.List;

import kr.or.dgit.SaleManagement.dto.Account;

public interface AccountDao {
	public Account selectAccountByCode(Account account);
	public List<Account> selectAllAccount();
	public int insertAccount(Account account);
}
