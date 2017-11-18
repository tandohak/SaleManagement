package kr.or.dgit.SaleManagement.dao;

import java.util.List;

import kr.or.dgit.SaleManagement.dto.Account;

public interface AccountDao {
	Account selectAccountByCode(Account account);
	Account selectAccountByName(Account account);
	Account selectAccountById(Account account);
	List<Account> selectAccountByLevel(Account account);
	List<Account> selectAccountByAllAdmit(Account account);
	List<Account> selectAccountByAll();
	
	int insertAccount(Account account);
	
	int updateAccount(Account account);	
	
	int selectMaxCode();
}
