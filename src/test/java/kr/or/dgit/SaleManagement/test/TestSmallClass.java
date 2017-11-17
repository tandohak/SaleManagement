package kr.or.dgit.SaleManagement.test;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.dgit.SaleManagement.dto.BigClass;
import kr.or.dgit.SaleManagement.dto.Product;
import kr.or.dgit.SaleManagement.dto.SmallClass;
import kr.or.dgit.SaleManagement.service.SmallClassService;

public class TestSmallClass {
	private static SmallClassService SmallClassService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		SmallClassService = new SmallClassService();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		SmallClassService = null;
	}
	
/*	@Test
	public void test4BySmallNameLike() {
		SmallClass SmallClass = new SmallClass();
		SmallClass.setSmallName("%TV%");
		SmallClassService.findBySmallClassLisk(SmallClass);
	}
*/
	/*@Test
	public void test() {
		List<SmallClass> lists = SmallClassService.findAll();
		Assert.assertNotNull(lists);
		for(SmallClass pdt : lists) {
			System.out.println(pdt);
			
		}
		
	}
*/	
	
	
/*	@Test
	public void test3insert() {		
		SmallClass row = new SmallClass(3, "기타", 10);
		int res = SmallClassService.insertSmallClass(row);
		Assert.assertEquals(1, res);
	}*/
	
/*	@Test
	public void test4ByBigClass() {
		SmallClass SmallClass = new SmallClass();
		SmallClass.setsBigClass(20);
		SmallClassService.findByBigClass(SmallClass);
	}*/
	
/*	@Test
	public void test4update() {
		
		SmallClass SmallClass = new SmallClass(1, "바뀜22", 10);
		int res = SmallClassService.updateSmallClass(SmallClass);
		Assert.assertEquals(1, res);
	}
	
	@Test
	public void test5delete() {
//		SmallClass SmallClass = new SmallClass(2);
		int res = SmallClassService.deleteSmallClass(3);
		Assert.assertEquals(1, res);
	}*/
	
	/*@Test
	public void test4ByClass() {
		SmallClass SmallClass = new SmallClass();
		SmallClass.setSmallClass(1);
		SmallClassService.findBySmallClass(SmallClass);
	}
	
	@Test
	public void test4ByName() {
		SmallClass SmallClass = new SmallClass();
		SmallClass.setRowName("이어폰");
		SmallClassService.findByRowName(SmallClass);
	}*/
	
	


	
}
