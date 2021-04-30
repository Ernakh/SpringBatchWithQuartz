package demo.SpringBatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableBatchProcessing
@SpringBootApplication
public class DemoSpringBatchApplication 
{
	/*
	 
	 JOB Repository => Gerenciador do job -> Estado do Job
	 
	 Steps => etapas que devem ser excutadas para a finalização do job
	 
	 Laucher => JOB de fato, ele inicia a execução
	 
	 */
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory; //Componente que contrói o job e suas steps
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory; ////Componente que contrói e gerencia as steps
		
	@Bean
	public Step step()
	{
		return this.stepBuilderFactory.get("step1").tasklet((stepContribution, chunkContext) ->
		{
			System.out.println("Atos - UFN");
			return RepeatStatus.FINISHED;
		}).build();
	}
	
	@Bean
	public Job job()
	{
		return this.jobBuilderFactory.get("jobAtosUFN").incrementer(new RunIdIncrementer())
				.start(step()).build();
	}
	
	public static void main(String[] args) 
	{
		SpringApplication.run(DemoSpringBatchApplication.class, args);
	}
}
