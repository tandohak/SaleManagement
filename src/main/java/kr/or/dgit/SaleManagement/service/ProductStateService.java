package kr.or.dgit.SaleManagement.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.SaleManagement.dao.ProductStateDao;
import kr.or.dgit.SaleManagement.dao.ProductStateDaoImpl;
import kr.or.dgit.SaleManagement.dto.ProductState;
import kr.or.dgit.SaleManagement.util.MyBatisSqlSessionFactory;

public class ProductStateService {
	private final static ProductStateService instance = new ProductStateService();

	public static ProductStateService getInstance() {
		return instance;
	}
	
	public List<ProductState> findProductStateAll() {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()){
			ProductStateDao pdtStateDao = new ProductStateDaoImpl(sqlSession);
			return pdtStateDao.selectAll();
		}	
	}
	public List<ProductState> findBigClassStateAll() {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()){
			ProductStateDao pdtStateDao = new ProductStateDaoImpl(sqlSession);
			return pdtStateDao.selectBigClassAll();
		}	
	}
	public List<ProductState> findSmallClassStateAll() {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()){
			ProductStateDao pdtStateDao = new ProductStateDaoImpl(sqlSession);
			return pdtStateDao.selectSmallClassAll();
		}	
	}
}
