package kr.or.dgit.SaleManagement.test;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import kr.or.dgit.SaleManagement.dto.Account;
import kr.or.dgit.SaleManagement.service.AccountService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAccount {
	private static AccountService accountService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		accountService = new AccountService();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		accountService = null;
	}

	
/*	@Test
	public void test1InsertAccount() {
		Account acc = new Account();
		acc.setAccCode(21999999);
		acc.setAccName("CGV");
		acc.setAccTel("02-123-1234");
		acc.setAccAddr(new UserAddr(90000000));
		acc.setAccAdmit(true);
		UserCategory uc = new UserCategory();
		uc.setCategoryNo(20);
		User tempId = new User("cgvcgv123","cgvcgv123",uc);
		acc.setAccId(tempId);		
		
		int res = accountService.insertAccount(acc);
		System.out.println(res);
	}*/
	
	@Test
	public void test2ByCode() {
		Account account = new Account();
		account.setAccCode(20703003);
		
		Account findAccount = accountService.findAccountByCode(account);
		System.out.println(findAccount);
	}

	@Test
	public void test3All() {
		List<Account> findAccount = accountService.findAllAccount();
		Assert.assertNotNull(findAccount);
	}
}
