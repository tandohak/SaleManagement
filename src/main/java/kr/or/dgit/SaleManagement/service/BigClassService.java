package kr.or.dgit.SaleManagement.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.SaleManagement.dao.BigClassDao;
import kr.or.dgit.SaleManagement.dao.BigClassDaoImpl;
import kr.or.dgit.SaleManagement.dao.ProductDao;
import kr.or.dgit.SaleManagement.dao.ProductDaoImpl;
import kr.or.dgit.SaleManagement.dto.BigClass;
import kr.or.dgit.SaleManagement.util.MyBatisSqlSessionFactory;

public class BigClassService {
	private final static BigClassService instance = new BigClassService();

	public static BigClassService getInstance() {
		return instance;
	}

	public List<BigClass> findAll() {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			BigClassDao dao = new BigClassDaoImpl(sqlSession);
			return dao.selectAll();
		}
	}

	public BigClass findBigClassByBigClass(BigClass bigClass) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			BigClassDao dao = new BigClassDaoImpl(sqlSession);
			return dao.selectbigClass(bigClass);
		}
	}

	public BigClass findBigClassByBigName(BigClass bigClass) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			BigClassDao dao = new BigClassDaoImpl(sqlSession);
			return dao.selectbigName(bigClass);
		}
	}

	public int findMaxCode() {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			BigClassDao dao = new BigClassDaoImpl(sqlSession);
			return dao.selectMaxCode();
		}
	}
	
	public int insertbigClass(BigClass bigClass) {
		int res = -1;
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			BigClassDao dao = new BigClassDaoImpl(sqlSession);
			res = dao.insertbigClass(bigClass);
			sqlSession.commit();
		}
		return res;
	}

	public int updatebigClass(BigClass bigClass) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
		try {
			BigClassDao dao = new BigClassDaoImpl(sqlSession);
			int res = dao.updatebigClass(bigClass);
			sqlSession.commit();
			return res;
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return 1;
	}

	public int deletebigClass(int bigClass) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
		try {
			BigClassDao dao = new BigClassDaoImpl(sqlSession);
			int res = dao.deletebigClass(bigClass);
			sqlSession.commit();
			return res;
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return 1;
	}

}
