package kr.or.dgit.SaleManagement.dao;

import java.util.List;

import org.apache.ibatis.annotations.Update;

import kr.or.dgit.SaleManagement.dto.BigClass;

public interface BigClassDao {
	List<BigClass> selectAll();
	BigClass selectbigClass(BigClass bigClass);
	
	BigClass selectbigName(BigClass bigClass);
	int insertbigClass(BigClass bigClass);
	
	int updatebigClass(BigClass bigClass);
	int deletebigClass(int bigClass);
}
