package com.demo.model.mongo;

import java.util.Date;

public class DBSource {
	private String connUrl;
	private String userName;
	private String password;
	private String driverClass;
	private String jndiName;
	private String connName;
	private String createdBy;
	private String isActive;
	private Date createdTime;
	private String queryContent;
	private Long dsId;
	public String getIsActive() {
		return isActive;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date date) {
		this.createdTime = date;
	}
 
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getConnUrl() {
		return connUrl;
	}
	public void setConnUrl(String connUrl) {
		this.connUrl = connUrl;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDriverClass() {
		return driverClass;
	}
	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}
	public String getJndiName() {
		return jndiName;
	}
	public void setJndiName(String jndiName) {
		this.jndiName = jndiName;
	}
	public String getConnName() {
		return connName;
	}
	public void setConnName(String connName) {
		this.connName = connName;
	}
	public Long getDsId() {
		return dsId;
	}
	public void setDsId(Long dsId) {
		this.dsId = dsId;
	}
	public String getQueryContent() {
		return queryContent;
	}
	public void setQueryContent(String queryContent) {
		this.queryContent = queryContent;
	}
}
