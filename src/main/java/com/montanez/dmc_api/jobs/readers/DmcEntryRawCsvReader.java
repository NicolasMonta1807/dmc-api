package com.montanez.dmc_api.jobs.readers;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.montanez.dmc_api.model.DmcEntryRaw;

@Configuration
public class DmcEntryRawCsvReader {

  @Bean
  public FlatFileItemReader<DmcEntryRaw> reader() {
    return new FlatFileItemReaderBuilder<DmcEntryRaw>()
        .name("dmcEntryReader")
        .resource(new FileSystemResource("src/main/resources/dmc-floss.csv"))
        .delimited()
        .names("number", "name", "red", "green", "blue", "hex", "similar")
        .targetType(DmcEntryRaw.class)
        .linesToSkip(1) // Skip header
        .build();
  }

}
