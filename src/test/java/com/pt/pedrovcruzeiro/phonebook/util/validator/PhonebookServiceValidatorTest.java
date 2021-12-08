package com.pt.pedrovcruzeiro.phonebook.util.validator;

import com.pt.pedrovcruzeiro.phonebook.util.error.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PhonebookServiceValidatorTest {

    @InjectMocks
    private PhonebookServiceValidator phonebookServiceValidator;

    private List<String> nullNumbersList;

    private List<String> emptyNumbersList;

    @BeforeEach
    public void init() {

        emptyNumbersList = Collections.emptyList();
    }

    @Test
    public void retrieveValidNumbers_Success() {

        List<String> validNumbersList = Arrays.asList("123", "456", "789");

        Map<String, String> retrievedValidNumbers = phonebookServiceValidator.retrieveValidPhoneNumbers(validNumbersList);

        assertEquals(3, retrievedValidNumbers.size());
    }

    @Test
    public void retrieveValidNumbers_One_Invalid_Number_Success() {

        List<String> validNumbersList = Arrays.asList("+1983236248", "+1 7490276403", "001382355A", "+351917382672", "+35191734022");

        Map<String, String> retrievedValidNumbers = phonebookServiceValidator.retrieveValidPhoneNumbers(validNumbersList);

        assertEquals(4, retrievedValidNumbers.size());
        assertTrue(retrievedValidNumbers.containsKey("+1983236248"));
        assertTrue(retrievedValidNumbers.containsKey("+1 7490276403"));
        assertTrue(retrievedValidNumbers.containsKey("+351917382672"));
        assertTrue(retrievedValidNumbers.containsKey("+35191734022"));
    }

    @Test
    public void retrieveValidNumbers_5_Invalid_Numbers_Success() {

        List<String> validNumbersList = Arrays.asList("+123", "00456", "+ 789", "00 789", "789", "+789", "asd123", "00789 213 1", "001234", "0012345678976542");

        Map<String, String> retrievedValidNumbers = phonebookServiceValidator.retrieveValidPhoneNumbers(validNumbersList);

        assertEquals(5, retrievedValidNumbers.size());
        assertTrue(retrievedValidNumbers.containsKey("+123"));
        assertTrue(retrievedValidNumbers.containsKey("00456"));
        assertTrue(retrievedValidNumbers.containsKey("789"));
        assertTrue(retrievedValidNumbers.containsKey("00789 213 1"));
        assertTrue(retrievedValidNumbers.containsKey("+789"));
    }

    @Test
    public void retrieveValidNumbers_Null_List_Error() {

        ValidationException exception = assertThrows(ValidationException.class, () -> phonebookServiceValidator.retrieveValidPhoneNumbers(nullNumbersList));

        assertEquals("Phonebook list is null or empty.", exception.getMessage());
    }

    @Test
    public void retrieveValidNumbers_Empty_List_Error() {

        ValidationException exception = assertThrows(ValidationException.class, () -> phonebookServiceValidator.retrieveValidPhoneNumbers(emptyNumbersList));

        assertEquals("Phonebook list is null or empty.", exception.getMessage());

    }

}