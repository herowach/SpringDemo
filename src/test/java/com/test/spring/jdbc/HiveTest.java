package com.test.spring.jdbc;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

//import org.apache.hive.jdbc.HivePreparedStatement;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/jdbc/spring-hive-config.xml"})
public class HiveTest {
	/*@Resource
	private HiveDAO hiveDAO;
	
	@Test
	public void testSelectList() {
		List<Map<String,Object>> testList = hiveDAO.getList();
		assertNotNull("testList should not be null", testList);
		assertTrue("size should greater than 0", testList.size() > 0);
	}
	
	@Test
	public void testList() throws Exception {
		Class.forName("org.apache.hive.jdbc.HiveDriver");
		Connection con = DriverManager.getConnection("jdbc:hive2://sd-4261-f1e1.nam.nsroot.net:10000", "bigdatagfts", "!QAZ2wsx");
		Statement stmt = con.createStatement();
		
		ResultSet res = stmt.executeQuery("select * from test1.dsmt_goc"); 
		while (res.next()) {
            System.out.println("Result: key:"+res.getString(1));// +"  –>  value:" +res.getString(2));
        }
	}*/
	
}
