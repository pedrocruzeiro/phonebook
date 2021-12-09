package com.pt.pedrovcruzeiro.phonebook.entity.dto.out;

import lombok.*;

import java.util.Map;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AggregateResponse {

  private Map<String, Map<String, Integer>> prefix;
}
