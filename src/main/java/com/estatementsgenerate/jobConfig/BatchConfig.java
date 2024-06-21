package com.estatementsgenerate.jobConfig;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.extensions.excel.poi.PoiItemReader;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;

import com.estatementsgenerate.mapper.CustomerMapper;
import com.estatementsgenerate.model.Customer;
import com.estatementsgenerate.processor.ItemProcessorFile;
import com.estatementsgenerate.repositoies.CustomerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@Configuration
@RequiredArgsConstructor
public class BatchConfig {
	
private final	JobRepository jobRepository;
private final	PlatformTransactionManager manager;
private final	CustomerRepository customerRepository;

	
	
@Bean
	public Job jobStart(DataSource dataSource) {
		return new JobBuilder("job",jobRepository)
				.start(step())
				.next(createExcelWithInvalidEmail(jobRepository, dataSource))
				.incrementer(new RunIdIncrementer())
				.build();

	}
				
		
		
		

@Bean
public Step step() {
System.out.println("ttttttttttttttttttt");
	return new StepBuilder("step",jobRepository)
			
			.<Customer,Customer>chunk(10,manager)
			.reader(multipleFileItemReader())
			.writer(writer())
			.build();
	
}






	
@Bean
public RepositoryItemWriter<? super Customer> writer() {
	return new RepositoryItemWriterBuilder<Customer>()
			.methodName("save")
			.repository(customerRepository)
			.build();
}






@Bean
public MultiResourceItemReader<Customer> multipleFileItemReader() {
	System.out.println("MultiResourceItemReader------------");
	return new MultiResourceItemReaderBuilder<Customer>()
			.name("multiplefile")
			.resources(resouresFiles())
			.delegate(reader())
			.build();
	
}


	
	private Resource[] resouresFiles() {
		return new Resource[] {
				new ClassPathResource("7DAYSNO.xls"),
				
		};
	
	}
		
	@Bean
	public ResourceAwareItemReaderItemStream<Customer> reader() {
		System.out.println("ResourceAwareItemReaderItemStream------------");

		PoiItemReader<Customer>itemReader=new PoiItemReader<>();
		itemReader.setName("excelfile");
		itemReader.setSaveState(false);
		itemReader.setLinesToSkip(1);
		itemReader.setRowMapper(new CustomerMapper());
		return itemReader;
	}

	
	@Bean
	public ItemProcessor<Customer, Customer> custProcessor() {
		System.out.println("ItemProcessor-------------------");
		return new ItemProcessorFile();
	}

	
  	@Bean
	   public ItemWriter<? super Customer> writeInExcel() {
		return new WriterExcel();
	}
	
	@Bean
	public Step createExcelWithInvalidEmail(JobRepository jobRepository,DataSource dataSource) {
		System.out.println("Step-------------------11133");

		return new StepBuilder("step2", jobRepository)
				.<Customer, Customer>chunk(10, manager)
                .reader(reader())
				.processor(custProcessor())
				.writer(writeInExcel())
				.build();
	}
		


}




		




