package com.demo.spring.webservice.soap.server.domain;

import java.io.Serializable;

public class Account implements Serializable{

	private static final long serialVersionUID = 1809206521095162813L;
	private long id;
	private String name;

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	/*@Override
	public String toString() {
		return this.name + "|" + this.id;
	}*/
	
	public Account(){
	}
	
	public Account(long id, String name){
		this.id = id;
		this.name = name;
	}

}
