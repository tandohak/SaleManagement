package kr.or.dgit.SaleManagement.dao;

import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.SaleManagement.dto.Sales;
import kr.or.dgit.SaleManagement.dto.SalesLevel;

public class SalesLevelDaoImpl implements SalesLevelDao{
	private SqlSession sqlSession;
	private static final Log log = LogFactory.getLog(SalesLevelDao.class);
	private static String namespace = "kr.or.dgit.SaleManagement.dao.SalesLevelDao.";	
	
	public SalesLevelDaoImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<SalesLevel> selectAllSaleLevel() {
		log.debug("selectAllSaleLevel()");
		return sqlSession.selectList(namespace+"selectAllSaleLevel");
	}

	@Override
	public SalesLevel selectSalesByOne(SalesLevel saleslevel) {
		log.debug("selectSalesByOne()");
		return sqlSession.selectOne(namespace+"selectSalesByOne",saleslevel);
	}

	/*@Override
	public List<SalesLevel> selectSalesLevelSearch(SalesLevel saleslevel) {
		log.debug("selectSalesLevelSearch()");
		return sqlSession.selectList(namespace+"selectSalesLevelSearch",saleslevel);
	}*/

	@Override
	public int insertSalesLevel(SalesLevel saleslevel) {
		log.debug("insertSalesLevel()");
		return sqlSession.insert(namespace+"insertSalesLevel", saleslevel);
	}

	@Override
	public int updateSalesLevel(SalesLevel saleslevel) {
		log.debug("updateSalesLevel()");
		return sqlSession.update(namespace+"updateSalesLevel", saleslevel);
	}

	@Override
	public int deletSalesLevel(SalesLevel saleslevel) {
		log.debug("deletSalesLevel()");
		return sqlSession.delete(namespace+"deletSalesLevel", saleslevel);
	}

}
