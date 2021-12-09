package com.pt.pedrovcruzeiro.phonebook.util.formatter;

import com.pt.pedrovcruzeiro.phonebook.util.constant.PhonebookConstants;
import org.slf4j.MDC;

import java.sql.Timestamp;
import java.time.Instant;

public class PhonebookLogFormatter {

  private PhonebookLogFormatter() {}

  public static String format(String message) {
    return String.format(
        PhonebookConstants.PHONEBOOK_PREFIX_MSG,
        MDC.get(PhonebookConstants.API_OPERATION),
        Timestamp.from(Instant.now()),
        message);
  }
}
