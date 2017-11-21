package kr.or.dgit.SaleManagement.dao;

import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.SaleManagement.dto.Sales;

public class SalesDaoImpl implements SalesDao {
	private SqlSession sqlSession;
	private static final Log log = LogFactory.getLog(SalesDao.class);
	private static String namespace = "kr.or.dgit.SaleManagement.dao.SalesDao.";	
	
	public SalesDaoImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public Sales selectSalesByCode(Sales sales) {
		log.debug("selectSalesByCode()");
		return sqlSession.selectOne(namespace + "selectSalesByCode", sales);
	}

	@Override
	public List<Sales> selectSalseByAll() {
		log.debug("selectSalseByAll()");
		return sqlSession.selectList(namespace + "selectSalseByAll");
	}

	@Override
	public int insertSales(Sales sales) {
		log.debug("insertSales()");
		return sqlSession.insert(namespace + "insertSales", sales);
	}

	@Override
	public List<Sales> selectSalesSearch(Sales sales) {
		log.debug("selectSalesSearch()");
		return sqlSession.selectList(namespace + "selectSalesSearch",sales);
	}

	@Override
	public int updateSales(Sales sales) {
		log.debug("updateSales()");
		return sqlSession.update(namespace + "updateSales", sales);
	}

	@Override
	public int deleteSales(Sales sales) {
		log.debug("deleteSales()");
		return sqlSession.delete(namespace + "deleteSales", sales);
	}

	@Override
	public int selectMaxCode() {
		log.debug("selectMaxCode()");
		return sqlSession.selectOne(namespace + "selectMaxCode");
	}

	@Override
	public List<Sales> selectSalesByLeave(Sales sales) {
		log.debug("selectSalesByLeave()");
		return sqlSession.getMapper(SalesDao.class).selectSalesByLeave(sales);
	}

	@Override
	public List<Sales> selectSalesLikeName(Sales sales) {
		log.debug("selectSalesLikeName()");
		return sqlSession.getMapper(SalesDao.class).selectSalesLikeName(sales);
	}
	
	

}
