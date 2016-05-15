package com.demo.spring.jms;

import java.io.Serializable;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageDelegate {
	
	final static Logger logger = LoggerFactory.getLogger(MessageDelegate.class);

	public void handleMessage(String message) {
		logger.info("String: " + message);
	}

	//Note: have to specify HashMap as parameter, if Map, 
	//the message will go to handleMessage(Serializable message) because HashMap is Serializable too
	public void handleMessage(HashMap<String, ?> message) {
		logger.info("HashMap: name: {}, age: {}", message.get("Name"),message.get("Age"));
	}

	public void handleMessage(byte[] message) {
		logger.info("byte[]: " + message.toString());
	}

	public void handleMessage(Serializable message) {
		logger.info("Serializable: " + message.toString());
	}
}
