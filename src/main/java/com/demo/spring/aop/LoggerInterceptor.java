package com.demo.spring.aop;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class LoggerInterceptor {

	final static Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);
			
	public Object log(ProceedingJoinPoint joinPoint) throws Throwable{
    	Object targer=joinPoint.getTarget();
    	String className = targer.getClass().getSimpleName();
    	String methodName=joinPoint.getSignature().getName();
    	String args = "";
    	long duration = 0;
    	String errorMessage = "";
    	
    	if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
    		List<String> argList = new ArrayList<String>();
    		for(Object arg : joinPoint.getArgs()) {
    			if (arg == null) {
    				argList.add("null");
    			} else {
    				argList.add(arg.toString());
    			}
    		}
    		args = StringUtils.collectionToCommaDelimitedString(argList);
    	}
    	
    	logger.debug("---------------------- LoggerInterceptor -----------------------");
    	
    	long startTime = System.currentTimeMillis();
    	long endTime = startTime;
    	try{
        	Object result = joinPoint.proceed();
        	endTime = System.currentTimeMillis();
        	return result;
    	}
    	catch(Throwable ex){
    		endTime = System.currentTimeMillis();
    		errorMessage = ex.getMessage();
    		throw ex;
    	} finally {
    		duration = endTime - startTime;
    		String methodInfo = MessageFormat.format("{0}.{1} ({2})>>>duration:{3}ms.{4}", 
    				className, methodName, args, duration, errorMessage);
    		if (StringUtils.isEmpty(errorMessage)) {
    			logger.info(methodInfo);
    		} else {
    			logger.error(methodInfo);
    		}
    		
    	}
    }
}
