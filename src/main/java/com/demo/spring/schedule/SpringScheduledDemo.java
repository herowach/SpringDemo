package com.demo.spring.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("springTimerTask")
public class SpringScheduledDemo {

	final static Logger logger = LoggerFactory.getLogger(SpringScheduledDemo.class);
	
	//@Scheduled(fixedDelay=1000)//run every 1 second
	/*
	 cron expression:
	 	1: second	range:0-59	eg: 5/10 means starting at the fifth second, and execute every 10 seconds
	 	2: minute	range:0-59
	 	3: hour		range:0-23
	 	4: date		range:1-31 or L(means the last day of a month) 
	 	5: month	range:1-12 or JAN-DEC
	 	6: day		range:1-7 or SUN-SAT
	 	7: year		range:1970-2099	(optional) 
	 	
	 	in spring, there should have at least one ? at date or day
	 	examples:
	 		0 0 08 * * ?			runs at 8:00 AM every day
	 		0 15 10 * * ? 2015		runs at 10:15 AM every day in 2015
	 		0 * 14 * * ?			runs every minute in 2:00 PM-2:59 PM every day
	 		0 0/5 14,18 * * ?		runs every 5 minutes in 2:00 PM-2:55 PM and 6:00 PM-6:55 PM
	 		0 0-5 14 * * ?			runs every minute in 2:00 PM-2:05PM
	 		0 15 10 ? * MON-FRI		runs at 10:15 AM on weekday
	 		0 0 10 L * ?			runs at 10:00 PM on the last day of each month
	 		0 0 10 ? * 6#3			runs at 10:00 PM on the third week of each month
	 */
	@Scheduled(cron="0/2 * * * * *")//runs every 2 seconds
	public void run() {
		logger.info(Thread.currentThread().getName()+" "+"work1");
	}
	
	@Scheduled(cron="0/2 * * * * *")
	public void run2() {
		logger.info(Thread.currentThread().getName()+" "+"work2");
	}
	
	@Scheduled(cron="0/2 * * * * *")
	public void run3() {
		logger.info(Thread.currentThread().getName()+" "+"work3");
	}
	
	@Scheduled(cron="0/2 * * * * *")
	public void run4() {
		logger.info(Thread.currentThread().getName()+" "+"work4");
	}
	
	@Scheduled(cron="0/2 * * * * *")
	public void run5() {
		logger.info(Thread.currentThread().getName()+" "+"work5");
	}

	@Scheduled(cron="0/2 * * * * *")
	public void run6() {
		logger.info(Thread.currentThread().getName()+" "+"work6");
	}
}
