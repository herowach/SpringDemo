package com.demo.spring.jms;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.listener.SessionAwareMessageListener;

public class SpringMsgReceiver implements SessionAwareMessageListener<TextMessage> {
	
	final static Logger logger = LoggerFactory.getLogger(SpringMsgReceiver.class);
	
	@Override
	public void onMessage(TextMessage message, Session session) {
		logger.info("-------------- SpringMsgReceiver: start receiving --------------");
		try {
        	final TextMessage textMessage = (TextMessage) message;
            logger.info(textMessage.getText());
		} catch (JMSException jmse) {
			jmse.printStackTrace();
		}
        logger.info("-------------- SpringMsgReceiver: end receiving ----------------");
    }
}
