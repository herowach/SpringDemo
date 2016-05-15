package com.test.spring.oxm;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.oxm.XmlMappingException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.model.jaxb.Log;
import com.demo.model.jaxb.User;
import com.demo.spring.oxm.OxmService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/oxm/spring-oxm.xml"})
public class JAXBTest {
	
	@Resource
	private OxmService oxmService;
	
	private User getUser() {
		User user = new User();
		user.setUserId(1);
		user.setUserName("test");
		user.setUserType("admin");
		
		ArrayList<Log> logs = new ArrayList<Log>();
		Log log = new Log();
		log.setLevel(1);
		log.setContent("This is a log info.");
		Log log2 = new Log();
		log2.setLevel(2);
		log2.setContent("This is the second log.");
		logs.add(log);
		logs.add(log2);
		user.setLogs(logs);
		return user;
	}
	
	@Test
	public void objectToXMLTest() throws XmlMappingException, IOException {
		oxmService.objectToXML(getUser());
	}
	
	@Test
	public void xmlToObjectTest() throws XmlMappingException, IOException {
		User user = oxmService.xmlToObject();
		System.out.println(user.getUserName() + ", " + user.getUserId());
	}
	
	@Test
	public void objectToXMLWithoutSpringTest() throws XmlMappingException, IOException {
		String userStr = oxmService.objectToXMLWithoutSpring(getUser());
		System.out.println(userStr);
	}
	
	@Test
	public void xmlToObjectWithoutSpringTest() throws XmlMappingException, IOException {
		User user = oxmService.xmlToObjectWithoutSpring();
		System.out.println(user.getUserName() + ", " + user.getUserId());
	}
	
	@Test
	public void testGetXML() {
		String userStr = oxmService.getXMLStr();
		System.out.println(userStr);
	}
	
}
