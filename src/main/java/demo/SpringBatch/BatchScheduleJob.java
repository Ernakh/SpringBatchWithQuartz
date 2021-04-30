package demo.SpringBatch;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class BatchScheduleJob extends QuartzJobBean
{
	@Autowired
	private Job job;
	
	@Autowired
	private JobExplorer jobExplorer;
	
	@Autowired
	private JobLauncher jobLauncher;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException 
	{
		JobParameters jobParams = new JobParametersBuilder(this.jobExplorer)
				.getNextJobParameters(this.job)
				.toJobParameters();
		
		try 
		{
			this.jobLauncher.run(this.job, jobParams);
		} 
		catch (Exception e) 
		{
			
		}
	}
}
