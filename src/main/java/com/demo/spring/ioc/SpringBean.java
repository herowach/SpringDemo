package com.demo.spring.ioc;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SpringBean {
	
	final static Logger logger = LoggerFactory.getLogger(SpringBean.class);
	
	static{
		logger.debug("================>SpringBean.static block");
	}
	
	{
		logger.debug("================>SpringBean.instance block");
	}
	
	public SpringBean() {
		logger.debug("================>SpringBean.consturctor");
	}

	//init-method="init"
	public void init() {
		logger.debug("================>SpringBean.init method. Property is: " + property);
	}
	
	//destroy-method="destroy"
	public void destroy() {
		logger.debug("================>SpringBean.destory");
	}
	
	private String property = "undefined";
	
	public static SpringBean getInstance(){
		logger.debug("================>SpringBean.getInstance");
		SpringBean bean = new SpringBean();
		bean.property = "init value";
		return bean;
	}
	
	@PostConstruct
	private void postConstructor(){
		logger.debug("================>SpringBean.postConstructor");
	}
	
}
