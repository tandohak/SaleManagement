package kr.or.dgit.SaleManagement.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import kr.or.dgit.SaleManagement.dto.Sales;
import kr.or.dgit.SaleManagement.dto.SalesLevel;
import kr.or.dgit.SaleManagement.service.SalesService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSalesService {
	
	private static SalesService salesService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		salesService = SalesService.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		salesService = null;
	}

	@Test
	public void test1InsertSlaes() {
		Sales sales = new Sales();
		sales.setSaleCode(1234);
		sales.setSaleName("테스트2");
		sales.setSaleTel("010-1234-1234");
		sales.setSaleAddr("테스트용 주소2");
		sales.setSaleId("testId");
		sales.setSalePw("asdfqwer");
		sales.setSaleLevel("B");
		sales.setSaleLeave("false");
		int res = salesService.insertSales(sales);
		Assert.assertSame(1, res);
	}
	
	@Test
	public void test2FindByAll() {
		List<Sales> lists = new ArrayList<>();
		
		lists = salesService.findSaleAll();
		Assert.assertNotNull(lists);
	}
	
	@Test
	public void test3findSalesSearch() {
		List<Sales> lists = new ArrayList<>();
		Sales sales = new Sales();
		sales.setSaleLevel("B");
		
		lists = salesService.findSalesSearch(sales);
		Assert.assertNotNull(lists);
	}
	
	@Test
	public void test4UpdateSales(){
		Sales sales = new Sales();
		sales.setSaleCode(99999992);
		sales.setSaleAddr("주소수정");
		
		int res = salesService.updateSales(sales);
		Assert.assertEquals(1, res);
	}
	
	@Test
	public void test5FindByCode() {
		Sales sales = new Sales();
		sales.setSaleCode(99999992);
		
		Sales sales2 = salesService.findSalesByCode(sales);
		Assert.assertEquals(sales.getSaleCode(),sales2.getSaleCode());
	}
}
