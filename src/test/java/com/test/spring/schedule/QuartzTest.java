package com.test.spring.schedule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.AnnualCalendar;
import org.quartz.spi.MutableTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.model.dto.ScheduleJob;
import com.demo.spring.schedule.quartz.JobService;
import com.demo.spring.schedule.quartz.SimpleJob;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/schedule/spring-quartz-config.xml"})
public class QuartzTest {
	
	final static Logger logger = LoggerFactory.getLogger(QuartzTest.class);
	
	@Resource
	private JobService jobService;
	
	@Test
	public void testConfiguration() {
		sleep(20);
	}
	
	//@Test
	public void testQuartzTask() throws SchedulerException {
		
		ScheduleJob scheduleJob = new ScheduleJob();
		scheduleJob.setJobId("10001");
		scheduleJob.setJobName("data_import");
		scheduleJob.setJobGroup("dataWork");
		scheduleJob.setJobStatus("1");
		scheduleJob.setCronExpression("0/5 * * * * ?");
		scheduleJob.setDesc("this is job description");
		
		logger.info("-------------->add and start job");
		pringJobSize();//0
		jobService.addJob(scheduleJob);
		pringJobSize();//1
		sleep(16);
		
		logger.info("-------------->pause job");
		jobService.pauseJob(scheduleJob);
		sleep(3);
		
		logger.info("-------------->run job directly");
		jobService.runJob(scheduleJob);
		sleep(6);
		
		logger.info("-------------->resume job");
		jobService.resumeJob(scheduleJob);
		sleep(11);
		
		logger.info("-------------->updateJob job");
		scheduleJob.setCronExpression("0/2 * * * * ?");
		jobService.updateJob(scheduleJob);
		sleep(7);
		
		logger.info("-------------->delete job");
		jobService.deleteJob(scheduleJob);
		pringJobSize();//0
		sleep(3);
	}
	
	private void pringJobSize() throws SchedulerException {
		logger.info("Job size: " + jobService.getAllJobs().size());
	}
	
	private void sleep(int duration) {
		try {
			Thread.sleep(duration * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void calendarTest() throws SchedulerException {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler scheduler = sf.getScheduler();
		
		//runs every year
		AnnualCalendar holidays = new AnnualCalendar();
		
		Calendar laborDay = new GregorianCalendar();
		laborDay.add(Calendar.MONTH, 5);
		laborDay.add(Calendar.DATE, 1);
		
		Calendar nationalDay = new GregorianCalendar();
		laborDay.add(Calendar.MONTH, 10);
		laborDay.add(Calendar.DATE, 1);
		
		ArrayList<Calendar> calendars = new ArrayList<Calendar>();
		calendars.add(laborDay);
		calendars.add(nationalDay);
		
		//exclude labor day and national day
		holidays.setDaysExcluded(calendars);
		scheduler.addCalendar("holidays", holidays, false, false);
		
		Date runDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(runDate);
        c.set(Calendar.MONTH, 3);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 10);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        runDate = c.getTime();
        
        JobDetail job = JobBuilder.newJob(SimpleJob.class).withIdentity("name", "group").build();
        SimpleScheduleBuilder sb = SimpleScheduleBuilder.simpleSchedule();
        SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("name", "group").withSchedule(sb).build();
        
        ((MutableTrigger)trigger).setStartTime(runDate);
        ((MutableTrigger)trigger).setCalendarName("holidays");
        scheduler.scheduleJob(job, trigger);
        scheduler.start();
	}
}
