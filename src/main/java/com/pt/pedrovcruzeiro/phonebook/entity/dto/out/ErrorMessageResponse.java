package com.pt.pedrovcruzeiro.phonebook.entity.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessageResponse {
  private String timestamp;
  private String traceId;
  private String operation;
  private int code;
  private String message;
}
