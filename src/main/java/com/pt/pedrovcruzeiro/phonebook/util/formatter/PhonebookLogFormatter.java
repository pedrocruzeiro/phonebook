package com.pt.pedrovcruzeiro.phonebook.util.formatter;

import com.pt.pedrovcruzeiro.phonebook.util.constant.PhonebookConstants;
import org.slf4j.MDC;

public class PhonebookLogFormatter {

  private PhonebookLogFormatter() {}

  public static String format(String message) {
    return String.format(
        PhonebookConstants.PHONEBOOK_PREFIX_MSG,
        MDC.get(PhonebookConstants.API_OPERATION),
        MDC.get(PhonebookConstants.TRACE_ID),
        MDC.get(PhonebookConstants.TIMESTAMP),
        message);
  }
}
