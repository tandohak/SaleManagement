package kr.or.dgit.SaleManagement.test;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.dgit.SaleManagement.dto.SalesLevel;
import kr.or.dgit.SaleManagement.service.SalesLevelService;

public class TestSalesLevel {
	
	private static SalesLevelService service;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service =SalesLevelService.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		service = null;
	}

	@Test
	public void test1SelectAll() {
		List<SalesLevel> lists = service.findAllSalesLevel();
		
		Assert.assertNotNull(lists);
	}
	
	@Test
	public void test2selectSalesByOne() {
		SalesLevel flevel= new SalesLevel();
		flevel.setSalLevel("A");
		SalesLevel slevel = service.findOneSalesLevel(flevel);
		
		Assert.assertEquals(slevel.getSalLevel(), flevel.getSalLevel());
	}
	
	@Test
	public void test3Insert() {
		SalesLevel saleslevel= new SalesLevel();
		saleslevel.setSalLevel("D");
		saleslevel.setSalDisrate(11);
		int res = service.insertSalesLevel(saleslevel);
		
		Assert.assertEquals(1, res);
	}
		
	@Test
	public void test4Update() {
		SalesLevel saleslevel= new SalesLevel();
		saleslevel.setSalLevel("D");
		saleslevel.setSalDisrate(12);
		int res = service.updateSalesLevel(saleslevel);
		
		Assert.assertEquals(1, res);
	}
	
	@Test
	public void test5Delete() {
		SalesLevel saleslevel= new SalesLevel();
		saleslevel.setSalLevel("D");
		saleslevel.setSalDisrate(11);
		int res = service.deleteSalesLevel(saleslevel);
		
		Assert.assertEquals(1, res);
	}

}
