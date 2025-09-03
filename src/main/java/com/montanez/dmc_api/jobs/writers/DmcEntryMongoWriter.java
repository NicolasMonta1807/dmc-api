package com.montanez.dmc_api.jobs.writers;

import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.montanez.dmc_api.model.DmcEntry;

@Configuration
public class DmcEntryMongoWriter {

  @Bean
  public MongoItemWriter<DmcEntry> writer(MongoTemplate mongoTemplate) {
    return new MongoItemWriterBuilder<DmcEntry>()
        .template(mongoTemplate)
        .collection("dmc_entries")
        .build();
  }

}
