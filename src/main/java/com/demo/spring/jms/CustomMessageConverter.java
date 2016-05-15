package com.demo.spring.jms;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import com.demo.model.jaxb.User;


public class CustomMessageConverter implements MessageConverter {

	private static final String ENCODING_FORMAT = "UTF-8";
	//private static final String MESSAGE_XSD_PATH = "Report.xsd";
	
	@Override
	public Object fromMessage(Message message) throws JMSException, MessageConversionException {
		User user = null;
		
		try{
			JAXBContext  context=JAXBContext.newInstance(User.class);
			Unmarshaller unmarshaller=context.createUnmarshaller();
			
			// Validation with xsd
			/*SchemaFactory factory = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(Thread.currentThread().getContextClassLoader().getResource(MESSAGE_XSD_PATH));
			unmarshaller.setSchema(schema);*/
			String msgText=((TextMessage)message).getText();
			user=(User)unmarshaller.unmarshal(new ByteArrayInputStream(msgText.getBytes(ENCODING_FORMAT)));
		}
		catch(Exception exc ){
			exc.printStackTrace();
		}
		return user;	
	}

	@Override
	public Message toMessage(Object reportObj, Session session) throws JMSException, MessageConversionException {
		TextMessage msgXMLTextMessage = null;
		try {
			JAXBContext context = JAXBContext.newInstance(User.class);
			Marshaller marshaller = context.createMarshaller();
			
			// Validation with xsd
			/*SchemaFactory factory = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(Thread.currentThread().getContextClassLoader().getResource(MESSAGE_XSD_PATH));
			marshaller.setSchema(schema);*/
			
			OutputStream msgStream = new ByteArrayOutputStream();
			marshaller.marshal(reportObj, msgStream);
			String msgXML = msgStream.toString();
			
			msgXMLTextMessage = session.createTextMessage(msgXML);
		}
		catch(JAXBException exc){
			exc.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return msgXMLTextMessage;
	}
}
