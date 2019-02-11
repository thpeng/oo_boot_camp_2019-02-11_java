package unit;

import measurements.Unit;
import measurements.Quantity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MeasurementTests {

    @Test
    void testEquals(){
        assertEquals(new Quantity(4, Unit.QUART), new Quantity(1, Unit.GALLON));
        assertEquals(new Quantity(2, Unit.CUP), new Quantity(1, Unit.PINT));
        assertEquals(new Quantity(8, Unit.PINT), new Quantity(1, Unit.GALLON));
        assertEquals(new Quantity(16, Unit.CUP), new Quantity(1, Unit.GALLON));
        assertEquals(new Quantity(128, Unit.OUNCE), new Quantity(1, Unit.GALLON));
    }

    @Test
    void add() {
        assertEquals(new Quantity(2, Unit.GALLON),
                new Quantity(128, Unit.OUNCE)
                        .add(new Quantity(1, Unit.GALLON)));
    }
}
