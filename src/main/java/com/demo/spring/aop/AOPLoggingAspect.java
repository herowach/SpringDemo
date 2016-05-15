package com.demo.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AOPLoggingAspect {

	final static Logger logger = LoggerFactory.getLogger(AOPLoggingAspect.class);
			
	@Before("execution(* com.spring.test..*.*(..)) || execution(* com.spring.service..*.*(..))")
	public void logBefore(final JoinPoint joinPoint) {
		if (logger.isDebugEnabled()) {
			StringBuffer logString = new StringBuffer();
			logString.append(">>>> Entering method [").append(joinPoint.getTarget().getClass().getName()).append(".").append(joinPoint.getSignature().getName() + "()]. ");
			if (joinPoint.getArgs().length > 0) {
				logString.append("Parameters Received : ");
				for (Object arg : joinPoint.getArgs()) {
					logString.append('{').append(arg).append('}');
				}
			}
			logger.debug(logString.toString());
		}
	}
}
