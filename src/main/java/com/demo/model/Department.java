package com.demo.model;

public class Department {
	public int id;
	public String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Department(){
		
	}
	
	public Department(String name){
		this.name = name;
	}
	
	public boolean equals(Department o){
		if(o == null)
			return false;
		return id == o.id;
	}
}
