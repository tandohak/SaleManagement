package kr.or.dgit.SaleManagement.dao;

import java.util.List;

import kr.or.dgit.SaleManagement.dto.Product;

public interface ProductDao {
	List<Product> selectAll();
	List<Product> SearchAllItem(Product product);
	Product selectBypdtCode(Product product);
	
	int insertPdt(Product product);
	int deletePdt(Product product);
	
	int updatePdt(Product product);
	int selectMaxCode() ;
}
