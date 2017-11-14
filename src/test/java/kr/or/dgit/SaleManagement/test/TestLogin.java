package kr.or.dgit.SaleManagement.test;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.dgit.SaleManagement.dto.Login;
import kr.or.dgit.SaleManagement.service.LoginService;

public class TestLogin {
	
	private static LoginService loginService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		loginService = new LoginService();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		loginService = null;
	}

	/*@Test
	public void test1() {
		Login login = new Login();
		login.setCode(1010);
		
		Login findLogin = loginService.findSalesById(login);
		System.out.println(findLogin);
	}

	@Test
	public void test() {
		List<Login> lists= loginService.findAll();
		Assert.assertNotNull(lists);
		
	}*/
	
}
