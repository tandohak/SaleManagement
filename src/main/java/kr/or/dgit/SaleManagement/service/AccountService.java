package kr.or.dgit.SaleManagement.service;
 
import java.util.List;
 
import org.apache.ibatis.session.SqlSession;
 
import kr.or.dgit.SaleManagement.dao.AccountDao;
import kr.or.dgit.SaleManagement.dao.AccountDaoImpl;
import kr.or.dgit.SaleManagement.dto.Account;
import kr.or.dgit.SaleManagement.util.MyBatisSqlSessionFactory;
 
public class AccountService {
	private final static AccountService instance = new AccountService();
 
	public static AccountService getInstance() {
		return instance;
	}
	
	public Account findAccountByCode(Account account) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			AccountDao dao = new AccountDaoImpl(sqlSession);
			return dao.selectAccountByCode(account);
		}
	}
 
	public Account findAccountByName(Account account) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			AccountDao dao = new AccountDaoImpl(sqlSession);
			return dao.selectAccountByName(account);
		}
	}
 
	public Account findAccountById(Account account) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			AccountDao dao = new AccountDaoImpl(sqlSession);
			return dao.selectAccountById(account);
		}
	}
 
	public List<Account> findAccountByLevel(Account account) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			AccountDao dao = new AccountDaoImpl(sqlSession);
			return dao.selectAccountByLevel(account);
		}
	}
 
	public List<Account> findAllAdmitAccount(Account account) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			AccountDao dao = new AccountDaoImpl(sqlSession);
			return dao.selectAccountByAllAdmit(account);
		}
	}
 
	public List<Account> findAllAccount() {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			AccountDao dao = new AccountDaoImpl(sqlSession);
			return dao.selectAccountByAll();
		}
	}
 
	public int insertAccount(Account account) {
		int res = -1;
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			AccountDao dao = new AccountDaoImpl(sqlSession);
			res = dao.insertAccount(account);
			sqlSession.commit();
		}
		return res;
	}
 
	public int updateAccount(Account account) {
		int res = -1;
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			AccountDao dao = new AccountDaoImpl(sqlSession);
			res = dao.updateAccount(account);
			sqlSession.commit();
		}
		return res;
	}
	
	public int findMaxCode() {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			AccountDao dao = new AccountDaoImpl(sqlSession);
			return dao.selectMaxCode();
		}
	}
	
	public List<Account> findAccountLikeName(Account account){
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			AccountDao dao = new AccountDaoImpl(sqlSession);
			return dao.selectAccountLikeName(account);
		}
	}
}
