package kr.or.dgit.SaleManagement.test;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.dgit.SaleManagement.dto.BigClass;
import kr.or.dgit.SaleManagement.service.BigClassService;

public class TestBigClass {
	
	private static BigClassService bigClassService; 

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bigClassService = new BigClassService();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		bigClassService =null;
	}

/*	@Test
	public void test() {
		List<BigClass> lists = bigClassService.findAll();
		Assert.assertNotNull(lists);
	}
	
	@Test
	public void test1() {
		BigClass big1 = new BigClass();
		big1.setBigClass(10);
		
		bigClassService.findBigClassByBigClass(big1);
	}
	
	@Test
	public void test2() {
		BigClass big = new BigClass();
		big.setBigName("기타");
		bigClassService.findBigClassByBigName(big);

	}
	
	@Test
	public void test3insert() {
		BigClass big = new BigClass();
		big.setBigClass(1);
		big.setBigName("기타1");
		
		int res = bigClassService.insertbigClass(big);
		Assert.assertEquals(1, res);
	}
	
	
	@Test
	public void test4update() {
		BigClass bigClass = new BigClass(1, "바뀜");
		int res = bigClassService.updatebigClass(bigClass);
		Assert.assertEquals(1, res);
	}
	
	@Test
	public void test5delete() {
		BigClass bigClass = new BigClass(1);
		int res = bigClassService.deletebigClass(1);
		Assert.assertEquals(1, res);
	}*/

}
