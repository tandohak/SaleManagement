package kr.or.dgit.SaleManagement.util;

import java.sql.Connection;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestMyBatisSqlSessionFactory {
	private static SqlSessionFactory factory;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		factory = MyBatisSqlSessionFactory.getSqlSessionFactory();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		factory = null;
	}
	
	@Test
	public void testASqlSession() {
		SqlSession sqlSession = factory.openSession();
		Assert.assertNotNull(sqlSession);
	}

	@Test
	public void testBConnection() {
		Connection con = factory.openSession().getConnection();
		Assert.assertNotNull(con);
	}
}
