package com.test.spring.jdbc;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.model.mongo.DBSource;
import com.demo.spring.jdbc.MongoDAO;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/mongo/spring-mongo-config.xml"})
public class MongoTemplateTest {
	
	@Resource
	private MongoDAO mongoDAO;
	
	@Test
	public void testInsert() {
		List<DBSource> results= mongoDAO.getAllDataSources();
		System.out.println(results.size());
	}
	
	
	
}
