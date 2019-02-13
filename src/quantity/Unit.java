package quantity;

public class Unit {

    public static final Unit INCH = new Unit();

    public static final Unit FOOT = new Unit(12, INCH);
    public static final Unit YARD = new Unit(3, FOOT);
    public static final Unit CHAIN = new Unit(22, YARD);
    public static final Unit FURLONG = new Unit(10, CHAIN);
    public static final Unit MILE = new Unit(8, FURLONG);

    public static final Unit TEASPOON = new Unit();
    public static final Unit TABLESPOON = new Unit(3, TEASPOON);
    public static final Unit OUNCE = new Unit(2, TABLESPOON);
    public static final Unit CUP = new Unit(8, OUNCE);
    public static final Unit PINT = new Unit(2, CUP);
    public static final Unit QUART = new Unit(2, PINT);
    public static final Unit GALLON = new Unit(4, QUART);

    public static final double ABSOLUTE_ZERO_CELSIUS = -273.15;
    public static final Unit CELSIUS = new Unit(){
        @Override
        public Quantity s(double amount) {
            if(amount < ABSOLUTE_ZERO_CELSIUS){
                throw new IllegalArgumentException(String.format("%d is less than absolute zero %d, go find another universe", amount, ABSOLUTE_ZERO_CELSIUS));
            }
            return super.s(amount);
        }

    };
    public static final double ABSOLUTE_ZERO_FAHRENHEIT = -459.67;

    public static final Unit FAHRENHEIT = new Unit(5/9.0, 32, CELSIUS){
        @Override
        public Quantity s(double amount) {
            if(amount < ABSOLUTE_ZERO_FAHRENHEIT){
                throw new IllegalArgumentException(String.format("%d is less than absolute zero %d, go find another universe", amount, ABSOLUTE_ZERO_CELSIUS));
            }
            return super.s(amount);
        }
    };

    final Object type;
    private final double baseUnitRatio;
    private final double offset;

    private Unit(){
        this.type = this;
        baseUnitRatio = 1.0;
        offset = 0.0;
    }

    protected Unit(double relativeRatio, Unit relativeUnit) {
        this.type = relativeUnit.type;
        baseUnitRatio = relativeRatio * relativeUnit.baseUnitRatio;
        offset = 0.0;
    }

    protected Unit(double ratio, double offset, Unit relativeUnit) {
        this.baseUnitRatio = ratio;
        this.offset = offset;
        this.type = relativeUnit.type;
    }

    public Quantity s(double amount) {
        return new Quantity(amount, this);
    }

    double convertedAmount(double otherAmount, Unit other) {
        if(this.type != other.type){
            throw new IllegalArgumentException("Incompatible types: " + this.type + ", " + other.type);
        }
        return (otherAmount - other.offset) * other.baseUnitRatio / this.baseUnitRatio + this.offset;
    }

    int hashCode(double amount) {
        return Double.hashCode(amount * baseUnitRatio);
    }

    private static class IntervalUnit extends Unit{
        private double absoluteZero;

        public IntervalUnit(double absoluteZero) {
            super();
            this.absoluteZero = absoluteZero;
        }

        public IntervalUnit(double relativeRatio, Unit relativeUnit) {
            super(relativeRatio, relativeUnit);
        }

        public IntervalUnit(double ratio, double offset, Unit relativeUnit) {
            super(ratio, offset, relativeUnit);
        }
    }
}
