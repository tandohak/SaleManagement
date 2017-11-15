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

}
