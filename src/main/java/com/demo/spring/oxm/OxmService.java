package com.demo.spring.oxm;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.XmlMappingException;

import com.demo.model.jaxb.User;

public class OxmService {

	final static Logger logger = LoggerFactory.getLogger(OxmService.class);
	
	private static final String XML_PATH = "C:\\export\\User.xml";
	private static final String XSD_PATH = "./schema/User.xsd";
	
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;
	
	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}

	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}

	public void objectToXML(User user) throws XmlMappingException, IOException {
		if (user == null) {
			return;
		}
		
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(XML_PATH);
			this.marshaller.marshal(user, new StreamResult(os));
			
		} finally {
			os.flush();
			os.close();
		}
	}
	
	public User xmlToObject() throws XmlMappingException, IOException {
		User user = null;
		FileInputStream is = null;
		
		try {
			is = new FileInputStream(XML_PATH);
			user = (User)this.unmarshaller.unmarshal(new StreamSource(is));
		} finally {
			is.close();
		}
		return user;
	}
	
	public String objectToXMLWithoutSpring(User user) {
		String msgXML = null;
		try {
			JAXBContext context = JAXBContext.newInstance(User.class);
			javax.xml.bind.Marshaller marshaller = context.createMarshaller();
			
			// Validation with xsd
			/*SchemaFactory factory = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
			URL url = Thread.currentThread().getContextClassLoader().getResource(XSD_PATH);
			Schema schema = factory.newSchema(url);
			marshaller.setSchema(schema);*/
			
			OutputStream msgStream = new ByteArrayOutputStream();
			marshaller.marshal(user, msgStream);
			msgXML = msgStream.toString();
			
					
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return msgXML;
	}
	
	public User xmlToObjectWithoutSpring() {
		User user = null;
		try {
			JAXBContext context = JAXBContext.newInstance(User.class);
			javax.xml.bind.Unmarshaller unmarshaller = context.createUnmarshaller();
			
			// Validation with xsd
			SchemaFactory factory = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
			/*Schema schema = */factory.newSchema(Thread.currentThread().getContextClassLoader().getResource(XSD_PATH));
			//unmarshaller.setSchema(schema);
			
			String msgText = getXMLStr();
			user = (User) unmarshaller.unmarshal(new ByteArrayInputStream(msgText.getBytes()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public String getXMLStr() {
		StringBuffer reslut = new StringBuffer();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(XML_PATH));
			String line;
			while((line = reader.readLine()) != null) {
				reslut.append(line);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return reslut.toString();
	}
}
