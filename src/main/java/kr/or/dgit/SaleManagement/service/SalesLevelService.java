package kr.or.dgit.SaleManagement.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.SaleManagement.dao.SalesLevelDao;
import kr.or.dgit.SaleManagement.dao.SalesLevelDaoImpl;
import kr.or.dgit.SaleManagement.dto.SalesLevel;
import kr.or.dgit.SaleManagement.util.MyBatisSqlSessionFactory;

public class SalesLevelService {	
	private final static SalesLevelService instance = new SalesLevelService();

	public static SalesLevelService getInstance() {
		return instance;
	}
	
	public SalesLevel findOneSalesLevel(SalesLevel saleslevel) {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()){
			SalesLevelDao sLevelDao = new SalesLevelDaoImpl(sqlSession);
			return sLevelDao.selectSalesByOne(saleslevel);
		}
	}
	
	public List<SalesLevel> findAllSalesLevel(){
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()){
			SalesLevelDao sLevelDao = new SalesLevelDaoImpl(sqlSession);
			return sLevelDao.selectAllSaleLevel();
		}
	}
	
	public int updateSalesLevel (SalesLevel saleslevel) {
		int res = -1;
		SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
		try {
			SalesLevelDao sLevelDao = new SalesLevelDaoImpl(sqlSession);
			
			res = sLevelDao.updateSalesLevel(saleslevel);
			sqlSession.commit();
			return res;			
		}catch (Exception e) {
			e.getStackTrace();
			sqlSession.rollback();
			throw new RuntimeException(e.getCause());
		}finally {
			sqlSession.close();
		}
	}
	
	public int deleteSalesLevel (SalesLevel saleslevel) {
		int res = -1;
		SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
		try {
			SalesLevelDao sLevelDao = new SalesLevelDaoImpl(sqlSession);
			
			res = sLevelDao.deletSalesLevel(saleslevel);
			sqlSession.commit();
			return res;			
		}catch (Exception e) {
			e.getStackTrace();
			sqlSession.rollback();
			throw new RuntimeException(e.getCause());
		}finally {
			sqlSession.close();
		}
	}

	public int insertSalesLevel(SalesLevel saleslevel) {
		int res = -1;
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()){
			SalesLevelDao sLevelDao = new SalesLevelDaoImpl(sqlSession);
			res = sLevelDao.insertSalesLevel(saleslevel);
			sqlSession.commit();
			return res;
		}
	}
}
