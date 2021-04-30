package demo.SpringBatch;

import java.sql.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfiguration 
{
	@Bean
	public JobDetail quartzJobDetail()
	{
		return JobBuilder.newJob(BatchScheduleJob.class).storeDurably().build();
	}
	
	@Bean
	public Trigger jobTrigger()
	{
		SimpleScheduleBuilder schedule = SimpleScheduleBuilder
				.simpleSchedule()
				.withIntervalInSeconds(4)
				.withRepeatCount(5);
		
		return TriggerBuilder.newTrigger()
				.forJob(quartzJobDetail())
				.withSchedule(schedule)
				.build();
	}
}
