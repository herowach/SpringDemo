package com.demo.spring.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PojoMsgReceiver {
	
	final static Logger logger = LoggerFactory.getLogger(PojoMsgReceiver.class);
	
	public void textMessageHandler(String message) {
		logger.info("-------------- PojoMsgReceiver: start receiving --------------");
	    logger.info(message);
        logger.info("-------------- PojoMsgReceiver: end receiving ----------------");
    }
}
