package com.demo.spring.webservice.soap.server;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.demo.spring.webservice.soap.server.domain.Account;
import com.demo.spring.webservice.soap.server.domain.CxfFileWrapper;

@WebService(name = "AccountWS")
public interface AccountService {
	@WebMethod
	public void insertAccount(Account account);

	@WebMethod
	public Account getAccount(@WebParam(name = "accountId") int id);
	
	@WebMethod
    public List<Account> getAccounts(@WebParam(name = "name") String name);
	
	@WebMethod
	boolean upload(@WebParam(name = "file") CxfFileWrapper file);
	 
	@WebMethod
	public CxfFileWrapper download(String fileName);
	
}
