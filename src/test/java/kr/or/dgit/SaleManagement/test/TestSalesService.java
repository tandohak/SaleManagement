package kr.or.dgit.SaleManagement.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import kr.or.dgit.SaleManagement.service.SalesService;

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

/*	@Test
	public void test1InsertSlaes() {
		Sales sales = new Sales();
		sales.setSaleCode(99999992);
		sales.setSaleName("테스트2");
		sales.setSaleTel("010-1234-1234");
		sales.setSaleAddr(new UserAddr(90000001));
		sales.setSaleLeave(true);
		sales.setSaleId(new User("10111001"));
		sales.setSaleLevel(new SalesLevel("A"));
		
		int res = salesService.insertSales(sales);
		Assert.assertSame(1, res);
	}*/

}
