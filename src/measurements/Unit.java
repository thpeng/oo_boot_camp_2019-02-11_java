package measurements;

public class Unit {


    public static final Unit TEASPOON = new Unit();
    public static final Unit TABLESPOON = new Unit(3, TEASPOON);
    public static final Unit OUNCE = new Unit(2, TABLESPOON);
    public static final Unit CUP = new Unit(8, OUNCE);
    public static final Unit PINT = new Unit(2, CUP);
    public static final Unit QUART = new Unit(2, PINT);
    public static final Unit GALLON = new Unit(4, QUART);


    private final int nextUnitMultiplicator;
    private final Unit nextUnit;

    Unit(int nextUnitMultiplicator, Unit nextUnit) {
        this.nextUnitMultiplicator = nextUnitMultiplicator;
        this.nextUnit = nextUnit;
    }

    public Unit() {
        this(1, null);
    }

    public double convertTo(double amount, Unit targetUnit) {
        return amount * internalMultiplicatorFor(targetUnit, this);
        //
        // amount / internalMultiplicator(targetUnit, this)
    }


    private static int internalMultiplicatorFor(Unit targetUnit, Unit sourceUnit) {
        if (targetUnit.equals(sourceUnit)) {
            return targetUnit.nextUnitMultiplicator;
        }
        return sourceUnit.nextUnitMultiplicator * internalMultiplicatorFor(targetUnit, sourceUnit.nextUnit);
    }

}
