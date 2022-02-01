package com.pt.pedrovcruzeiro.phonebook.util.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@CacheConfig(cacheNames = {"prefixes"})
@RequiredArgsConstructor
public class PhonebookServiceValidator extends GenericValidator {

  private final PrefixCacheValidator prefixCacheValidator;

  public Map<String, String> retrieveValidPhoneNumbers(List<String> numbers) {

    validateListFieldNotNullOrEmpty(numbers, "Phonebook list is null or empty.");

    List<String> filteredPhoneNumbers =
        numbers.stream()
            .filter(s -> s.matches("([+]*|[00]*)([0-9 ]{7,12}$|[0-9 ]{3})"))
            .collect(Collectors.toList());

    return retrievePhoneNumbersWithValidPrefixes(filteredPhoneNumbers);
  }

  private Map<String, String> retrievePhoneNumbersWithValidPrefixes(List<String> phoneNumberList) {

    Map<String, String> phoneNumbersWithValidPrefixes = new HashMap<>(Collections.emptyMap());

    for (String phoneNumber : phoneNumberList) {
      String prefix = retrieveValidPrefix(phoneNumber);
      if (prefix != null && !prefix.isBlank()) {
        phoneNumbersWithValidPrefixes.put(phoneNumber, prefix);
      }
    }

    return phoneNumbersWithValidPrefixes;
  }

  private String retrieveValidPrefix(String phoneNumber) {
    String validPrefix = null;

    for (int i = 0; i < phoneNumber.length(); i++) {
      String tempPrefix = phoneNumber;
      tempPrefix = tempPrefix.replaceAll("(^[+]|^[0-0]{2})", "");
      tempPrefix = tempPrefix.substring(0, i + 1);
      if (prefixCacheValidator.isValidPrefix(tempPrefix)) {
        validPrefix = tempPrefix;
        return validPrefix;
      }
    }
    return null;
  }
}
