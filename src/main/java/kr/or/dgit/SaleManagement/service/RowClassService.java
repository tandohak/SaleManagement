package kr.or.dgit.SaleManagement.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.SaleManagement.dao.RowClassDao;
import kr.or.dgit.SaleManagement.dao.RowClassDaoImpl;
import kr.or.dgit.SaleManagement.dao.RowClassDao;
import kr.or.dgit.SaleManagement.dao.RowClassDaoImpl;
import kr.or.dgit.SaleManagement.dto.RowClass;
import kr.or.dgit.SaleManagement.dto.RowClass;
import kr.or.dgit.SaleManagement.util.MyBatisSqlSessionFactory;

public class RowClassService {
	
	public List<RowClass> findAll() {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();){
			RowClassDao dao = new RowClassDaoImpl(sqlSession);
			return dao.selectAll();
		}
	}
	
	public RowClass findByRowClass(RowClass rowclass) {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();){
			RowClassDao dao = new RowClassDaoImpl(sqlSession);
			return dao.selectByRowClass(rowclass);
		}
	}
	
	public RowClass findByRowName(RowClass rowclass) {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();){
			RowClassDao dao = new RowClassDaoImpl(sqlSession);
			return dao.selectByRowName(rowclass);
		}
	}
	
	
	public int insertRowClass(RowClass rowClass) {
		int res=-1;
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();){
			RowClassDao dao = new RowClassDaoImpl(sqlSession);
			res = dao.insertRowClass(rowClass);
			sqlSession.commit();
		}
		return res;		
	}
	
	public int updateRowClass(RowClass rowClass) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
		try {
			RowClassDao dao = new RowClassDaoImpl(sqlSession);
			int res = dao.updateRowClass(rowClass);
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
	
	public int	deleteRowClass(int rowClass) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
		try {
			RowClassDao dao = new RowClassDaoImpl(sqlSession);
			int res = dao.deleteRowClass(rowClass);
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
