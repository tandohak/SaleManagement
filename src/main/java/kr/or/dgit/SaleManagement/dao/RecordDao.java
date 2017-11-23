package kr.or.dgit.SaleManagement.dao;

import java.util.List;

import kr.or.dgit.SaleManagement.dto.Record;

public interface RecordDao {
	Record selectRecordByOne(Record record);
	List<Record> selectRecordBySearch(Record record);
	List<Record> selectRecordByAll();
	int selectMaxCode();
	int insertRecord(Record record);
	int updateRecord(Record record);
	int deleteRecord(Record record);
}
