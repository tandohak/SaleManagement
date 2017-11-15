package kr.or.dgit.SaleManagement.service;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.SaleManagement.dao.SalesDao;
import kr.or.dgit.SaleManagement.dao.SalesDaoImpl;
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
	
	public int insertSales(Sales sales) {
		int res = -1;
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()){
			SalesDao salesDao = new SalesDaoImpl(sqlSession);
			res = salesDao.insertSales(sales);
			
			sqlSession.commit();
			return res;
		}
	}
}
