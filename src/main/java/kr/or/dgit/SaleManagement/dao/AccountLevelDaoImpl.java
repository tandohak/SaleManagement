package kr.or.dgit.SaleManagement.dao;

import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.SaleManagement.dto.AccountLevel;

public class AccountLevelDaoImpl implements AccountLevelDao{
	private SqlSession sqlSession;
	private static final Log log = LogFactory.getLog(AccountLevelDao.class);
	private static String namespace = "kr.or.dgit.SaleManagement.dao.AccountLevelDao.";
	
	public AccountLevelDaoImpl(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}
	
	@Override
	public List<AccountLevel> selectAccountLevelByAll() {
		log.debug("selectAccountLevelByAll()");
		return sqlSession.selectList(namespace+"selectAccountLevelByAll");
	}

	@Override
	public AccountLevel selectAccountLevelByOne(AccountLevel accountLevel) {
		log.debug("selectAccountLevelByOne()");
		return sqlSession.selectOne(namespace+"selectAccountLevelByOne",accountLevel);
	}
	
}
