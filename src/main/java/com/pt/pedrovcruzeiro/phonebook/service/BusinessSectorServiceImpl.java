package com.pt.pedrovcruzeiro.phonebook.service;

import com.pt.pedrovcruzeiro.phonebook.entity.dto.in.PhoneNumberBusinessSectorResponse;
import com.pt.pedrovcruzeiro.phonebook.feign.BusinessSectorFeignClient;
import com.pt.pedrovcruzeiro.phonebook.util.error.RetrieveBusinessSectorException;
import com.pt.pedrovcruzeiro.phonebook.util.formatter.PhonebookLogFormatter;
import feign.FeignException;
import feign.RetryableException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@CacheConfig(cacheNames = {"businessSector"})
public class BusinessSectorServiceImpl implements BusinessSectorService {

  private final BusinessSectorFeignClient businessSectorFeignClient;

  @Cacheable(
      cacheNames = {"businessSector"},
      key = "#phoneNumber")
  public PhoneNumberBusinessSectorResponse retrievePhoneNumberBusinessSector(String phoneNumber) {
    PhoneNumberBusinessSectorResponse response;
    try {
      response = businessSectorFeignClient.retrieveBusinessSector(phoneNumber);
    } catch (FeignException e) {
      log.error(PhonebookLogFormatter.format(e.getMessage()));
      throw new RetrieveBusinessSectorException("Business Sector API is unavailable.");
    }

    return response;
  }
}
