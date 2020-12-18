package com.aerosimo.apps;

/**
 * This piece of work is to print out Helloworld triggered by Jobscheduler.
 * 
 * @author Aerosimo
 * @Organization: Aerosimo Ltd
 * 
 * Last Updated: 202009241300Z
 * 
 * Copyright (c) 2020 Aerosimo
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class MainScheduler {

	public static void main(String[] args) throws SchedulerException {
		
		// Give the job a name
		String name = "CronJob";
		
		// Create the actual Job		
		JobDetail job;
		job = JobBuilder.newJob(HelloWorld.class).build();
		
		// Create the job trigger
		Trigger trig;
		trig = TriggerBuilder.newTrigger().withIdentity(name).withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(20).repeatForever()).build();

		// Create the Scheduler
		Scheduler shed;
		shed = StdSchedulerFactory.getDefaultScheduler();
		shed.start();
		shed.scheduleJob(job, trig);
	}

}