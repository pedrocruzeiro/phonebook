package com.pt.pedrovcruzeiro.phonebook.util.error;

import java.util.List;

import static com.pt.pedrovcruzeiro.phonebook.util.constant.PhonebookConstants.COLON_WHITE_SPACE_DELIMITER;
import static com.pt.pedrovcruzeiro.phonebook.util.constant.PhonebookConstants.WHITE_SPACE_DELIMITER;

public class ValidationException extends RuntimeException {

  private static final String INVALID_PHONE_NUMBER_MSG = "Invalid %s phone number %s.";
  private static final String RECOGNIZED_STATUS_MSG = "Recognized status %s.";

  public ValidationException(String entity) {
    super(entity);
  }

  public ValidationException(String entity, String status) {
    super(getFormattedInvalidResourceStatusMessage(entity, status));
  }

  public ValidationException(String entity, String name, List<String> status) {
    super(
        String.join(
            WHITE_SPACE_DELIMITER,
            getFormattedInvalidResourceStatusMessage(entity, name),
            getFormattedRecognizedResourceStatusMessage(status)));
  }

  private static String getFormattedInvalidResourceStatusMessage(String entity, String status) {
    return String.format(INVALID_PHONE_NUMBER_MSG, entity, status);
  }

  private static String getFormattedRecognizedResourceStatusMessage(List<String> status) {
    return String.format(RECOGNIZED_STATUS_MSG, String.join(COLON_WHITE_SPACE_DELIMITER, status));
  }
}
