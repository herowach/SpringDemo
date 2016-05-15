package com.demo.spring.webservice.soap.server;

import java.util.List;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class AuthValidateInterceptor extends AbstractPhaseInterceptor<SoapMessage> {
	
	private static final String NAMESPACE = "http://localhost:8080:Spring_Test/auth";
	private static final String TAG_NAME = "auth";
	
    public AuthValidateInterceptor(){
    	//specify the phase to trigger this interceptor
        super(Phase.PRE_INVOKE);
    }

    @Override
    public void handleMessage(SoapMessage message) throws Fault {
        List<Header> headers = message.getHeaders();
        if(headers == null || headers.size() < 1) {  
            throw new Fault(new Exception("Not authorization."));
        }

        Element auth = null;
        for(Header header : headers){
            QName qname = header.getName();
            String ns = qname.getNamespaceURI();
            String tagName = qname.getLocalPart();
            if(NAMESPACE.equals(ns) && TAG_NAME.equals(tagName)){
                auth = (Element)header.getObject();
                break;
            }
        }

        if(auth == null){
            throw new Fault(new Exception("Not authorization."));
        }

        NodeList nameList = auth.getElementsByTagName("name");
        NodeList pwdList = auth.getElementsByTagName("password");
        if(nameList.getLength() != 1 || pwdList.getLength() != 1){
            throw new Fault(new Exception("Authorization failed"));
        }

        String name = nameList.item(0).getTextContent();
        String password = pwdList.item(0).getTextContent();
        if(!"admin".equals(name) || !"admin".equals(password)){
            throw new Fault(new Exception("Authorization failed"));
        }
    }
}