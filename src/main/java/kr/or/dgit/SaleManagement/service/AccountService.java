package kr.or.dgit.SaleManagement.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.SaleManagement.dao.AccountDao;
import kr.or.dgit.SaleManagement.dao.AccountDaoImpl;
import kr.or.dgit.SaleManagement.dto.Account;
import kr.or.dgit.SaleManagement.util.MyBatisSqlSessionFactory;

public class AccountService {
	
	public Account findAccountByCode(Account account) {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();){
			AccountDao dao = new AccountDaoImpl(sqlSession);
			return dao.selectAccountByCode(account);
		}
	}
	
	public List<Account> findAllAccount(){
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();){
			AccountDao dao = new AccountDaoImpl(sqlSession);
			return dao.selectAllAccount();
		}
	}
	
	public int insertAccount(Account account) {
		int res = -1;
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();){
			AccountDao dao = new AccountDaoImpl(sqlSession);
			res = dao.insertAccount(account);
			sqlSession.commit();
		}
		return res;
	}

}
