package com.demo.model.mongo;

import java.util.Date;

public class UserQuery {
	private String soeid;
	private String queryName;
	private String queryDesc;
	private int queryId;
	private String queryContent;
	private String isActive;
	private Date createdTime;
	private Date updatedTime;
	private String dsId;
	private String connName;
	public String getSoeid() {
		return soeid;
	}
	public void setSoeid(String soeid) {
		this.soeid = soeid;
	}
	public String getQueryName() {
		return queryName;
	}
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}
	public String getQueryDesc() {
		return queryDesc;
	}
	public void setQueryDesc(String queryDesc) {
		this.queryDesc = queryDesc;
	}
	public int getQueryId() {
		return queryId;
	}
	public void setQueryId(int queryId) {
		this.queryId = queryId;
	}
	public String getQueryContent() {
		return queryContent;
	}
	public void setQueryContent(String queryContent) {
		this.queryContent = queryContent;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getDsId() {
		return dsId;
	}
	public void setDsId(String dsId) {
		this.dsId = dsId;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	public String getConnName() {
		return connName;
	}
	public void setConnName(String connName) {
		this.connName = connName;
	}
	
}
