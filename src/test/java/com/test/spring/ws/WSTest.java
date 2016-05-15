package com.test.spring.ws;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/ws/spring-ws.xml"})
public class WSTest {
	
	//@Resource
	//private WebServiceClient client;
	
	@Test
	public void testWs() {
		
	}
	
	
}
