package kr.or.dgit.SaleManagement.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.dgit.SaleManagement.dto.Product;
import kr.or.dgit.SaleManagement.service.ProductService;

public class TestProduct {
	
	private static ProductService pdt;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		pdt = new ProductService();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		pdt = null;
	}

	
	/*@Test
	public void test() {
		List<Product> lists = pdt.findAll();
		Assert.assertNotNull(lists);	
	}
	@Test
	public void test1234() {
		List<Product> lists = pdt.findAll();
		Assert.assertNotNull(lists);	
	}
	
	@Test
	public void test2ByClass() {
		Product product = new Product();
		product.setPdtCode(1000);
		pdt.findBypdtProduct(product);
	}*/
	
	/*@Test
	public void test2ByClass() {
		Product product = new Product();
		
		product.setPdtName("%노트북%");
		pdt.findByAllItem(product);
	}*/
	
	/*@Test
	public void test2ByClass() {
		Product product = new Product();
		
		product.setPdtName("삼성노트북");
		pdt.findByAllItem(product);
	}
	
	@Test
	public void test3ByClass() {
		Product product = new Product();
		
		product.setPdtCost(730000);
		pdt.findByAllItem(product);
	}
	
	@Test
	public void test4ByClass() {
		Product product = new Product();
		
		product.setPdtAdmit("true");
		pdt.findByAllItem(product);
	}*/
	
	
	
//	@Test
//	public void test2insert() {		
//		Product product = new Product(13, 21, "테스트", 700, 70, "true", 21722051);
//		int res = pdt.insertProduct(product);
//		Assert.assertEquals(1, res);
//	}
//	
//	@Test
//	public void test3insert() {		
//		Product product = new Product(13, 21, "테스트111", 700, 70, "true", 21722051);
//		int res = pdt.updatePdt(product);
//		Assert.assertEquals(1, res);
//	}
	
//	@Test
//	public void test5delete() {
//		Product product = new Product();
//		product.setPdtName("테스트111");
//		int res = pdt.deleteProduct(product);
//		Assert.assertEquals(1, res);
//	}
}
