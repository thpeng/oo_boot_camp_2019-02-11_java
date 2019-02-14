/*
 * Copyright (c) 2019 by Fred George
 * May be used freely except for training; license required for training.
 */

package unit;

import compare.MyComparable;
import org.junit.jupiter.api.Test;
import rectangle.Shape;
import rectangle.ShapeSorter;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// Ensures Shape operates correctly
class RectangleTest {

    @Test
    void area() {
        assertEquals(24.0, Shape.rectangle(4.0, 6.0).area());
        assertEquals(16, Shape.square(4.0).area());
        //assertEquals(4 * Math.PI, Shape.circle(4.0).area());

    }

    @Test
    void perimeter() {
        assertEquals(20.0, Shape.rectangle(4.0, 6.0).perimeter());
    }

    @Test
    void parameterRanges() {
        assertThrows(IllegalArgumentException.class, () -> Shape.rectangle(0, 6));
        assertThrows(IllegalArgumentException.class, () -> Shape.rectangle(4, 0));
    }

    @Test
    void sortAreas() {
        List<Shape> shapes = Arrays.asList(
                Shape.square(3),
                Shape.rectangle(1, 2),
                Shape.rectangle(2, 4),
                Shape.square(2));
        Shape maxShape = ShapeSorter.max(shapes);
        /*assertIterableEquals(Arrays.asList(
                Shape.rectangle(1, 2),
                Shape.square(2),
                Shape.rectangle(2, 4),
                Shape.square(3)), sorted);
    }*/
        assertEquals(Shape.square(3).area(), maxShape.area());
    }


}
