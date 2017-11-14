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


	@Override
	public List<RowClass> selectAll() {
		log.debug("selectAll()");
		return sqlSession.getMapper(RowClassDao.class).selectAll();
	}


	@Override
	public int insertRowClass(RowClass rowclass) {
		log.debug("insertRowClass()");
		return sqlSession.getMapper(RowClassDao.class).insertRowClass(rowclass);
	}

}
