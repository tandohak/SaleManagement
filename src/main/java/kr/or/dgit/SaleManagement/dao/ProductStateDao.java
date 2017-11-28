package kr.or.dgit.SaleManagement.dao;

import java.util.List;

import kr.or.dgit.SaleManagement.dto.ProductState;

public interface ProductStateDao {
	List<ProductState> selectAll();
	List<ProductState> selectBigClassAll();
	List<ProductState> selectSmallClassAll();
}
