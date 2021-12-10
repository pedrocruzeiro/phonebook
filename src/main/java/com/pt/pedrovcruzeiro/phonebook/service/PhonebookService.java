package com.pt.pedrovcruzeiro.phonebook.service;

import com.pt.pedrovcruzeiro.phonebook.entity.dto.out.AggregateResponse;

import java.util.List;

public interface PhonebookService {

  public AggregateResponse aggregatePhoneNumbers(List<String> phoneNumbers);
}
