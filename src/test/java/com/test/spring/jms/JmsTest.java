package com.test.spring.jms;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.spring.jms.MsgSender;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/jms/spring-jms-config.xml"})
public class JmsTest {
	
	@Resource(name="msgSender")
	private MsgSender sender;
	
	@Test
	public void sendTextMessageTest() {
		sender.sendTextMessage();
	}
	
	@Test
	public void sendMapMessageTest() {
		sender.sendMapMessage(null);
	}
	
	@Test
	public void sendObjectMessageTest() {
		sender.sendObjectMessage();
	}
	
	
	public static void sendMsgWithoutSpring() throws NamingException, JMSException {
		InitialContext ic = new InitialContext();
		ConnectionFactory cf = (ConnectionFactory)ic.lookup("/ConnectionFactory");
		Queue queue = (Queue)ic.lookup("/queue/Queue1");
		Connection connection = cf.createConnection();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		MessageProducer producer = session.createProducer(queue);
		MessageConsumer consumer = session.createConsumer(queue);
		
		connection.start();
		TextMessage message = session.createTextMessage("This is a message");
        producer.send(message);
        
        TextMessage receivedMessage = (TextMessage)consumer.receive();
        System.out.println("Received message: " + receivedMessage.getText());
	}
}
