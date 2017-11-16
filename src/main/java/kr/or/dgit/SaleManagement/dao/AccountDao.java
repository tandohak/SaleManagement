package kr.or.dgit.SaleManagement.dao;

import java.util.List;

import kr.or.dgit.SaleManagement.dto.Account;

public interface AccountDao {
	public Account selectAccountByCode(Account account);
	public Account selectAccountByName(Account account);
	public Account selectAccountById(Account account);
	public List<Account> selectAccountByLevel(Account account);
	public List<Account> selectAccountByAllAdmit(Account account);
	public List<Account> selectAccountByAll();
	
	public int insertAccount(Account account);
	
	public int updateAccount(Account account);	
}
