package kr.or.dgit.SaleManagement.dao;

import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.SaleManagement.dto.Record;

public class RecordDaoImpl implements RecordDao {
	private SqlSession sqlSession;

	private static final Log log = LogFactory.getLog(RecordDao.class);
	
	public RecordDaoImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public Record selectRecordByOne(Record record) {
		log.debug("selectRecordByOne()");
		return sqlSession.getMapper(RecordDao.class).selectRecordByOne(record);
	}

	@Override
	public List<Record> selectRecordBySearch(Record record) {
		log.debug("selectRecordBySearch()");
		return sqlSession.getMapper(RecordDao.class).selectRecordBySearch(record);
	}

	@Override
	public List<Record> selectRecordByAll() {
		log.debug("selectRecordByAll()");
		return sqlSession.getMapper(RecordDao.class).selectRecordByAll();
	}

	@Override
	public int insertRecord(Record record) {
		log.debug("insertRecord()");
		return sqlSession.getMapper(RecordDao.class).insertRecord(record);
	}

	@Override
	public int updateRecord(Record record) {
		log.debug("updateRecord()");
		return sqlSession.getMapper(RecordDao.class).updateRecord(record);
	}

	@Override
	public int deleteRecord(Record record) {
		log.debug("deleteRecord()");
		return sqlSession.getMapper(RecordDao.class).deleteRecord(record);
	}

	@Override
	public int selectMaxCode() {
		log.debug("selectMaxCode()");
		return sqlSession.getMapper(RecordDao.class).selectMaxCode();
	}
}
