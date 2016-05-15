package com.demo.spring.webservice.soap.server;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;
import org.springframework.stereotype.Component;

@Component("serverPasswordCallback")
public class ServerPasswordCallbackHandler implements CallbackHandler{
	@Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];

        if (pc.getIdentifier().equals("admin")) {
            //set password
            pc.setPassword("123");
        }
    }
}
