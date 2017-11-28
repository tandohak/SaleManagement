package kr.or.dgit.SaleManagement.test;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import kr.or.dgit.SaleManagement.dto.Account;
import kr.or.dgit.SaleManagement.dto.ProductState;
import kr.or.dgit.SaleManagement.service.AccountService;
import kr.or.dgit.SaleManagement.service.ProductStateService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestProductState {
	private static ProductStateService service;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = new ProductStateService();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		service = null;
	}

	@Test
	public void testAll() {
		List<ProductState> findAccount = service.findProductStateAll();
		for(ProductState psts : findAccount) {
			System.out.println(psts);
		}
		Assert.assertNotNull(findAccount);
	}

}
