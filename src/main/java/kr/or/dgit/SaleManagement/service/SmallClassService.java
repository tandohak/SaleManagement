package kr.or.dgit.SaleManagement.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.SaleManagement.dao.SmallClassDao;
import kr.or.dgit.SaleManagement.dao.SmallClassDaoImpl;
import kr.or.dgit.SaleManagement.dto.SmallClass;
import kr.or.dgit.SaleManagement.util.MyBatisSqlSessionFactory;

public class SmallClassService {
	
	public List<SmallClass> findAll() {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();){
			SmallClassDao dao = new SmallClassDaoImpl(sqlSession);
			return dao.selectAll();
		}
	}
	
	public SmallClass findBySmallClass(SmallClass Smallclass) {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();){
			SmallClassDao dao = new SmallClassDaoImpl(sqlSession);
			return dao.selectBySmallClass(Smallclass);
		}
	}
	
	public SmallClass findBySmallName(SmallClass Smallclass) {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();){
			SmallClassDao dao = new SmallClassDaoImpl(sqlSession);
			return dao.selectBySmallName(Smallclass);
		}
	}
	public List<SmallClass> findByBigClass(SmallClass Smallclass) {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();){
			SmallClassDao dao = new SmallClassDaoImpl(sqlSession);
			return dao.selectByBigClass(Smallclass);
		}
	}
	
	
	
	public int insertSmallClass(SmallClass SmallClass) {
		int res=-1;
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();){
			SmallClassDao dao = new SmallClassDaoImpl(sqlSession);
			res = dao.insertSmallClass(SmallClass);
			sqlSession.commit();
		}
		return res;		
	}
	
	public int updateSmallClass(SmallClass SmallClass) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
		try {
			SmallClassDao dao = new SmallClassDaoImpl(sqlSession);
			int res = dao.updateSmallClass(SmallClass);
			sqlSession.commit();
			return res;					
		}catch(Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return 1;
	}
	
	public int	deleteSmallClass(int SmallClass) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
		try {
			SmallClassDao dao = new SmallClassDaoImpl(sqlSession);
			int res = dao.deleteSmallClass(SmallClass);
			sqlSession.commit();
			return res;					
		}catch(Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return 1;
	}
	
}
