package kr.or.dgit.SaleManagement.service;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.SaleManagement.dao.UserDao;
import kr.or.dgit.SaleManagement.dao.UserDaoImpl;
import kr.or.dgit.SaleManagement.dto.User;
import kr.or.dgit.SaleManagement.util.MyBatisSqlSessionFactory;

public class UserService {
	private final static UserService instance = new UserService();

	public static UserService getInstance() {
		return instance;
	}

	public User findUserById(User user) {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()){
			UserDao userDao = new UserDaoImpl(sqlSession);
			return userDao.selectUserById(user);
		}
	}
	
	public int insertUser(User user) {
		int res = -1;
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()){
			UserDao userDao = new UserDaoImpl(sqlSession);
			res = userDao.insertUser(user);
			
			sqlSession.commit();
			return res;
		}
	}
}
