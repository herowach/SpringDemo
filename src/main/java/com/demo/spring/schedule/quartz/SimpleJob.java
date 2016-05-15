package com.demo.spring.schedule.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.model.dto.ScheduleJob;


@DisallowConcurrentExecution
public class SimpleJob implements Job {

	final static Logger logger = LoggerFactory.getLogger(SimpleJob.class);
	private static int count = 0;
	
	@Override
	public void execute(JobExecutionContext jobCtx)
			throws JobExecutionException {
		//JobDataMap jobDataMap = jobCtx.getTrigger().getJobDataMap();
		Object value = jobCtx.getMergedJobDataMap().get("scheduleJob");
		if (value instanceof ScheduleJob) {
			logger.info("Job {} triggered. count: {}", ((ScheduleJob)value).getJobName(), ++count);
		} else {
			logger.info("{} triggered. count: {}", jobCtx.getJobDetail().getKey().getName(), ++count);
		}
	}

}
