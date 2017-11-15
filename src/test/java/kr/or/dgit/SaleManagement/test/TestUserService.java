package kr.or.dgit.SaleManagement.test;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.dgit.SaleManagement.dto.User;
import kr.or.dgit.SaleManagement.dto.UserCategory;
import kr.or.dgit.SaleManagement.service.UserService;

public class TestUserService {
	private static UserService userService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		userService = UserService.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		userService = null;
	}

/*	@Test
	public void test1FindUserById() {
		User user = new User();
		user.setUserId("10111001");
		
		User user2 = userService.findUserById(user);
		Assert.assertEquals(user.getUserId(), user2.getUserId());
	}
	
	@Test
	public void test2InsertUser() {
		User user = new User();
		user.setUserId("testtest");
		user.setCategoryNo(new UserCategory(10));
		user.setUserPw("12341234");
		int res = userService.insertUser(user);
		Assert.assertSame(1, res);
	}*/

}
