package kr.or.dgit.SaleManagement.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.SaleManagement.dao.AccountDao;
import kr.or.dgit.SaleManagement.dao.AccountDaoImpl;
import kr.or.dgit.SaleManagement.dao.SalesDao;
import kr.or.dgit.SaleManagement.dao.SalesDaoImpl;
import kr.or.dgit.SaleManagement.dto.Account;
import kr.or.dgit.SaleManagement.dto.Sales;
import kr.or.dgit.SaleManagement.util.MyBatisSqlSessionFactory;

public class SalesService {
	private final static SalesService instance = new SalesService();

	public static SalesService getInstance() {
		return instance;
	}

	public Sales findSalesByCode(Sales sales) {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()){
			SalesDao salesDao = new SalesDaoImpl(sqlSession);
			return salesDao.selectSalesByCode(sales);
		}
	}
	
	public List<Sales> findSalesSearch(Sales sales) {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()){
			SalesDao salesDao = new SalesDaoImpl(sqlSession);
			return salesDao.selectSalesSearch(sales);
		}
	}
	
	public List<Sales> findSaleAll(){
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()){
			SalesDao salesDao = new SalesDaoImpl(sqlSession);
			return salesDao.selectSalseByAll();
		}
	}
		
	public int insertSales(Sales sales) {
		int res = -1;
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()){
			SalesDao salesDao = new SalesDaoImpl(sqlSession);
			res = salesDao.insertSales(sales);
			
			sqlSession.commit();
			return res;
		}
	}
	
	public int updateSales(Sales sales) {
		int res = -1;
		SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
		try{
			SalesDao salesDao = new SalesDaoImpl(sqlSession);
			res = salesDao.updateSales(sales);
			
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

	public int deleteSales(Sales sales) {
		int res = -1;
		SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
		try{
			SalesDao salesDao = new SalesDaoImpl(sqlSession);
			res = salesDao.deleteSales(sales);
			
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

	public int findMaxCode() {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			SalesDao salesDao = new SalesDaoImpl(sqlSession);
			return salesDao.selectMaxCode();
		}
	}
	
	public List<Sales> findSalesByLeave(Sales sales){
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()){
			SalesDao salesDao = new SalesDaoImpl(sqlSession);
			return salesDao.selectSalesByLeave(sales);
		}
	}
	
	public List<Sales> findSalesLikeName(Sales sales){
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			SalesDao dao = new SalesDaoImpl(sqlSession);
			return dao.selectSalesLikeName(sales);
		}
	}
}
