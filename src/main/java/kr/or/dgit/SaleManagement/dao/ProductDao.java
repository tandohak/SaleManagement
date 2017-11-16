package kr.or.dgit.SaleManagement.dao;

import java.util.List;

import kr.or.dgit.SaleManagement.dto.Product;

public interface ProductDao {
	public List<Product> selectAll();
	public List<Product> SearchAllItem(Product product);
	public Product selectBypdtCode(Product product);
	
	public int insertPdt(Product product);
	public int deletePdt(Product product);
	
	public int updatePdt(Product product);
}
