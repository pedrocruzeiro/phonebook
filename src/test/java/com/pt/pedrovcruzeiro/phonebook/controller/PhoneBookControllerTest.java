package com.pt.pedrovcruzeiro.phonebook.controller;

import com.pt.pedrovcruzeiro.phonebook.entity.dto.out.AggregateResponse;
import com.pt.pedrovcruzeiro.phonebook.service.PhonebookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PhoneBookControllerTest {

  @InjectMocks private PhonebookControllerImpl phonebookController;

  @Mock private PhonebookService phonebookService;

  @Test
  public void aggregate_phone_numbers_success() {

    List<String> phoneNumberList =
        Arrays.asList("+1983236248", "001382355A", "+351917382672", "+35191734022", "+4439877");

    Map<String, Map<String, Integer>> prefixMap = new HashMap<>();

    prefixMap.put("3519173", Collections.singletonMap("Clothing", 2));
    prefixMap.put("44", Collections.singletonMap("Banking", 1));
    prefixMap.put("1", Collections.singletonMap("Technology", 1));

    AggregateResponse aggregateResponse = AggregateResponse.builder().prefix(prefixMap).build();

    when(phonebookService.aggregatePhoneNumbers(phoneNumberList)).thenReturn(aggregateResponse);

    ResponseEntity<Map<String, Map<String, Integer>>> response =
        phonebookController.aggregate(phoneNumberList);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertTrue(response.getBody().containsKey("1"));
    assertTrue(response.getBody().containsKey("44"));
    assertTrue(response.getBody().containsKey("3519173"));
    assertEquals(1, response.getBody().get("1").get("Technology"));
    assertEquals(1, response.getBody().get("44").get("Banking"));
    assertEquals(2, response.getBody().get("3519173").get("Clothing"));
  }
}
