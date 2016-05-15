package com.test.spring.orm;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.model.dto.ReportDTO;
import com.demo.spring.orm.mybatis.MyBatisDAO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/orm/spring-orm-config.xml"})
public class MyBatisTest {
	
	@Resource
	private MyBatisDAO mybatisDAO;
	
	@Test
	public void testInsert() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("rptName", "ReportName");
		params.put("createdBy", "cw67094");
		params.put("dateStr", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		mybatisDAO.insertTest(params);
	}
	
	@Test
	public void testUpdate() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("rptId", "1");
		params.put("rptName", "ChangedName");
		params.put("createdBy", "cw67094");
		params.put("dateStr", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		mybatisDAO.updateTest(params);
	}
	
	@Test
	public void testSelectOne() {
		String result = mybatisDAO.getReportName("1");
		assertNotNull("report name should not be null", result);
	}
	
	@Test
	public void testSelectList() {
		List<ReportDTO> testList = mybatisDAO.getDataWithSqlSessionTemplate();
		assertNotNull("testList should not be null", testList);
		assertTrue("size should greater than 0", testList.size() > 0);
	}
	
	@Test
	public void testPagination() {
		int offset = 0;
		int pageSize = 10;
		List<ReportDTO> testList = mybatisDAO.getListWithPagination(offset, pageSize);
		assertNotNull("testList should not be null", testList);
		assertEquals("size should equal to 10", 10, testList.size());
	}
	
}
