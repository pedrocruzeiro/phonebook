package com.pt.pedrovcruzeiro.phonebook.controller;

import com.pt.pedrovcruzeiro.phonebook.entity.dto.out.AggregateResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Tag(name = "phonebook", description = "Phonebook API")
public interface PhonebookController {

    @PostMapping("/aggregate")
    @Operation(summary = "", description = "", tags = {"phonebook"})
    ResponseEntity aggregate(@RequestBody List<String> phoneNumbers);
}
