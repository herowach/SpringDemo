package com.demo.model;

import java.math.BigDecimal;
import java.util.Date;

public class Employee {
	private int id;
	private String code;
	private String name;
	private BigDecimal salary;
	private Department department;
	private Date createDate; 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Employee(){
		
	}
	
	public Employee(String name, String code) {
		this.name = name;
		this.code = code;
	}
}
