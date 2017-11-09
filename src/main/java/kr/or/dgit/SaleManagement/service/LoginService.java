package kr.or.dgit.SaleManagement.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.SaleManagement.dao.LoginDao;
import kr.or.dgit.SaleManagement.dao.LoginDaoImpl;
import kr.or.dgit.SaleManagement.dto.Login;
import kr.or.dgit.SaleManagement.util.MyBatisSqlSessionFactory;

public class LoginService {
	
	public Login findSalesById(Login login) {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();){
			LoginDao dao = new LoginDaoImpl(sqlSession);
			return dao.selectSalesId(login);
		}
	}
	
	public List<Login> findAll(){
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();){
			LoginDao dao = new LoginDaoImpl(sqlSession);
			return dao.selectAll();
		}
	}
}
