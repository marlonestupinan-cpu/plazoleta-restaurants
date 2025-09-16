package com.pragma.restaurant.domain.model;

import com.pragma.restaurant.domain.exception.DomainException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DishTest {

    @Test
    void shouldSetPriceWhenPriceIsPositive() {
        Dish dish = new Dish();
        Integer price = 25000;

        dish.setPrice(price);

        assertEquals(price, dish.getPrice());
    }

    @Test
    void shouldThrowExceptionWhenPriceIsZero() {
        Dish dish = new Dish();
        Integer zeroPrice = 0;

        DomainException exception = assertThrows(DomainException.class, () -> {
            dish.setPrice(zeroPrice);
        });

        assertEquals("El precio debe ser un entero positivo", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenPriceIsNegative() {
        Dish dish = new Dish();
        Integer negativePrice = -500;

        DomainException exception = assertThrows(DomainException.class, () -> {
            dish.setPrice(negativePrice);
        });

        assertEquals("El precio debe ser un entero positivo", exception.getMessage());
    }
}