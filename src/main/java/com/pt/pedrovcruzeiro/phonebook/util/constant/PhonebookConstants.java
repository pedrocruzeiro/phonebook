package com.pt.pedrovcruzeiro.phonebook.util.constant;

public class PhonebookConstants {

  private PhonebookConstants() {
    throw new IllegalStateException("Utility class");
  }

  /*
   * Operation
   */
  public static final String PHONEBOOK_BACKEND = "Phonebook Service";

  /** API Operations */
  public static final String AGGREGATION_PHONEBOOK_API_OPERATION = "aggregate";

  /** MDC Keys */
  public static final String API_OPERATION = "operation";

  public static final String TRACE_ID = "trace-id";
  public static final String TIMESTAMP = "timestamp";

  /*
   * Message
   */
  public static final String PHONEBOOK_PREFIX_MSG = "Operation [%s] ::Timestamp [%s] :: %s";

  /** Misc */
  public static final String FRONT_SLASH_DELIMITER = "/";

  public static final String COLON_WHITE_SPACE_DELIMITER = ", ";
  public static final String WHITE_SPACE_DELIMITER = " ";
  public static final String SEMI_COLON_DELIMITER = ";";
}
