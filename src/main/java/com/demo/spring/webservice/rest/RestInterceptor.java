package com.demo.spring.webservice.rest;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

public class RestInterceptor extends AbstractPhaseInterceptor<Message>{
	public RestInterceptor(){
		super(Phase.PRE_INVOKE);
    }

    @SuppressWarnings("unchecked")
	@Override
    public void handleMessage(Message message) throws Fault {
        System.out.println(message);
        Map<String, Object> map =  (Map<String, Object>)message.get(Message.PROTOCOL_HEADERS);
        Set<String> set = map.keySet();
        Iterator<String> ite = set.iterator();
        while(ite.hasNext()){
        	String key = ite.next();
        	System.out.println(key + ": " + map.get(key));
        }
    }
}
