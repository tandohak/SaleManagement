package kr.or.dgit.SaleManagement.test;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.dgit.SaleManagement.dto.AccountLevel;
import kr.or.dgit.SaleManagement.service.AccountLevelService;

public class TestAccountLevel {
	
	private static AccountLevelService service;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = AccountLevelService.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		service = null;
	}

	@Test
	public void test1SelectAll() {
		List<AccountLevel> lists = service.findAllAccountLevel();
		for(AccountLevel sl : lists) {
			System.out.println(sl);
		}
		Assert.assertNotNull(lists);
	}
	
	@Test
	public void test1SelectOne() {
		AccountLevel acc  =service.findOneAccount(new AccountLevel("L"));
//		AccountLevel acc = new AccountLevel("L");
		System.out.println(acc);
		Assert.assertEquals( "L",acc.getAccLevel());
	}
}
