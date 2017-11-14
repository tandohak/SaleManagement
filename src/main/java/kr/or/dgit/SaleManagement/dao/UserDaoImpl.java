package kr.or.dgit.SaleManagement.dao;

import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.SaleManagement.dto.User;

public class UserDaoImpl implements UserDao {
	
	private SqlSession sqlSession;
	private static final Log log = LogFactory.getLog(UserDao.class);
	private static String namespace = "kr.or.dgit.SaleManagement.dao.UserDao.";


	public UserDaoImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	
	@Override
	public User selectUserById(User user) {
		log.debug("selectUserById()");
		return sqlSession.selectOne(namespace + "selectUserById", user);
	}

	@Override
	public List<User> selectUserByAll() {
		log.debug("selectUserByAll()");
		return sqlSession.selectList(namespace + "selectUserByAll");
	}

	@Override
	public int insertUser(User user) {
		log.debug("insertUser()");
		return sqlSession.insert(namespace + "insertUser", user);
	}

}
