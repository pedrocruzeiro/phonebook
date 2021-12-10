package com.pt.pedrovcruzeiro.phonebook.service;

import com.pt.pedrovcruzeiro.phonebook.entity.dto.in.PhoneNumberBusinessSectorResponse;

public interface BusinessSectorService {

  public PhoneNumberBusinessSectorResponse retrievePhoneNumberBusinessSector(String phoneNumber);
}
