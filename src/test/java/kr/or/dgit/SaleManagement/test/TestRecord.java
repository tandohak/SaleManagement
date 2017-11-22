package kr.or.dgit.SaleManagement.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.dgit.SaleManagement.dto.Record;
import kr.or.dgit.SaleManagement.service.RecordSerivce;

public class TestRecord {
	private static RecordSerivce recordSerivce;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		recordSerivce = RecordSerivce.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		recordSerivce = null;
	}

	@Test
	public void test1SelectedAll() {
		List<Record> lists = recordSerivce.findRecordByAll();
		Assert.assertNotNull(lists);
	}
}
