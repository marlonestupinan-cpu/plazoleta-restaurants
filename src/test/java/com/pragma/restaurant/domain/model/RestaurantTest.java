package com.pragma.restaurant.domain.model;

import com.pragma.restaurant.domain.exception.DomainException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RestaurantTest {

    @Test
    void shouldSetPhoneWhenFormatIsValid() {
        Restaurant restaurant = new Restaurant();
        String validPhone = "+573001234567";
        restaurant.setPhone(validPhone);
        assertEquals(validPhone, restaurant.getPhone());
    }

    @Test
    void shouldThrowExceptionWhenPhoneFormatIsInvalid() {
        Restaurant restaurant = new Restaurant();
        String invalidPhoneWithLetters = "abcde";
        String invalidPhoneWithSymbols = "300-123-4567";

        assertThrows(DomainException.class, () -> restaurant.setPhone(invalidPhoneWithLetters));
        assertThrows(DomainException.class, () -> restaurant.setPhone(invalidPhoneWithSymbols));
    }

    @Test
    void shouldThrowExceptionWhenPhoneLengthIsExceeded() {
        Restaurant restaurant = new Restaurant();
        String longPhone = "+12345678901234";
        assertThrows(DomainException.class, () -> restaurant.setPhone(longPhone));
    }

    @Test
    void shouldSetNitWhenItContainsOnlyNumbers() {
        Restaurant restaurant = new Restaurant();
        String validNit = "1234567890";
        restaurant.setNit(validNit);
        assertEquals(validNit, restaurant.getNit());
    }

    @Test
    void shouldThrowExceptionWhenNitContainsNonNumericCharacters() {
        Restaurant restaurant = new Restaurant();
        String invalidNit = "12345-ABC";
        assertThrows(DomainException.class, () -> restaurant.setNit(invalidNit));
    }

    @Test
    void shouldSetNameWhenItContainsLetters() {
        Restaurant restaurant = new Restaurant();
        String validName = "Restaurante Delicioso";
        restaurant.setName(validName);
        assertEquals(validName, restaurant.getName());
    }

    @Test
    void shouldSetNameWhenItContainsLettersAndNumbers() {
        Restaurant restaurant = new Restaurant();
        String validNameWithNumbers = "Plaza Food 5";
        restaurant.setName(validNameWithNumbers);
        assertEquals(validNameWithNumbers, restaurant.getName());
    }

    @Test
    void shouldThrowExceptionWhenNameContainsOnlyNumbers() {
        Restaurant restaurant = new Restaurant();
        String numericName = "1234567";
        assertThrows(DomainException.class, () -> restaurant.setName(numericName));
    }
}
