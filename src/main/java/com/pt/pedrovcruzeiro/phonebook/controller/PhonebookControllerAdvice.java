package com.pt.pedrovcruzeiro.phonebook.controller;

import com.pt.pedrovcruzeiro.phonebook.entity.dto.out.ErrorMessageResponse;
import com.pt.pedrovcruzeiro.phonebook.util.constant.PhonebookConstants;
import com.pt.pedrovcruzeiro.phonebook.util.error.RetrieveBusinessSectorException;
import com.pt.pedrovcruzeiro.phonebook.util.error.ValidationException;
import com.pt.pedrovcruzeiro.phonebook.util.formatter.PhonebookLogFormatter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Optional;

import static com.pt.pedrovcruzeiro.phonebook.util.constant.PhonebookConstants.*;

@Slf4j
@ControllerAdvice
public class PhonebookControllerAdvice implements ResponseBodyAdvice<Object> {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(RetrieveBusinessSectorException.class)
  public ResponseEntity<ErrorMessageResponse> handleRetrieveBusinessSectorException(
      RetrieveBusinessSectorException e) {
    return buildErrorMessageResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<ErrorMessageResponse> handleValidationException(
      ValidationException e) {
    return buildErrorMessageResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
  }

  private ResponseEntity<ErrorMessageResponse> buildErrorMessageResponseEntity(
      String msg, HttpStatus httpStatus) {
    log.error(PhonebookLogFormatter.format(msg));
    return new ResponseEntity<>(
        ErrorMessageResponse.builder()
            .timestamp(MDC.get(TIMESTAMP))
            .traceId(MDC.get(TRACE_ID))
            .operation(MDC.get(API_OPERATION))
            .code(httpStatus.value())
            .message(msg)
            .build(),
        httpStatus);
  }

  @Override
  public boolean supports(
      MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
    return true;
  }

  @Override
  public Object beforeBodyWrite(
      Object body,
      MethodParameter methodParameter,
      MediaType mediaType,
      Class<? extends HttpMessageConverter<?>> aClass,
      ServerHttpRequest serverHttpRequest,
      ServerHttpResponse serverHttpResponse) {

    serverHttpResponse.getHeaders().add(TRACE_ID_HEADER, MDC.get(TRACE_ID));
    serverHttpResponse
        .getHeaders()
        .add(
            API_OPERATION_HEADER,
            Optional.ofNullable(MDC.get(API_OPERATION)).orElse(UNDEFINED_SERVICE_OPERATION));
    return body;
  }
}
