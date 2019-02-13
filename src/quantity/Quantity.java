/*
 * Copyright (c) 2019 by Fred George
 * May be used freely except for training; license required for training.
 */

package quantity;

// Understands a particular measurement
public class Quantity {
    private static final double EPSILON_ERROR = 0.0000001;

    private final double amount;
    private final Unit unit;

    Quantity(double amount, Unit unit) {
        this.amount = amount;
        this.unit = unit;
    }

    @Override
    public boolean equals(Object other) {
        return this == other ||
                other != null && other.getClass() == Quantity.class && this.equals((Quantity) other);
    }

    private boolean equals(Quantity other) {
        return this.unit.type == other.unit.type && Math.abs(this.amount - convertedAmount(other)) < EPSILON_ERROR;
    }

    private double convertedAmount(Quantity other) {
        return this.unit.convertedAmount(other.amount, other.unit);
    }

    public Quantity add(Quantity other) {
        return new Quantity(this.amount + this.unit.convertedAmount(other.amount, other.unit), this.unit);
    }

    @Override
    public int hashCode() {
        return unit.hashCode(amount);
    }

    @Override
    public String toString() {
        return "Quantity{" +
                "amount=" + amount +
                ", unit=" + unit +
                '}';
    }

    public Quantity subtract(Quantity other) {
        return new Quantity(this.amount - this.unit.convertedAmount(other.amount, other.unit), this.unit);
    }
}
