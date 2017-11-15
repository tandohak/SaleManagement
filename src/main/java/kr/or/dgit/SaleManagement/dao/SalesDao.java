package kr.or.dgit.SaleManagement.dao;

import java.util.List;

import kr.or.dgit.SaleManagement.dto.Sales;

public interface SalesDao {
	public Sales selectSalesByCode(Sales sales);
	public List<Sales> selectSalseByAll();
	public int insertSales(Sales sales);
}
