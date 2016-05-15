package com.demo.spring.ioc;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.model.Employee;


public class SpringBeanService {
	
	final static Logger logger = LoggerFactory.getLogger(SpringBeanService.class);
	
	public static int intProperty = 0;
	
	private SpringBean bean;
	private String injectByName;
	private String name;
	private int integer;
	
	private String[] array;
	private List<String> list;
	private Map<String, Integer> map;
	private Object obj;
	//private emnu
	
	
	
	
	public SpringBeanService(String name, SpringBean bean, String injectByName){
		this.name = name;
		this.bean = bean;
		this.injectByName = injectByName;
	}
	
	
	private static Employee employee;
	public static void setEmployee(Employee emp) {
		employee = emp;
	}
	
	@Override
	public String toString(){
		logger.debug("intProperty:" + intProperty);
		logger.debug("bean:" + bean.toString());
		logger.debug("injectByName:" + injectByName);
		logger.debug("name:" + name);
		logger.debug("integer:" + integer);
		logger.debug("array:" + array.toString());
		logger.debug("list:" + list.toString());
		logger.debug("map:" + map.toString());
		logger.debug("obj:" + obj.toString());
		logger.debug("employee:" + employee.toString());
		
		return "";
	}
	
}
