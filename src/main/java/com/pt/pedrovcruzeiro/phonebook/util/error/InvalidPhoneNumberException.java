package com.pt.pedrovcruzeiro.phonebook.util.error;

import java.util.List;

import static com.pt.pedrovcruzeiro.phonebook.util.constant.PhonebookConstants.COLON_WHITE_SPACE_DELIMITER;
import static com.pt.pedrovcruzeiro.phonebook.util.constant.PhonebookConstants.WHITE_SPACE_DELIMITER;

public class InvalidPhoneNumberException extends RuntimeException {

    private static String RECOGNIZED_STATUS_MSG = "Recognized status %s.";

    public InvalidPhoneNumberException(String entity) {
        super(entity);
    }

}
