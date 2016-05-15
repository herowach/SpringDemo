package com.demo.spring.schedule.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PojoJob {

	final static Logger logger = LoggerFactory.getLogger(PojoJob.class);
	private static int count = 0;
	
	public void runJob(String testArg) {
		logger.info("---------------------->PojoJob triggered. argument is {}. count: {}", testArg, ++count);
	}

}
