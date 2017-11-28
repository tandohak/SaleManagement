package kr.or.dgit.SaleManagement.dao;

import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.SaleManagement.dto.ProductState;

public class ProductStateDaoImpl implements ProductStateDao {
	private SqlSession sqlSession;
	private static final Log log = LogFactory.getLog(SalesLevelDao.class);
	private static String namespace = "kr.or.dgit.SaleManagement.dao.ProductStateDao.";
	
	

	public ProductStateDaoImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	@Override
	public List<ProductState> selectAll() {
		log.debug("selectAll()");
		return sqlSession.selectList(namespace+"selectAll");
	}
	@Override
	public List<ProductState> selectBigClassAll() {
		log.debug("selectBigClassAll()");
		return sqlSession.selectList(namespace+"selectBigClassAll");
	}
	@Override
	public List<ProductState> selectSmallClassAll() {
		log.debug("selectSmallClassAll()");
		return sqlSession.selectList(namespace+"selectSmallClassAll");
	}

	
}
