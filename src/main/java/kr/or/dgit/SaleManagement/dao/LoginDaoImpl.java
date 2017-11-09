package kr.or.dgit.SaleManagement.dao;

import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.SaleManagement.dto.Login;

public class LoginDaoImpl implements LoginDao {

	private SqlSession sqlSession;
	
	private static final Log log = LogFactory.getLog(LoginDao.class);

	public LoginDaoImpl(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}
	
	@Override
	public List<Login> selectAll() {
		log.debug("selectAll()");
		return sqlSession.getMapper(LoginDao.class).selectAll();
	}
	@Override
	public Login selectSalesId(Login login) {
		log.debug("selectSalesId()");
		return sqlSession.getMapper(LoginDao.class).selectSalesId(login);
	}
	
}
