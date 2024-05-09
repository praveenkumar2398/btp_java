package com.assestmanagement.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.multipart.MultipartFile;

import com.assestmanagement.model.EmployeeModel;
import com.assestmanagement.model.TestModel;
import com.assestmanagement.repository.EmployeeRepository;
import com.assestmanagement.repository.TestRepository;

@Configuration
public class SpringBatchConfig {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private TestRepository testRepository;
	
	   public FlatFileItemReader<EmployeeModel> itemReader(){
	        FlatFileItemReader<EmployeeModel> itemReader = new FlatFileItemReader<>();
	        itemReader.setResource(new FileSystemResource("src/main/resources/sample.csv"));
	        itemReader.setName("csv-reader");
	        itemReader.setLinesToSkip(1);
	        itemReader.setLineMapper(lineMapper());
	        return itemReader;
	    }

	    private LineMapper<EmployeeModel> lineMapper() {
	        DefaultLineMapper<EmployeeModel> lineMapper = new DefaultLineMapper<>();

	        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
	        tokenizer.setDelimiter(",");
	       // tokenizer.setNames("id","name");
	        tokenizer.setNames("employeeId","firstName","lastName","emailId","password","dateOfJoining","contactNumber","dateOfBirth","designation");
	        tokenizer.setStrict(false);

	        BeanWrapperFieldSetMapper mapper = new BeanWrapperFieldSetMapper<>();
	        mapper.setTargetType(EmployeeModel.class);

	        lineMapper.setFieldSetMapper(mapper);
	        lineMapper.setLineTokenizer(tokenizer);
	        return lineMapper;
	    }
	

	@Bean
	public EmployeeProcessor processor() {
		return new EmployeeProcessor();
	}
	    
//		@Bean
//		public TestProcessor processor() {
//			return new TestProcessor();
//		}

    @Bean
   public RepositoryItemWriter<EmployeeModel> itemWriter(){
        RepositoryItemWriter<EmployeeModel> writer = new RepositoryItemWriter<>();
        writer.setRepository(employeeRepository);
        writer.setMethodName("save");
        return writer;
   }
    
	@Bean
    public Step step(JobRepository repository, PlatformTransactionManager transactionManager){
        return new StepBuilder("csv-step",repository)
                .<EmployeeModel,EmployeeModel>chunk(10,transactionManager)
                .reader(itemReader())
                .processor(processor())
                .writer(itemWriter())
                .taskExecutor(taskExecutor())
                .build();
   }

	@Bean(name="csvJob")
    public Job job(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new JobBuilder("csv-job",jobRepository)
	                .flow(step(jobRepository,transactionManager)).end().build();	
    }

    private TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(10);
        return asyncTaskExecutor;
    }
}
