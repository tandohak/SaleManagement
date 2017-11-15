package kr.or.dgit.SaleManagement.dao;

import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.SaleManagement.dto.Account;

public class AccountDaoImpl implements AccountDao {
	private SqlSession sqlSession;
	private static final Log log = LogFactory.getLog(AccountDao.class);
	private static String namespace = "kr.or.dgit.SaleManagement.dao.AccountDao.";

	public AccountDaoImpl(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}

	@Override
	public Account selectAccountByCode(Account account) {
		log.debug("selectAccountByCode()");
		return sqlSession.getMapper(AccountDao.class).selectAccountByCode(account);
	}

	@Override
	public List<Account> selectAllAccount() {
		log.debug("selectAllAccount()");
		return sqlSession.getMapper(AccountDao.class).selectAllAccount();
	}

	@Override
	public int insertAccount(Account account) {
		log.debug("insertAccount()");
		return sqlSession.insert(namespace+"insertAccount", account);
	}

}
