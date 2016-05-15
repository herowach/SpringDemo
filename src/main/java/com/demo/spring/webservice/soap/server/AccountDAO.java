package com.demo.spring.webservice.soap.server;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.demo.spring.webservice.soap.server.domain.Account;


@Repository("accountDao")
public class AccountDAO {
	
	final static Logger logger = LoggerFactory.getLogger(AccountDAO.class);
	static List<Account> list = new ArrayList<Account>();
	
	static {
		list.add(new Account(1, "Name1"));
		list.add(new Account(2, "Name2"));
	}
	
	
	public void addAccount(Account account) {
		//do persistence
		logger.info("AccountDAO>>>add account: " + account.toString());
	}
	
	public Account getAccount(int id) {
		return list.get(id);
	}
	
	public List<Account> getAccounts() {
		logger.info("AccountDAO>>>getAccounts");
		return list;
	}
	
}
