package com.pt.pedrovcruzeiro.phonebook.service;

import com.pt.pedrovcruzeiro.phonebook.entity.dto.in.PhoneNumberBusinessSectorResponse;
import com.pt.pedrovcruzeiro.phonebook.feign.BusinessSectorFeignClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BusinessSectorServiceTest {

  @InjectMocks private BusinessSectorServiceImpl businessSectorService;

  @Mock private BusinessSectorFeignClient businessSectorFeignClient;

  private static final String CLOTHING = "Clothing";
  private static final String PHONE_NUMBER = "+351323423";

  @Test
  public void retrieveBusinessSector_Success() {

    when(businessSectorFeignClient.retrieveBusinessSector(PHONE_NUMBER))
        .thenReturn(
            PhoneNumberBusinessSectorResponse.builder()
                .sector(CLOTHING)
                .number(PHONE_NUMBER)
                .build());

    PhoneNumberBusinessSectorResponse response =
        businessSectorService.retrievePhoneNumberBusinessSector(PHONE_NUMBER);

    assertNotNull(response);
    assertEquals(CLOTHING, response.getSector());
    assertEquals(PHONE_NUMBER, response.getNumber());
  }
}
