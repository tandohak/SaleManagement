package kr.or.dgit.SaleManagement.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.dgit.SaleManagement.dto.BigClass;
import kr.or.dgit.SaleManagement.dto.RowClass;
import kr.or.dgit.SaleManagement.service.RowClassService;

public class TestRowClass {
	private static RowClassService rowClassService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		rowClassService = new RowClassService();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		rowClassService = null;
	}

	/*@Test
	public void test() {
		List<RowClass> lists = rowClassService.findAll();
		Assert.assertNotNull(lists);
	}*/
	
	@Test
	public void test3insert() {
		RowClass row = new RowClass();
		row.setRowClass(3);
		row.setRowName("기타1");
		BigClass big = new BigClass(10);
		row.setBigClass(big);
		
		int res = rowClassService.insertRowClass(row);
		Assert.assertEquals(1, res);
	}
}
