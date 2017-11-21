package kr.or.dgit.SaleManagement.dao;

import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.SaleManagement.dto.Addr;

public class AddrDaoImpl implements AddrDao {
	private SqlSession sqlSession;
	private static final Log log = LogFactory.getLog(AddrDao.class);
	private static String namespace = "kr.or.dgit.SaleManagement.dao.AddrDao.";

	public AddrDaoImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<Addr> selectAddrBySido() {
		log.debug("selectAddrBySido()");
		return sqlSession.getMapper(AddrDao.class).selectAddrBySido();
	}

	@Override
	public List<Addr> selectAddrBySigungu(Addr addr) {
		log.debug("selectAddrBySigungu()");
		return sqlSession.getMapper(AddrDao.class).selectAddrBySigungu(addr);
	}

}
