package measurements;

import java.util.Objects;

public class Gallon {

    private int numberOfGallons;

    public Gallon(int numberOfGallons) {
        this.numberOfGallons = numberOfGallons;
    }

    public Quart toQuarts() {
        return new Quart(4);
    }

    @Override
    public String toString() {
        return "Gallon{" +
                "numberOfGallons=" + numberOfGallons +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gallon gallon = (Gallon) o;
        return numberOfGallons == gallon.numberOfGallons;
    }

    @Override
    public int hashCode() {

        return Objects.hash(numberOfGallons);
    }
}
