package com.test.spring.mail;

import static org.junit.Assert.*;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.spring.mail.MailSender;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/mail/spring-mail-config.xml"})
@PropertySource("classpath:properties/mail.properties")
public class MailSenderTest {
	
	@Resource(name="myMailSender")
	private MailSender sender;
	
	private GreenMail smtpServer;
	
	@Value("${mail.from}") private String from;
	@Value("${mail.to}") private String to;
	@Value("${mail.subject}") private String subject;
	@Value("${mail.content.text}") private String textContent;
	@Value("${mail.content.html}") private String htmlContent;
	
	@Before
	public void startSmptServer() {
		smtpServer = new GreenMail(ServerSetupTest.SMTP);
        smtpServer.start();
	}
	
	@After
	public void tearDown() {
		smtpServer.stop();
	}
	
	@Test
	public void testSendTextMail() throws MessagingException {
		sender.sendTextMail();
		
		Message[] messages = smtpServer.getReceivedMessages();
        assertEquals(1, messages.length);
        Message message = messages[0];
        assertEquals(from, message.getFrom()[0].toString());
        assertEquals(to, message.getAllRecipients()[0].toString());
        assertEquals(subject, message.getSubject());
        assertEquals(textContent, GreenMailUtil.getBody(message).replaceAll("=\r?\n", ""));
	}
	
	@Test
	public void testSendHTMLMail() throws MessagingException {
		sender.sendHTMLMail();
		
		Message[] messages = smtpServer.getReceivedMessages();
		assertEquals(1, messages.length);
        Message message = messages[0];
        assertEquals(from, message.getFrom()[0].toString());
        assertEquals(to, message.getAllRecipients()[0].toString());
        assertEquals(subject, message.getSubject());
        assertEquals(htmlContent, GreenMailUtil.getBody(message).replaceAll("=\r?\n", ""));
	}
	
}
