package com.montanez.dmc_api.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.montanez.dmc_api.model.dmc.DmcEntry;
import com.montanez.dmc_api.model.dmc.DmcEntryRaw;

@Configuration
public class DmcImporterJob {

  @Bean
  public Job importDmcJob(JobRepository jobRepository, Step step) {
    return new JobBuilder("importDmcJob", jobRepository)
        .incrementer(new RunIdIncrementer())
        .flow(step)
        .end()
        .build();
  }

  @Bean
  public Step step(
      JobRepository jobRepository,
      PlatformTransactionManager transactionManager,
      FlatFileItemReader<DmcEntryRaw> reader,
      ItemProcessor<DmcEntryRaw, DmcEntry> processor,
      MongoItemWriter<DmcEntry> writer) {

    return new StepBuilder("step", jobRepository)
        .<DmcEntryRaw, DmcEntry>chunk(10, transactionManager)
        .reader(reader)
        .processor(processor)
        .writer(writer)
        .build();

  }
}
