package kr.or.dgit.SaleManagement.dao;

import java.util.List;

import kr.or.dgit.SaleManagement.dto.RowClass;

public interface RowClassDao {
	public List<RowClass> selectAll();
	
	int insertRowClass(RowClass rowclass);
}	
