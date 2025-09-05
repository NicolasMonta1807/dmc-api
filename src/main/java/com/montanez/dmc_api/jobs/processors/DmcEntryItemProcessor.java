package com.montanez.dmc_api.jobs.processors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.montanez.dmc_api.model.dmc.DmcEntry;
import com.montanez.dmc_api.model.dmc.DmcEntryRaw;

@Configuration
public class DmcEntryItemProcessor {

  @Bean
  public ItemProcessor<DmcEntryRaw, DmcEntry> processor() {
    return raw -> {
      String number = raw.getNumber();
      String name = raw.getName();
      String baseName = name;
      String variation = "";

      if (name != null) {
        if (name.contains(" - ")) {
          String[] parts = name.split(" - ", 2);
          baseName = parts[0].trim();
          variation = parts[1].trim();
        } else if (name.contains("/")) {
          String[] parts = name.split("/", 2);
          baseName = parts[0].trim();
          variation = parts[1].trim(); // fixed
        }
      }

      List<String> similars = new ArrayList<>();
      if (raw.getSimilar() != null && !raw.getSimilar().isBlank()) {
        String cleaned = raw.getSimilar()
            .replace("[", "")
            .replace("]", "")
            .replace("'", "")
            .trim();
        if (!cleaned.isEmpty()) {
          similars = Arrays.stream(cleaned.split(","))
              .map(String::trim)
              .toList();
        }
      }

      return DmcEntry.builder()
          .number(number)
          .baseName(baseName)
          .variation(variation)
          .red(raw.getRed() != null ? raw.getRed().intValue() : 0)
          .green(raw.getGreen() != null ? raw.getGreen().intValue() : 0)
          .blue(raw.getBlue() != null ? raw.getBlue().intValue() : 0)
          .hex(raw.getHex().toLowerCase().replaceAll("#", ""))
          .similars(similars)
          .build();
    };
  }

}
