package com.demo.spring.jms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.MessagePostProcessor;

import com.demo.model.dto.ReportDTO;
import com.demo.model.jaxb.Log;
import com.demo.model.jaxb.User;



public class MsgSender {
	private JmsTemplate jmsTemplate;
    private Queue queue;
    private Topic topic;
    
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void setQueue(Queue queue) {
		this.queue = queue;
	}
	
	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public void sendTextMessage() {
    	MessageCreator messageCreator = new MessageCreator() {
    		@Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("hello queue world");
            }
        };
    	
    	if (this.queue == null) {
    		//send the message to the default destination
    		this.jmsTemplate.send(messageCreator);
    	} else {
    		this.jmsTemplate.send(this.queue, messageCreator);
    	}
    }
    
    public void sendMapMessage(String queueName) {
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("Name", "Mark");
        map.put("Age", new Integer(47));
        
        MessagePostProcessor mpp = new MessagePostProcessor() {
        	@Override
            public Message postProcessMessage(Message message) throws JMSException {
        		//change data
        		((MapMessage)message).setString("Name", "Steve");
        		//add properties
                message.setIntProperty("AccountID", 1234);
                message.setJMSCorrelationID("123-00001");
                return message;
            }
        };
        
        
        if (queueName != null) {
        	//find queue by name
        	jmsTemplate.convertAndSend(queueName, map, mpp);
        } else if (this.queue != null) {
        	jmsTemplate.convertAndSend(this.queue, map, mpp);
        	/*jmsTemplate.send(this.queue, new MessageCreator() {
        		@Override
                public Message createMessage(Session session) throws JMSException {
        			MapMessage mm = session.createMapMessage();
        			for (Map.Entry<String, ?> entry : map.entrySet()) {
        				mm.setObject(entry.getKey(), entry.getValue());
        			}
        			mm.setIntProperty("AccountID", 1234);
                    mm.setJMSCorrelationID("123-00001");
                    return mm;
                }
            });*/
        } else {
        	//send to the default queue
        	jmsTemplate.convertAndSend(map, mpp);
        }
    }
    
	public void sendObjectMessage() {
        final ReportDTO report = new ReportDTO();
        report.setRptId("1");
        report.setRptName("JmsTest");
        report.setCreatedBy("cw67094");
        report.setDateStr("2016-01-01");
        
        MessageCreator mc = new MessageCreator() {
    		@Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(report);
            }
        };
        
        if (this.queue != null) {
        	jmsTemplate.send(this.queue, mc);
        } else {
        	jmsTemplate.send(mc);
        }
    }
	
	public void sendBytesMessage() {
		final byte[] bytes = "This is a bytes message".getBytes();
		MessageCreator messageCreator = new MessageCreator() {
    		@Override
            public Message createMessage(Session session) throws JMSException {
                BytesMessage bms = session.createBytesMessage();
                bms.writeBytes(bytes);
                bms.setJMSCorrelationID("123-00001");
                return bms;
            }
        };
        
        if (this.queue != null) {
        	jmsTemplate.send(this.queue, messageCreator);
        } else {
        	jmsTemplate.send(messageCreator);
        }
	}
	
	public void sendTextTopic() {
		MessageCreator messageCreator = new MessageCreator() {
    		@Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("hello, JMS topic!");
            }
        };
    	
    	this.jmsTemplate.send(this.topic, messageCreator);
	}
	
	public void sendWithCustomConverter() {
		User user = new User();
		user.setUserId(1);
		user.setUserName("testUser");
		user.setUserType("admin");
		
		ArrayList<Log> logs = new ArrayList<Log>();
		Log log = new Log();
		log.setLevel(1);
		log.setContent("This is the first log.");
		Log log2 = new Log();
		log2.setLevel(2);
		log2.setContent("This is the second log.");
		logs.add(log);
		logs.add(log2);
		user.setLogs(logs);
		
		jmsTemplate.convertAndSend("Queue1", user);
    }
}
