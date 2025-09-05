package com.montanez.dmc_api.model;

import java.util.ArrayList;
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

  private int red;
  private int green;
  private int blue;

  private String hex;

  @Builder.Default
  private List<String> similars = new ArrayList<>();
}
