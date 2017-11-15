package kr.or.dgit.SaleManagement.dao;

import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.SaleManagement.dto.RowClass;

public class RowClassDaoImpl implements RowClassDao {
	private SqlSession sqlSession;

	private static final Log log = LogFactory.getLog(LoginDao.class);
	
	
	public RowClassDaoImpl(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}

	private String namespace = "kr.or.dgit.SaleManagement.dao.RowClassDao";

	@Override
	public List<RowClass> selectAll() {
		log.debug("selectAll()");
		return sqlSession.getMapper(RowClassDao.class).selectAll();
	}
	
	@Override
	public RowClass selectByRowClass(RowClass rowclass) {
		log.debug("selectByRowClass()");
		return sqlSession.getMapper(RowClassDao.class).selectByRowClass(rowclass);
	}
	
	@Override
	public RowClass selectByRowName(RowClass rowclass) {
		log.debug("selectByRowName()");
		return sqlSession.getMapper(RowClassDao.class).selectByRowName(rowclass);
	}



	@Override
	public int insertRowClass(RowClass rowclass) {
		log.debug("insertRowClass()");
		return sqlSession.getMapper(RowClassDao.class).insertRowClass(rowclass);
	}

	@Override
	public int updateRowClass(RowClass rowclass) {
		log.debug("updateRowClass()");
		return sqlSession.getMapper(RowClassDao.class).updateRowClass(rowclass);
	}

	@Override
	public int deleteRowClass(int rowclass) {
		log.debug("deleteRowClass()");
		return sqlSession.getMapper(RowClassDao.class).deleteRowClass(rowclass);
	}

	


	

}
