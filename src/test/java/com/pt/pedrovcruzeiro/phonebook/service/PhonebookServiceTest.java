package com.pt.pedrovcruzeiro.phonebook.service;

import com.pt.pedrovcruzeiro.phonebook.entity.dto.in.PhoneNumberBusinessSectorResponse;
import com.pt.pedrovcruzeiro.phonebook.entity.dto.out.AggregateResponse;
import com.pt.pedrovcruzeiro.phonebook.util.validator.PhonebookServiceValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PhonebookServiceTest {

  @InjectMocks private PhonebookServiceImpl phonebookService;

  @Mock private BusinessSectorServiceImpl businessSectorService;

  @Mock private PhonebookServiceValidator phonebookServiceValidator;

  List<String> PHONE_NUMBER_LIST;

  Map<String, String> VALID_PHONE_NUMBER_MAP = new HashMap<>();

  public static final String PHONE_NUMBER_1 = "+1983236248";

  public static final String PHONE_NUMBER_2 = "+1 7490276403";

  public static final String PHONE_NUMBER_3 = "+351917382672";

  public static final String PHONE_NUMBER_4 = "+35191734022";

  public static final String CLOTHING = "Clothing";

  public static final String BANKING = "Banking";

  @BeforeEach
  void init() {

    PHONE_NUMBER_LIST =
        Arrays.asList(PHONE_NUMBER_1, PHONE_NUMBER_2, "001382355A", PHONE_NUMBER_3, PHONE_NUMBER_4);

    VALID_PHONE_NUMBER_MAP.put(PHONE_NUMBER_1, "1");
    VALID_PHONE_NUMBER_MAP.put(PHONE_NUMBER_2, "1");
    VALID_PHONE_NUMBER_MAP.put(PHONE_NUMBER_3, "351");
    VALID_PHONE_NUMBER_MAP.put(PHONE_NUMBER_4, "351");

    when(businessSectorService.retrievePhoneNumberBusinessSector(PHONE_NUMBER_1))
        .thenReturn(
            PhoneNumberBusinessSectorResponse.builder()
                .number(PHONE_NUMBER_1)
                .sector(CLOTHING)
                .build());
    when(businessSectorService.retrievePhoneNumberBusinessSector(PHONE_NUMBER_2))
        .thenReturn(
            PhoneNumberBusinessSectorResponse.builder()
                .number(PHONE_NUMBER_2)
                .sector(CLOTHING)
                .build());
    when(businessSectorService.retrievePhoneNumberBusinessSector(PHONE_NUMBER_3))
        .thenReturn(
            PhoneNumberBusinessSectorResponse.builder()
                .number(PHONE_NUMBER_3)
                .sector(BANKING)
                .build());
    when(businessSectorService.retrievePhoneNumberBusinessSector(PHONE_NUMBER_4))
        .thenReturn(
            PhoneNumberBusinessSectorResponse.builder()
                .number(PHONE_NUMBER_4)
                .sector(BANKING)
                .build());
  }

  @Test
  void retrievePhoneNumbersBusinessSector_Success() {

    when(phonebookServiceValidator.retrieveValidPhoneNumbers(PHONE_NUMBER_LIST))
        .thenReturn(VALID_PHONE_NUMBER_MAP);

    AggregateResponse response = phonebookService.aggregatePhoneNumbers(PHONE_NUMBER_LIST);

    assertNotNull(response);
    assertNotNull(response.getPrefix());
    assertTrue(response.getPrefix().containsKey("1"));
    assertTrue(response.getPrefix().get("1").containsKey(CLOTHING));
    assertEquals(2, response.getPrefix().get("1").get(CLOTHING));
    assertTrue(response.getPrefix().containsKey("351"));
    assertTrue(response.getPrefix().get("351").containsKey(BANKING));
    assertEquals(2, response.getPrefix().get("351").get(BANKING));
  }
}
