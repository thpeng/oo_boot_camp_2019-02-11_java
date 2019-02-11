package measurements;

import java.util.Objects;

public class Quart {
    private int numberOfQuarts;

    public Quart(int numberOfQuarts) {
        this.numberOfQuarts = numberOfQuarts;
    }

    @Override
    public String toString() {
        return "Quart{" +
                "numberOfQuarts=" + numberOfQuarts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quart quart = (Quart) o;
        return numberOfQuarts == quart.numberOfQuarts;
    }

    @Override
    public int hashCode() {

        return Objects.hash(numberOfQuarts);
    }
}
