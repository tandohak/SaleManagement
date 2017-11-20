package kr.or.dgit.SaleManagement.dao;

import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.SaleManagement.dto.Product;

public class ProductDaoImpl implements ProductDao {
	private SqlSession sqlSession;

	private static final Log log = LogFactory.getLog(SmallClassDao.class);

	public ProductDaoImpl(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}

	@Override
	public List<Product> selectAll() {
		log.debug("selectAll()");
		return sqlSession.getMapper(ProductDao.class).selectAll();
	}

	@Override
	public Product selectBypdtCode(Product product) {
		log.debug("selectBypdtCode()");
		return sqlSession.getMapper(ProductDao.class).selectBypdtCode(product);
	}

	@Override
	public List<Product> SearchAllItem(Product product) {
		log.debug("SearchAllItem()");
		return sqlSession.getMapper(ProductDao.class).SearchAllItem(product);
	}

	@Override
	public int insertPdt(Product product) {
		log.debug("insertPdt()");
		return sqlSession.getMapper(ProductDao.class).insertPdt(product);
	}

	@Override
	public int deletePdt(Product product) {
		log.debug("deletPdt()");
		return sqlSession.getMapper(ProductDao.class).deletePdt(product);
	}

	@Override
	public int updatePdt(Product product) {
		log.debug("updatePdt()");
		return sqlSession.getMapper(ProductDao.class).updatePdt(product);
	}

	@Override
	public int selectMaxCode() {
		log.debug("selectMaxCode()");
		return sqlSession.getMapper(ProductDao.class).selectMaxCode();
	}

}
