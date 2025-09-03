package com.montanez.dmc_api.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "dmc_entries")
public class DmcEntry {

  @Id
  private String number;

  private String baseName;
  private String variation;
  private List<String> similars;
}
