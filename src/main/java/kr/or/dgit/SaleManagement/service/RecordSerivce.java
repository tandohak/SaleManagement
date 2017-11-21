package kr.or.dgit.SaleManagement.service;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.SaleManagement.dao.RecordDao;
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
	
	public Record findRecordByOne(Record record) {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()){
			RecordDao recordDao = new RecordDaoImpl(sqlSession);
			return recordDao.selectRecordByOne(record);
		}		 
	}
}
