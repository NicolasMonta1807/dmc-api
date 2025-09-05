package com.montanez.dmc_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DmcEntryRaw {
  private String number;
  private String name;
  private Double red;
  private Double green;
  private Double blue;
  private String hex;
  private String similar;
}
