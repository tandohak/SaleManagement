package kr.or.dgit.SaleManagement.dao;

import java.util.List;

import kr.or.dgit.SaleManagement.dto.SalesLevel;

public interface SalesLevelDao {
	List<SalesLevel> selectAllSaleLevel();
	SalesLevel selectSalesByOne(SalesLevel saleslevel);	
//	List<SalesLevel> selectSalesLevelSearch(SalesLevel saleslevel);
	int insertSalesLevel(SalesLevel saleslevel);
	int updateSalesLevel(SalesLevel saleslevel);
	int deletSalesLevel(SalesLevel saleslevel);
}
