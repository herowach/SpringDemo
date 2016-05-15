package com.demo.spring.jms;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.model.dto.ReportDTO;


public class MsgReceiver implements MessageListener {
	
	final static Logger logger = LoggerFactory.getLogger(MsgReceiver.class);
	
	@Override
	public void onMessage(Message message) {
		logger.info("-------------- MsgReceiver: start receiving --------------");
		try {
	        if (message instanceof TextMessage) {
	        	final TextMessage textMessage = (TextMessage) message;
	            logger.info("TextMessage: " + textMessage.getText());
	        }
	        else if (message instanceof MapMessage) {
	        	final MapMessage mapMessage = (MapMessage) message;
	        	logger.info("JMSCorrelationID: {}, accountId: {}, name: {}, age: {}", 
	        			message.getJMSCorrelationID(),
	        			mapMessage.getIntProperty("AccountID"),
	        			mapMessage.getString("Name"),
	        			mapMessage.getInt("Age"));
	        }
	        else if (message instanceof ObjectMessage) {
	        	final ObjectMessage objMessage = (ObjectMessage) message;
	        	ReportDTO report = (ReportDTO)objMessage.getObject();
	        	logger.info("ObjectMessage: " + report.toString());
	        }
	        else if (message instanceof BytesMessage) {
	        	final BytesMessage bytesMessage = (BytesMessage) message;
	        	int length = (int)bytesMessage.getBodyLength();
	        	byte [] buf = new byte[length];
	            bytesMessage.readBytes(buf);
	            
	            String content = new String(buf);
	            logger.info("BytesMessages: " + content);
	        }
	        else {
	            logger.warn("Dead Message: >>>>>>>>>>>>>"+ message.getJMSDestination().toString());
	        }
		} catch (JMSException jmse) {
			jmse.printStackTrace();
		}
        logger.info("-------------- MsgReceiver: end receiving ----------------");
    }
}
