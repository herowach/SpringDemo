package com.demo.model.dto;

import java.io.Serializable;

public class ReportDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String rptId;
	private String rptName;
	private String createdBy;
	private String dateStr;
	
	public String getRptId() {
		return rptId;
	}
	public void setRptId(String rptId) {
		this.rptId = rptId;
	}
	public String getRptName() {
		return rptName;
	}
	public void setRptName(String rptName) {
		this.rptName = rptName;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	
	@Override
	public String toString() {
		return this.rptName + "(" + this.rptId + ") created by " + this.createdBy + " on " + this.dateStr;
	}
}
