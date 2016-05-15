package com.demo.spring.schedule.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

//@DisallowConcurrentExecution  - don't allow multi job(thread/worker) are executed at the same time
//@PersistJobDataAfterExecution - serialize the jobMapData, and share between jobs(stateful job)
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class SpringJob extends QuartzJobBean {

	final static Logger logger = LoggerFactory.getLogger(SpringJob.class);
	private static int count = 0;
	public static final String KEY_JOB_DATA_MAP_COUNT = "jobDataMapCount";
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		
		JobDataMap data = context.getJobDetail().getJobDataMap();
		//get current applicationContext
		@SuppressWarnings("unused")
		ApplicationContext applicationContext = (ApplicationContext)data.get("applicationContext");
		
		//@PersistJobDataAfterExecution: make jobDataMap shared between jobs
		int jobDataMapCount = data.getInt(KEY_JOB_DATA_MAP_COUNT);
		
		//@DisallowConcurrentExecution: pause 15s, then the subsequent job will be pendding till this job finished
		if (count == 5) {
			logger.info("----------------- start sleep ------------------");
			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			logger.info("----------------- end sleep --------------------");
		}
		
		logger.info("SpringJob: count: {}, jobDataMapCount: {}", count, jobDataMapCount);
		count++;
		jobDataMapCount++;
		data.put(KEY_JOB_DATA_MAP_COUNT, jobDataMapCount);
	}
}
