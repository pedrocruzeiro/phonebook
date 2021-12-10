package com.pt.pedrovcruzeiro.phonebook.util.interceptor;

import brave.Span;
import brave.Tracer;
import com.pt.pedrovcruzeiro.phonebook.util.annotation.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.Instant;

import static com.pt.pedrovcruzeiro.phonebook.util.constant.PhonebookConstants.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class MdcContextProviderInterceptor implements HandlerInterceptor {

  private final Tracer tracer;

  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) {

    if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
      HandlerMethod handlerMethod = (HandlerMethod) handler;

      if (handlerMethod.getBeanType().getName().contains("com.pt.pedrovcruzeiro.phonebook")) {

        ApiOperation serviceOperationAnnotation =
            handlerMethod.getMethodAnnotation(ApiOperation.class);
        if (serviceOperationAnnotation != null) {
          MDC.put(API_OPERATION, serviceOperationAnnotation.value());
        }

        Span span = tracer.currentSpan();
        String traceId = span.context().traceIdString();
        MDC.put(TIMESTAMP, Timestamp.from(Instant.now()).toString());
        MDC.put(TRACE_ID, traceId);
      }
    }
    return true;
  }
}
