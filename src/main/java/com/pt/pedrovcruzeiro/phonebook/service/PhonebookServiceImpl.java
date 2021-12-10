package com.pt.pedrovcruzeiro.phonebook.service;

import com.pt.pedrovcruzeiro.phonebook.entity.dto.in.PhoneNumberBusinessSectorResponse;
import com.pt.pedrovcruzeiro.phonebook.entity.dto.out.AggregateResponse;
import com.pt.pedrovcruzeiro.phonebook.util.error.RetrieveBusinessSectorException;
import com.pt.pedrovcruzeiro.phonebook.util.error.ValidationException;
import com.pt.pedrovcruzeiro.phonebook.util.formatter.PhonebookLogFormatter;
import com.pt.pedrovcruzeiro.phonebook.util.validator.PhonebookServiceValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class PhonebookServiceImpl implements PhonebookService {

  private final PhonebookServiceValidator phonebookServiceValidator;

  private final BusinessSectorService businessSectorService;

  public AggregateResponse aggregatePhoneNumbers(List<String> phoneNumbers) {

    log.debug("Processing the following phone numbers list: " + phoneNumbers);

    // Retrieve only valid numbers
    Map<String, String> validPhoneNumberList =
        phonebookServiceValidator.retrieveValidPhoneNumbers(phoneNumbers);

    log.debug("List of valid phone numbers: " + validPhoneNumberList);

    // Call business Sector API

    Map<String, String> phoneNumberListWithSectors =
        retrievePhoneNumbersBusinessSector(new ArrayList<>(validPhoneNumberList.keySet()));

    // Build Response

    AggregateResponse response =
        buildAggregatorResponse(validPhoneNumberList, phoneNumberListWithSectors);

    if (response.getPrefix().isEmpty()) {
      throw new ValidationException("No valid phone numbers where sent in the input.");
    }

    return response;
  }

  private AggregateResponse buildAggregatorResponse(
      Map<String, String> validPhoneNumbers, Map<String, String> phoneNumbersWithSectors) {

    Map<String, Map<String, Integer>> prefixes = new HashMap<>(Collections.emptyMap());

    for (String phoneNumber : phoneNumbersWithSectors.keySet()) {
      String tempPrefix = validPhoneNumbers.get(phoneNumber);
      String tempSector = phoneNumbersWithSectors.get(phoneNumber);
      Map<String, Integer> tempSectorMap;
      if (!prefixes.containsKey(tempPrefix)) {
        tempSectorMap = new HashMap<>(Collections.emptyMap());
        tempSectorMap.put(tempSector, 1);
      } else {
        tempSectorMap = prefixes.get(tempPrefix);
        if (!tempSectorMap.containsKey(tempSector)) {
          tempSectorMap.put(tempSector, 1);
        } else {
          tempSectorMap.put(tempSector, tempSectorMap.getOrDefault(tempSector, 0) + 1);
        }
      }
      prefixes.put(tempPrefix, tempSectorMap);
    }

    return AggregateResponse.builder().prefix(prefixes).build();
  }

  private Map<String, String> retrievePhoneNumbersBusinessSector(List<String> phoneNumbers) {

    Map<String, String> response = new HashMap<>(Collections.emptyMap());

    for (String phoneNumber : phoneNumbers) {

      PhoneNumberBusinessSectorResponse phoneNumberBusinessSectorResponse =
          businessSectorService.retrievePhoneNumberBusinessSector(phoneNumber);
      response.put(phoneNumber, phoneNumberBusinessSectorResponse.getSector());


    }
    return response;
    }
}
