package com.pt.pedrovcruzeiro.phonebook.controller;

import com.pt.pedrovcruzeiro.phonebook.entity.dto.out.AggregateResponse;
import com.pt.pedrovcruzeiro.phonebook.service.PhonebookService;
import com.pt.pedrovcruzeiro.phonebook.util.formatter.PhonebookLogFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PhonebookControllerImpl implements PhonebookController {

  private final PhonebookService phonebookService;

  @Override
  public ResponseEntity<Map<String, Map<String, Integer>>> aggregate(List<String> phoneNumbers) {

    log.info(
        PhonebookLogFormatter.format("Started processing number aggregation by prefix and sector"));

    AggregateResponse response = phonebookService.aggregatePhoneNumbers(phoneNumbers);

    log.info(
        PhonebookLogFormatter.format(
            "Finished processing number aggregation by prefix and sector"));

    return ResponseEntity.ok(response.getPrefix());
  }
}
