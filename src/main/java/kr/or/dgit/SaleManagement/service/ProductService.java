package kr.or.dgit.SaleManagement.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.SaleManagement.dao.ProductDao;
import kr.or.dgit.SaleManagement.dao.ProductDaoImpl;
import kr.or.dgit.SaleManagement.dto.Product;
import kr.or.dgit.SaleManagement.util.MyBatisSqlSessionFactory;

public class ProductService {

	public List<Product> findAll() {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			ProductDao dao = new ProductDaoImpl(sqlSession);
			return dao.selectAll();
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
}
