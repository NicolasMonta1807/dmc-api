package com.montanez.dmc_api.jobs.processors;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.montanez.dmc_api.model.DmcEntry;
import com.montanez.dmc_api.model.DmcEntryRaw;

@Configuration
public class DmcEntryItemProcessor {

  @Bean
  public ItemProcessor<DmcEntryRaw, DmcEntry> processor() {
    return raw -> {
      String number = raw.getNumber();
      String name = raw.getName();
      String baseName = name;
      String variation = "";

      if (number.endsWith("*")) {
        System.out.println("Removing asterisk from: " + number);
        number = number.substring(0, number.length() - 1);
        System.out.println("Final result: " + number);
      }

      if (name.contains(" - ")) {
        String[] parts = name.split(" - ", 2);
        baseName = parts[0].trim();
        variation = parts[1].trim();
      } else if (name.contains("/")) {
        String[] parts = name.split("/", 2);
        baseName = parts[0].trim();
        variation = parts[0].trim();
      }

      return DmcEntry.builder()
          .number(number)
          .baseName(baseName)
          .variation(variation)
          .build();
    };
  }

}
