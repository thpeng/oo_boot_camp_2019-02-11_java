package temperature;

import java.util.Objects;

public class Temperature {

    private static final double ABSOLUTE_ZERO_FAHRENHEIT = -459.67;

    public static final double EPSILON_ERROR = 0.000000000001;

    private double fahrenheit;

    private Temperature(double fahrenheit) {
        if(fahrenheit < ABSOLUTE_ZERO_FAHRENHEIT){
            throw new IllegalArgumentException(String.format("%d is less than absolute zero %d, go find another universe", fahrenheit, ABSOLUTE_ZERO_FAHRENHEIT));
        }
        this.fahrenheit = fahrenheit;
    }

    public static Temperature fahrenheit(double fahrenheit) {
        return new Temperature(fahrenheit);
    }

    public static Temperature celsius(double celsius) {
        return new Temperature(convertFromCelsiusToFahrenheit(celsius));
    }

    private static double convertFromCelsiusToFahrenheit(double celsius){
        return celsius * 9 / 5 + 32;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Temperature that = (Temperature) o;
        return Double.compare(that.fahrenheit, fahrenheit) < EPSILON_ERROR;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fahrenheit);
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "fahrenheit=" + fahrenheit +
                '}';
    }

    public Temperature add(Temperature other) {
        return new Temperature(this.fahrenheit + other.fahrenheit);
    }

    public Temperature subtract(Temperature other) {
        return new Temperature(this.fahrenheit - other.fahrenheit);
    }
}
