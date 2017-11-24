package kr.or.dgit.SaleManagement.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.SaleManagement.dao.ProductDao;
import kr.or.dgit.SaleManagement.dao.ProductDaoImpl;
import kr.or.dgit.SaleManagement.dao.SalesDao;
import kr.or.dgit.SaleManagement.dao.SalesDaoImpl;
import kr.or.dgit.SaleManagement.dto.Product;
import kr.or.dgit.SaleManagement.util.MyBatisSqlSessionFactory;

public class ProductService {

	private final static ProductService instance = new ProductService();

	public static ProductService getInstance() {
		return instance;
	}

	public List<Product> findAll() {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			ProductDao dao = new ProductDaoImpl(sqlSession);
			return dao.selectAll();
		}
	}
	
	public List<Product> findAllByAdmin() {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			ProductDao dao = new ProductDaoImpl(sqlSession);
			return dao.selectByAdmit();
		}
	}
	
	public List<Product> findAllAdmit() {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			ProductDao dao = new ProductDaoImpl(sqlSession);
			return dao.selectByAdmit();
		}
	}

	public Product findBypdtProduct(Product product) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			ProductDao dao = new ProductDaoImpl(sqlSession);
			return dao.selectBypdtCode(product);
		}
	}

	public List<Product> findByAllItem(Product product) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			ProductDao dao = new ProductDaoImpl(sqlSession);
			return dao.SearchAllItem(product);
		}
	}
	
	public List<Product> findJoinAccount() {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			ProductDao dao = new ProductDaoImpl(sqlSession);
			return dao.selectProductJoinAccount();
		}
	}

	public int insertProduct(Product product) {
		int res = -1;
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			ProductDao dao = new ProductDaoImpl(sqlSession);
			res = dao.insertPdt(product);
			sqlSession.commit();
		}
		return res;
	}

	public int deleteProduct(Product product) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
		try {
			ProductDao dao = new ProductDaoImpl(sqlSession);
			int res = dao.deletePdt(product);
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

	public int updatePdt(Product product) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
		try {
			ProductDao dao = new ProductDaoImpl(sqlSession);
			int res = dao.updatePdt(product);
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
	
	public int updatePdtAdmit(Product product) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
		try {
			ProductDao dao = new ProductDaoImpl(sqlSession);
			int res = dao.deletePdtfalse(product);
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
	
	

	public int findMaxCode() {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			ProductDao dao = new ProductDaoImpl(sqlSession);
			return dao.selectMaxCode();
		}
	}
}
