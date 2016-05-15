package com.demo.model;

import java.math.BigDecimal;

public class Employee {
	private String id;
	private String code;
	private String name;
	private BigDecimal salary;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;  
	}
	public BigDecimal getSalary() {
		return salary;
	}
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	
	public Employee(){
		
	}
	
	public Employee(int id, String name) {
		this.id = String.valueOf(id);
		this.name = name;
	}
	
	
}
