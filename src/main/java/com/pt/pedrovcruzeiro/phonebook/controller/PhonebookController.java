package com.pt.pedrovcruzeiro.phonebook.controller;

import com.pt.pedrovcruzeiro.phonebook.util.annotation.ApiOperation;
import com.pt.pedrovcruzeiro.phonebook.util.constant.PhonebookConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Tag(name = "phonebook", description = "Phonebook API")
public interface PhonebookController {

  @PostMapping("/aggregate")
  @Operation(
      summary = "",
      description = "Operation to aggregate phone numbers by prefix and sector",
      tags = {"phonebook"})
  @ApiOperation(PhonebookConstants.AGGREGATION_PHONEBOOK_API_OPERATION)
  ResponseEntity<Map<String, Map<String, Integer>>> aggregate(
      @RequestBody List<String> phoneNumbers);
}
