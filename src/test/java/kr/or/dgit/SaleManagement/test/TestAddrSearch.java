package kr.or.dgit.SaleManagement.test;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.dgit.SaleManagement.dto.Addr;
import kr.or.dgit.SaleManagement.service.AddrService;

public class TestAddrSearch {
	private static AddrService addrService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		addrService = AddrService.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		addrService = null;
	}

	@Test
	public void test() {
		Addr addr = new Addr();
		addr.setDoro("대명%");
		List<Addr> list = addrService.findAddrSearch(addr);
		
		Assert.assertNotNull(list);
	}
\
}
