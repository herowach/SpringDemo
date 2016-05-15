package com.test.spring.ioc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/ioc/spring-ioc.xml"})
public class IocTest {
	
	
	//private SpringBean bean;
	
	@Test
	public void sendTextMessageTest() {
		
	}
	
	
}
