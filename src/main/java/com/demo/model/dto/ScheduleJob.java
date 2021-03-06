package com.demo.model.dto;

public class ScheduleJob {
	private String jobId;
	private String jobName;
	private String jobGroup;
	private String desc;
	private String jobStatus;
	private String cronExpression;
	
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobGroup() {
		return jobGroup;
	}
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String jobDesc) {
		this.desc = jobDesc;
	}
	/*0: disabled 1: enabled 2: deleted*/
	public String getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	public String getCronExpression() {
		return cronExpression;
	}
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
}
