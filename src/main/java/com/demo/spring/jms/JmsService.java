package com.demo.spring.jms;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * http://localhost:8080/Spring_Test/jms/text/1
 * http://localhost:8080/Spring_Test/jms/text/2
 * http://localhost:8080/Spring_Test/jms/map/2
 * http://localhost:8080/Spring_Test/jms/object/1
 * http://localhost:8080/Spring_Test/jms/bytes/1
 * http://localhost:8080/Spring_Test/jms/topic
 */

@Controller
@RequestMapping(value="/jms",method={RequestMethod.OPTIONS,RequestMethod.GET,RequestMethod.POST})
public class JmsService {
	
	@Resource(name="msgSender")
	private MsgSender sender;
	
	@Resource(name="msgSender2")
	private MsgSender sender2;
	
	@Resource(name="msgSender3")
	private MsgSender sender3;
	
	@Resource(name="msgSender4")
	private MsgSender sender4;
	
	@Resource(name="msgSender5")
	private MsgSender sender5;
	
	private MsgSender getSender(int queueNO) {
		switch (queueNO) {
			case 2:
				return sender2;
			case 3:
				return sender3;
			case 4:
				return sender4;
			default:
				return sender;
		}
	}
	
	@RequestMapping(value = "text/{queueNO}", method = {RequestMethod.GET})
	@ResponseBody
	public void sendTextMessage(@PathVariable int queueNO) throws Exception{
		getSender(queueNO).sendTextMessage();
	}
	
	@RequestMapping(value = "map/{queueNO}", method = {RequestMethod.GET})
	@ResponseBody
	public void sendMapMessage(@PathVariable int queueNO) throws Exception{
		if (queueNO == 2 || queueNO == 4) {
			getSender(queueNO).sendMapMessage("Queue1");
		} else {
			getSender(queueNO).sendMapMessage(null);
		}
	}
	
	@RequestMapping(value = "object/{queueNO}", method = {RequestMethod.GET})
	@ResponseBody
	public void sendObjectMessage(@PathVariable int queueNO) throws Exception{
		getSender(queueNO).sendObjectMessage();
	}
	
	@RequestMapping(value = "bytes/{queueNO}", method = {RequestMethod.GET})
	@ResponseBody
	public void sendBytesMessage(@PathVariable int queueNO) throws Exception{
		getSender(queueNO).sendBytesMessage();
	}
	
	@RequestMapping(value = "customConverter", method = {RequestMethod.GET})
	public void sendWithCustomConverter(@RequestParam int queueNO) throws Exception{
		sender.sendWithCustomConverter();
	}
	
	@RequestMapping(value = "test", method = {RequestMethod.GET})
	public void test(HttpServletRequest request) throws Exception{
		
	}
	
	@RequestMapping(value = "topic", method = {RequestMethod.GET})
	@ResponseBody
	public void sendTopic() throws Exception{
		sender5.sendTextTopic();
	}
	
	@RequestMapping(value = "convert", method = {RequestMethod.GET})
	@ResponseBody
	public void sendMessageWithConvertor() throws Exception{
		sender2.sendWithCustomConverter();
	}
}
