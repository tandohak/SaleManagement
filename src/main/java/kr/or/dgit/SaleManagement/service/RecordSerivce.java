package kr.or.dgit.SaleManagement.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.SaleManagement.dao.RecordDao;
import kr.or.dgit.SaleManagement.dao.RecordDaoImpl;
import kr.or.dgit.SaleManagement.dao.SalesLevelDao;
import kr.or.dgit.SaleManagement.dao.SalesLevelDaoImpl;
import kr.or.dgit.SaleManagement.dto.Record;
import kr.or.dgit.SaleManagement.util.MyBatisSqlSessionFactory;

public class RecordSerivce {
	private final RecordSerivce instance = new RecordSerivce();

	public RecordSerivce getInstance() {
		return instance;
	}
	
	public Record findRecordByOne(Record record) {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()){
			RecordDao recordDao = new RecordDaoImpl(sqlSession);
			return recordDao.selectRecordByOne(record);
		}		 
	}
	
	public List<Record> findRecordBySearch(Record record) {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()){
			RecordDao recordDao = new RecordDaoImpl(sqlSession);
			return recordDao.selectRecordBySearch(record);
		}		 
	}
	
	public List<Record> findRecordByAll() {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()){
			RecordDao recordDao = new RecordDaoImpl(sqlSession);
			return recordDao.selectRecordByAll();
		}		 
	}
	
	public int insertRecord(Record record) {
		int res = -1;
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()){
			RecordDao recordDao = new RecordDaoImpl(sqlSession);
			res = recordDao.insertRecord(record);
			return res;
		}		 
	}
	
	public int updateRecord(Record record) {
		int res = -1;
		SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
		try {
			RecordDao recordDao = new RecordDaoImpl(sqlSession);
			
			res = recordDao.updateRecord(record);
			sqlSession.commit();
			return res;			
		}catch (Exception e) {
			e.getStackTrace();
			sqlSession.rollback();
			throw new RuntimeException(e.getCause());
		}finally {
			sqlSession.close();
		}
	}
	
	public int deleteRecord(Record record) {
		int res = -1;
		SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
		try {
			RecordDao recordDao = new RecordDaoImpl(sqlSession);
			
			res = recordDao.deleteRecord(record);
			sqlSession.commit();
			return res;			
		}catch (Exception e) {
			e.getStackTrace();
			sqlSession.rollback();
			throw new RuntimeException(e.getCause());
		}finally {
			sqlSession.close();
		}
	}
	
	
}
