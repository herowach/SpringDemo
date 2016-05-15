package com.test.spring.jdbc;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.model.dto.ReportDTO;
import com.demo.spring.jdbc.JdbcDAO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/jdbc/spring-jdbc-config.xml"})
public class JDBCTemplateTest {
	
	@Resource
	private JdbcDAO jdbcDAO;
	
	@BeforeClass
	public static void beforeClass() {
		
	}
	
	@AfterClass
	public static void afterClass() {
		
	}
	
	@Before
	public void beforeTest() {
		
	}
	
	@After
	public void afterTest() {
		
	}
	
	//@Test
	public void testDB() {
		List<Map<String, Object>> testList = jdbcDAO.getList();
		System.out.println(testList.size());
	}
	
	@Test
	public void testInsert() {
		Map<String, String> params = new LinkedHashMap<String, String>();
		params.put("rptId", jdbcDAO.getNextId());
		params.put("rptName", "newReportName");
		params.put("createdBy", "cw67094");
		params.put("dateStr", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		
		Object[] paramArr = ((LinkedHashMap<String, String>)params).values().toArray();
		int count = jdbcDAO.insert(paramArr);
		assertEquals("insert result count is not 1", 1, count);
	}
	
	@Test
	public void testInsertWithPreparedStatementSetter() {
		Map<String, String> params = new LinkedHashMap<String, String>();
		params.put("rptName", "PreparedStatementSetter");
		params.put("createdBy", "cw67094");
		params.put("dateStr", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		
		int count = jdbcDAO.insertWithPreparedStatementSetter(params);
		assertEquals("insert result count is not 1", 1, count);
	}
	
	@Test
	public void testInsertWithNamedParameterTemplate() {
		Map<String, String> params = new LinkedHashMap<String, String>();
		params.put("rptId", jdbcDAO.getNextId());
		params.put("rptName", "newReportName");
		params.put("createdBy", "cw67094");
		params.put("dateStr", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		
		int count = jdbcDAO.insertWithNamedParameterTemplate(params);
		assertEquals("insert result count is not 1", 1, count);
	}
	
	@Test
	public void testUpdate() {
		if (jdbcDAO.getCount() <= 0) {
			fail("there is no data in test1");
			return;
		}
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("rptId", "1");
		params.put("rptName", "ChangedName");
		params.put("createdBy", "cw67094");
		params.put("dateStr", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		int count = jdbcDAO.update(params);
		assertEquals("update result count is not 1", 1, count);
	}
	
	
	@Test
	public void testSelectOne() {
		ReportDTO report = jdbcDAO.selectOne("1");
		assertNotNull("report should not be null", report);
		assertEquals("report id should equal to 1", "1", report.getRptId());
	}
	
	@Test
	public void testSelectList() {
		List<Map<String, Object>> testList = jdbcDAO.getList();
		assertNotNull("testList should not be null", testList);
		assertTrue("size should greater than 0", testList.size() > 0);
		
		List<ReportDTO> testList2 = jdbcDAO.getListWithRowCallbackHandler(1);
		assertNotNull("testList should not be null", testList2);
		assertTrue("size should greater than 0", testList2.size() > 0);
		
		List<ReportDTO> testList3 = jdbcDAO.getListWithRowMapper(10);
		assertNotNull("testList should not be null", testList3);
		assertTrue("size should greater than 0", testList3.size() > 0);
	}
	
}
