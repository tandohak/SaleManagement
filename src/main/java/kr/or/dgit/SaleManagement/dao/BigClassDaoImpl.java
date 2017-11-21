package kr.or.dgit.SaleManagement.dao;

import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.SaleManagement.dto.BigClass;

public class BigClassDaoImpl implements BigClassDao {

	private SqlSession sqlSession;

	private static final Log log = LogFactory.getLog(BigClassDao.class);

	public BigClassDaoImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<BigClass> selectAll() {
		log.debug("selectAll()");
		return sqlSession.getMapper(BigClassDao.class).selectAll();
	}

	@Override
	public BigClass selectbigClass(BigClass bigClass) {
		log.debug("selectbigClass()");
		return sqlSession.getMapper(BigClassDao.class).selectbigClass(bigClass);
	}

	@Override
	public BigClass selectbigName(BigClass bigClass) {
		log.debug("selectbigName()");
		return sqlSession.getMapper(BigClassDao.class).selectbigName(bigClass);
	}

	@Override
	public int insertbigClass(BigClass bigClass) {
		log.debug("insertbigClass()");
		return sqlSession.getMapper(BigClassDao.class).insertbigClass(bigClass);
	}

	@Override
	public int updatebigClass(BigClass bigClass) {
		log.debug("updatebigClass()");
		return sqlSession.getMapper(BigClassDao.class).updatebigClass(bigClass);
	}

	@Override
	public int deletebigClass(int bigClass) {
		log.debug("deletebigClass()");
		return sqlSession.getMapper(BigClassDao.class).deletebigClass(bigClass);
	}

	@Override
	public int selectMaxCode() {
		log.debug("selectMaxCode()");
		return sqlSession.getMapper(BigClassDao.class).selectMaxCode();
	}

}
