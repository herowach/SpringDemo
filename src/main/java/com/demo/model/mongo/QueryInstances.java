package com.demo.model.mongo;

import java.util.Date;

public class QueryInstances {
	private Long runId;
	private String soeid;
	private String queryContent;
	private String outputPath;
	private int status;
	private String failReason;
	private Date createTime;
	private Long queryTime;
	public Long getRunId() {
		return runId;
	}
	public void setRunId(Long runId) {
		this.runId = runId;
	}
	public String getSoeid() {
		return soeid;
	}
	public void setSoeid(String soeid) {
		this.soeid = soeid;
	}
	public String getQueryContent() {
		return queryContent;
	}
	public void setQueryContent(String queryContent) {
		this.queryContent = queryContent;
	}
	public String getOutputPath() {
		return outputPath;
	}
	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getFailReason() {
		return failReason;
	}
	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(Long queryTime) {
		this.queryTime = queryTime;
	}
}
