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

	/*@Test
	public void test1InsertAccount() {
		Account acc = new Account();
		acc.setAccCode(21999999);
		acc.setAccName("CGV");
		acc.setAccTel("02-123-1234");
		acc.setAccAddr("대구광역시 달서구 감삼동 123-123");
		acc.setAccAdmit("true");
		acc.setAccLevel("S");
		acc.setAccId("cgvcgv123");
		acc.setAccPw("12345");

		int res = accountService.insertAccount(acc);
		System.out.println("결과 : " + res);
	}

	@Test
	public void test2ByCode() {
		Account account = new Account();
		account.setAccCode(20703003);

		Account findAccount = accountService.findAccountByCode(account);
		System.out.println(findAccount);
	}

	@Test
	public void test3ByName() {
		Account account = new Account();
		account.setAccName("CGV");

		Account findAccount = accountService.findAccountByName(account);
		System.out.println(findAccount);
	}

	@Test
	public void test4ByLevel() {
		Account account = new Account();
		account.setAccLevel("L");

		List<Account> findAccount = accountService.findAccountByLevel(account);
		Assert.assertNotNull(findAccount);
	}

	@Test
	public void test5All() {
		List<Account> findAccount = accountService.findAllAccount();
		Assert.assertNotNull(findAccount);
	}

	@Test
	public void test6UpdateAccount() {
		Account acc = new Account();
		acc.setAccCode(21999999);
		acc.setAccName("CGV");
		acc.setAccTel("02-123-1234");
		acc.setAccAddr("대구광역시 달서구 감삼동 987-23");
		acc.setAccAdmit("false");
		acc.setAccLevel("L");
		acc.setAccPw("12345");

		int res = accountService.updateAccount(acc);
		System.out.println("결과 : " + res);
	}*/
}
