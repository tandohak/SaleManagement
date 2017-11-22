package kr.or.dgit.SaleManagement.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.SaleManagement.dao.AccountLevelDao;
import kr.or.dgit.SaleManagement.dao.AccountLevelDaoImpl;
import kr.or.dgit.SaleManagement.dto.AccountLevel;
import kr.or.dgit.SaleManagement.util.MyBatisSqlSessionFactory;

public class AccountLevelService {
	private final static AccountLevelService instance = new AccountLevelService();

	public static AccountLevelService getInstance() {
		return instance;
	}
	
	public List<AccountLevel> findAllAccountLevel() {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			AccountLevelDao dao = new AccountLevelDaoImpl(sqlSession);
			return dao.selectAccountLevelByAll();
		}
	}

	public AccountLevel findOneAccount(AccountLevel accountLevel) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			AccountLevelDao dao = new AccountLevelDaoImpl(sqlSession);
			return dao.selectAccountLevelByOne(accountLevel);
		}
	}
}
