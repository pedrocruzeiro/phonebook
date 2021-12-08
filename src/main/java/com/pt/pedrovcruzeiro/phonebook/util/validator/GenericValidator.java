package com.pt.pedrovcruzeiro.phonebook.util.validator;

import com.pt.pedrovcruzeiro.phonebook.util.error.ValidationException;

import java.util.List;

public class GenericValidator {

    protected void validateListFieldNotNullOrEmpty(List list, String message) {

        if (list == null || list.isEmpty()) {
            throw new ValidationException(message);
        }

    }

}
