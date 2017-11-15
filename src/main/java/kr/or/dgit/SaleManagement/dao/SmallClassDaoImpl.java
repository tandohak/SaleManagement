package kr.or.dgit.SaleManagement.dao;

import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.SaleManagement.dto.SmallClass;

public class SmallClassDaoImpl implements SmallClassDao {
	private SqlSession sqlSession;

	private static final Log log = LogFactory.getLog(SmallClassDao.class);
	
	
	public SmallClassDaoImpl(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}

	private String namespace = "kr.or.dgit.SaleManagement.dao.SmallClassDao";

	@Override
	public List<SmallClass> selectAll() {
		log.debug("selectAll()");
		return sqlSession.getMapper(SmallClassDao.class).selectAll();
	}
	
	@Override
	public SmallClass selectBySmallClass(SmallClass Smallclass) {
		log.debug("selectBySmallClass()");
		return sqlSession.getMapper(SmallClassDao.class).selectBySmallClass(Smallclass);
	}
	
	@Override
	public SmallClass selectBySmallName(SmallClass Smallclass) {
		log.debug("selectBySmallName()");
		return sqlSession.getMapper(SmallClassDao.class).selectBySmallName(Smallclass);
	}



	@Override
	public int insertSmallClass(SmallClass Smallclass) {
		log.debug("insertSmallClass()");
		return sqlSession.getMapper(SmallClassDao.class).insertSmallClass(Smallclass);
	}

	@Override
	public int updateSmallClass(SmallClass Smallclass) {
		log.debug("updateSmallClass()");
		return sqlSession.getMapper(SmallClassDao.class).updateSmallClass(Smallclass);
	}

	@Override
	public int deleteSmallClass(int Smallclass) {
		log.debug("deleteSmallClass()");
		return sqlSession.getMapper(SmallClassDao.class).deleteSmallClass(Smallclass);
	}

	


	

}
