package kr.or.dgit.SaleManagement.dao;

import java.util.List;

import org.apache.ibatis.annotations.Update;

import kr.or.dgit.SaleManagement.dto.BigClass;

public interface BigClassDao {
	public List<BigClass> selectAll();
	public BigClass selectbigClass(BigClass bigClass);
	
	public BigClass selectbigName(BigClass bigClass);
	public int insertbigClass(BigClass bigClass);
	
	int updatebigClass(BigClass bigClass);
	int deletebigClass(int bigClass);
}
