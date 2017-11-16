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
	public Account selectAccountByName(Account account) {
		log.debug("selectAccountByName()");
		return sqlSession.getMapper(AccountDao.class).selectAccountByName(account);
	}

	@Override
	public List<Account> selectAccountByLevel(Account account) {
		log.debug("selectAccountByLevel()");
		return sqlSession.getMapper(AccountDao.class).selectAccountByLevel(account);
	}

	@Override
	public List<Account> selectAccountByAllAdmit(Account account) {
		log.debug("selectAccountByAllAdmit()");
		return sqlSession.getMapper(AccountDao.class).selectAccountByAllAdmit(account);
	}

	@Override
	public List<Account> selectAccountByAll() {
		log.debug("selectAllAccount()");
		return sqlSession.getMapper(AccountDao.class).selectAccountByAll();
	}

	@Override
	public int insertAccount(Account account) {
		log.debug("insertAccount()");
		return sqlSession.insert(namespace + "insertAccount", account);
	}

	@Override
	public int updateAccount(Account account) {
		log.debug("updateAccount()");
		return sqlSession.update(namespace + "updateAccount", account);
	}

	@Override
	public Account selectAccountById(Account account) {
		log.debug("selectAccountById()");
		return sqlSession.getMapper(AccountDao.class).selectAccountById(account);
	}

}
