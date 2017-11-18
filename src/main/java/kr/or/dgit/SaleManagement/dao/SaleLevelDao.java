package kr.or.dgit.SaleManagement.dao;

import java.util.List;

import kr.or.dgit.SaleManagement.dto.Sales;
import kr.or.dgit.SaleManagement.dto.SalesLevel;

public interface SaleLevelDao {
	List<SalesLevel> selectAllSaleLevel();
	Sales selectSalesByOne(SalesLevel saleslevel);	
	List<SalesLevel> selectSalesLevelSearch(SalesLevel saleslevel);
	int insertSalesLevel(SalesLevel saleslevel);
	int updateSalesLevel(SalesLevel saleslevel);
	int deletSalesLevel(SalesLevel saleslevel);
}
