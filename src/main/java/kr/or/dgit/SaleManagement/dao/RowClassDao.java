package kr.or.dgit.SaleManagement.dao;

import java.util.List;

import kr.or.dgit.SaleManagement.dto.RowClass;

public interface RowClassDao {
	public List<RowClass> selectAll();
	public RowClass selectByRowClass(RowClass rowclass);
	public RowClass selectByRowName(RowClass rowclass);
	
	
	public int insertRowClass(RowClass rowclass);
	
	int updateRowClass(RowClass rowclass);
	int deleteRowClass(int rowClass);
}	
