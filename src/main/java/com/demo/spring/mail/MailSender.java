package com.demo.spring.mail;

import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component("myMailSender")
@PropertySource("classpath:properties/mail.properties")
public class MailSender {
	
	@Resource
	private SimpleMailMessage message;
	
	@Resource
	private JavaMailSender sender;
	
	@Value("${mail.from}") private String from;
	@Value("${mail.to}") private String to;
	@Value("${mail.subject}") private String subject;
	@Value("${mail.content.html}") private String htmlContent;
	@Value("${mail.smtp.auth}") private boolean auth;
    @Value("${mail.smtp.starttls.enable}") private boolean starttls;
	
	public void sendTextMail() {
		Properties prop = ((JavaMailSenderImpl)sender).getJavaMailProperties();
		prop.put("mail.smtp.auth", auth);
		prop.put("mail.smtp.starttls.enable", starttls);
		sender.send(message);
	}
	
	public void sendHTMLMail() throws MessagingException {
		MimeMessage mimeMessage = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);
		mimeMessage.setContent(htmlContent, "text/html");
		sender.send(mimeMessage);
	}
	
}
