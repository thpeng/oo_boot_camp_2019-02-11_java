package unit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static quantity.Unit.CHAIN;
import static quantity.Unit.FOOT;
import static quantity.Unit.FURLONG;
import static quantity.Unit.INCH;
import static quantity.Unit.MILE;
import static quantity.Unit.OUNCE;
import static quantity.Unit.TABLESPOON;
import static quantity.Unit.TEASPOON;
import static quantity.Unit.YARD;


public class DistanceTest {

    @Test
    void equalityOfLikeUnits() {
        assertEquals(INCH.s(12), FOOT.s(1));
        assertEquals(YARD.s(1), INCH.s(36));
        assertEquals(MILE.s(1), CHAIN.s(80));
        assertNotEquals(MILE.s(4), new Object());
        assertNotEquals(FURLONG.s(4), null);
    }

    @Test void equalityOfDifferentUnits() {
        assertNotEquals(CHAIN.s(2), OUNCE.s(2));
        assertNotEquals(INCH.s(2), TEASPOON.s(2));
    }

    @Test
    void testAddition() {
        assertEquals(INCH.s(5), INCH.s(2).add(INCH.s(3)));
        assertNotEquals(INCH.s(5), TEASPOON.s(2).add(TEASPOON.s(3)));
    }

}
