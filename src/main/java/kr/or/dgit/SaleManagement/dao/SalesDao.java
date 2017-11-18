package kr.or.dgit.SaleManagement.dao;

import java.util.List;

import kr.or.dgit.SaleManagement.dto.Sales;

public interface SalesDao {
	Sales selectSalesByCode(Sales sales);
	List<Sales> selectSalseByAll();
	List<Sales> selectSalesSearch(Sales sales);
	int insertSales(Sales sales);
	int updateSales(Sales sales);
}
