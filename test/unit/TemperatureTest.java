package unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import temperature.Temperature;

public class TemperatureTest {

    @Test
    void compareFahrenheitToCelsius() {
        Assertions.assertEquals(Temperature.fahrenheit(32d), Temperature.celsius(0d));
        Assertions.assertEquals(Temperature.fahrenheit(50), Temperature.celsius(10));
        Assertions.assertEquals(Temperature.fahrenheit(212), Temperature.celsius(100));
        Assertions.assertEquals(Temperature.fahrenheit(-40), Temperature.celsius(-40));
    }

    @Test
    void absoluteNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Temperature.celsius(-273.16));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Temperature.fahrenheit(-500));
    }

    @Test
    void add() {
        Assertions.assertEquals(Temperature.fahrenheit(100), Temperature.fahrenheit(50).add(Temperature.celsius(10)));
        Assertions.assertEquals(Temperature.fahrenheit(32), Temperature.fahrenheit(0).add(Temperature.celsius(0)));
        Assertions.assertEquals(Temperature.celsius(0), Temperature.fahrenheit(0).add(Temperature.celsius(0)));
    }

    @Test
    void subtract() {
        Assertions.assertEquals(Temperature.fahrenheit(-1), Temperature.fahrenheit(1).subtract(Temperature.fahrenheit(2)));
        Assertions.assertEquals(Temperature.fahrenheit(100), Temperature.fahrenheit(200).subtract(Temperature.fahrenheit(100)));
        Assertions.assertEquals(Temperature.celsius(-1), Temperature.celsius(1).subtract(Temperature.celsius(2)));
        Assertions.assertEquals(Temperature.celsius(0), Temperature.celsius(10).subtract(Temperature.fahrenheit(18)));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Temperature.celsius(-273.16).subtract(Temperature.celsius(1)));
    }

}
