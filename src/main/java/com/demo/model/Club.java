package com.demo.model;

import java.math.BigDecimal;

public class Club {
	public int id;
	public String name;
	public String desc;
	public BigDecimal fee;
	
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	
	public boolean equals(Club o){
		if(o == null)
			return false;
		return id == o.id;
	}
}
