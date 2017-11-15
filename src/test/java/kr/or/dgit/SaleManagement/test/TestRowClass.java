package kr.or.dgit.SaleManagement.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.dgit.SaleManagement.dto.BigClass;
import kr.or.dgit.SaleManagement.dto.RowClass;
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
	
	/*@Test
	public void test3insert() {
		RowClass row = new RowClass();
		row.setRowClass(3);
		row.setRowName("기타1");
		RowClass Row = new RowClass(10);
		row.setRowClass(Row);
		
		int res = rowClassService.insertRowClass(row);
		Assert.assertEquals(1, res);
	}*/
	
	
	/*@Test
	public void test4ByClass() {
		RowClass rowClass = new RowClass();
		rowClass.setRowClass(1);
		rowClassService.findByRowClass(rowClass);
	}
	
	@Test
	public void test4ByName() {
		RowClass rowClass = new RowClass();
		rowClass.setRowName("이어폰");
		rowClassService.findByRowName(rowClass);
	}*/
	
	
	@Test
	public void test4update() {
		BigClass bigClass = new BigClass(99);
		RowClass RowClass = new RowClass(2, "바뀜22");
		int res = rowClassService.updateRowClass(RowClass);
		Assert.assertEquals(1, res);
	}
	
/*	@Test
	public void test5delete() {
//		RowClass RowClass = new RowClass(2);
		int res = rowClassService.deleteRowClass(1);
		Assert.assertEquals(1, res);
	}*/
	
}
