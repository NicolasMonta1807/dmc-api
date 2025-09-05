package com.montanez.dmc_api.data;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.montanez.dmc_api.model.DmcEntry;

public interface DmcRepository extends MongoRepository<DmcEntry, String> {
  List<DmcEntry> findByNumberIgnoreCase(String number);

  List<DmcEntry> findByBaseNameIgnoreCase(String baseName);

  List<DmcEntry> findByNumberAndBaseName(String number, String baseName);
}
