/*
 * Copyright (c) 2019 by Fred George
 * May be used freely except for training; license required for training.
 */

package unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import temperature.Temperature;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static quantity.Unit.CELSIUS;
import static quantity.Unit.CHAIN;
import static quantity.Unit.CUP;
import static quantity.Unit.FAHRENHEIT;
import static quantity.Unit.GALLON;
import static quantity.Unit.MILE;
import static quantity.Unit.OUNCE;
import static quantity.Unit.TABLESPOON;
import static quantity.Unit.TEASPOON;

// Ensures Quantity operates correctly
class QuantityTest {

    @Test void equalityOfLikeUnits() {
        assertEquals(TABLESPOON.s(4), TABLESPOON.s(4));
        assertNotEquals(TABLESPOON.s(4), TABLESPOON.s(6));
        assertNotEquals(TABLESPOON.s(4), new Object());
        assertNotEquals(TABLESPOON.s(4), null);
    }

    @Test void equalityOfDifferentUnits() {
        assertEquals(CELSIUS.s(-7.777777777777777), FAHRENHEIT.s(18));
        assertEquals(TABLESPOON.s(4), OUNCE.s(2));
        assertEquals(CUP.s(1/4.0), TABLESPOON.s(4));
        assertEquals(GALLON.s(1), TEASPOON.s(768));
        assertNotEquals(TABLESPOON.s(4), TEASPOON.s(4));
        assertEquals(CELSIUS.s(10), FAHRENHEIT.s(50));
        assertEquals(CELSIUS.s(0), FAHRENHEIT.s(32));
    }

    @Test
    void add() {
        assertThrows(IllegalArgumentException.class, () -> MILE.s(0).add(CELSIUS.s(0)));
        assertThrows(IllegalArgumentException.class, () -> CUP.s(0).add(FAHRENHEIT.s(0)));
        assertThrows(IllegalArgumentException.class, () ->  FAHRENHEIT.s(0).add(CELSIUS.s(0)));
        assertThrows(IllegalArgumentException.class, () -> CELSIUS.s(0).add(FAHRENHEIT.s(18)));
    }

    @Test void setOperations() {
        assertTrue(new HashSet<>(Collections.singletonList(TABLESPOON.s(4))).contains(TABLESPOON.s(4)));
        assertTrue(new HashSet<>(Collections.singletonList(TABLESPOON.s(4))).contains(OUNCE.s(2)));
        assertEquals(1, new HashSet<>(Arrays.asList(TABLESPOON.s(4), TABLESPOON.s(4))).size());
        assertEquals(1, new HashSet<>(Arrays.asList(TABLESPOON.s(4), OUNCE.s(2))).size());
    }

    @Test void hash() {
        assertEquals(TABLESPOON.s(4).hashCode(), TABLESPOON.s(4).hashCode());
        assertEquals(TABLESPOON.s(4).hashCode(), CUP.s(1/4.0).hashCode());
    }

    @Test
    void absoluteNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> CELSIUS.s(-273.16));
        Assertions.assertThrows(IllegalArgumentException.class, () -> FAHRENHEIT.s(-500));
    }

    @Test
    void subtract() {
        //Assertions.assertEquals(FAHRENHEIT.s(-1), FAHRENHEIT.s(1).subtract(FAHRENHEIT.s(2)));
        //Assertions.assertEquals(FAHRENHEIT.s(100), FAHRENHEIT.s(200).subtract(FAHRENHEIT.s(100)));
        //Assertions.assertEquals(CELSIUS.s(-1), CELSIUS.s(1).subtract(CELSIUS.s(2)));
        Assertions.assertEquals(CELSIUS.s(0), CELSIUS.s(10).subtract(FAHRENHEIT.s(18)));
        Assertions.assertThrows(IllegalArgumentException.class, () -> CELSIUS.s(-273.16).subtract(CELSIUS.s(1)));
    }

}
