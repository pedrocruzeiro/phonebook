package com.pt.pedrovcruzeiro.phonebook.util.constant;

public class PhonebookConstants {

  /*
   * Operation
   */
  public static final String PHONEBOOK_BACKEND = "Phonebook Service";
  public static final String UNDEFINED_SERVICE_OPERATION = "Undefined";

  /** API Operations */
  public static final String AGGREGATION_PHONEBOOK_API_OPERATION = "aggregate";

  /** MDC Keys */
  public static final String API_OPERATION = "operation";

  public static final String TRACE_ID = "trace-id";
  public static final String TIMESTAMP = "timestamp";
  /** Header Names */
  public static final String TRACE_ID_HEADER = "Trace-Id";

  public static final String API_OPERATION_HEADER = "Service-Operation";
  /*
   * Message
   */
  public static final String PHONEBOOK_PREFIX_MSG =
      "Operation [%s] :: Trace-Id [%s] :: Timestamp [%s] :: %s";
  /** Misc */
  public static final String FRONT_SLASH_DELIMITER = "/";

  public static final String COLON_WHITE_SPACE_DELIMITER = ", ";
  public static final String WHITE_SPACE_DELIMITER = " ";
  public static final String SEMI_COLON_DELIMITER = ";";

  private PhonebookConstants() {
    throw new IllegalStateException("Utility class");
  }
}
