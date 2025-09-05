package com.montanez.dmc_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.montanez.dmc_api.data.DmcRepository;
import com.montanez.dmc_api.model.dmc.DmcEntry;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/dmc")
@RequiredArgsConstructor
public class DmcController {

  private final DmcRepository dmcRepository;

  @GetMapping("/all")
  public ResponseEntity<List<DmcEntry>> getAllEntries() {
    List<DmcEntry> entries = dmcRepository.findAll();
    return ResponseEntity.ok().body(entries);
  }

  @GetMapping("/find")
  public ResponseEntity<?> findEntries(
      @RequestParam(required = false) String number,
      @RequestParam(required = false) String baseName) {

    List<DmcEntry> entries;

    if (number != null && baseName != null) {
      entries = dmcRepository.findByNumberAndBaseName(number, baseName);
    } else if (number != null) {
      entries = dmcRepository.findByNumberIgnoreCase(number);
    } else if (baseName != null) {
      entries = dmcRepository.findByBaseNameIgnoreCase(baseName);
    } else {
      entries = dmcRepository.findAll();
    }

    // TODO: Handle with request exception
    if (entries.isEmpty()) {
      Map<String, String> error = Map.of("error", "No entries found with given parameters");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    return ResponseEntity.ok(entries);
  }

  @GetMapping("/{number}")
  public ResponseEntity<DmcEntry> getEntryByNumber(@PathVariable String number) {
    Optional<DmcEntry> entry = dmcRepository.findById(number);

    if (entry.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok().body(entry.get());
  }

  @GetMapping("/{number}/similars")
  public ResponseEntity<?> getSimilars(@PathVariable String number) {
    Optional<DmcEntry> entry = dmcRepository.findById(number);

    if (entry.isEmpty()) {
      Map<String, String> error = Map.of("error", "No entries found with given parameters");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    return ResponseEntity.ok().body(entry.get().getSimilars());
  }
}
