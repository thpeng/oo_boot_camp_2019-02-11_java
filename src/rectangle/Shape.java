/*
 * Copyright (c) 2019 by Fred George
 * May be used freely except for training; license required for training.
 */

package rectangle;

import compare.MyComparable;

import java.util.Objects;

public class Shape implements MyComparable<Shape> {

    private final double width;
    private final double height;

    private Shape(double width, double height) {
        if (width <= 0 || height <= 0)
            throw new IllegalArgumentException("Each dimension must be greater than zero");
        this.width = width;
        this.height = height;
    }

    public double area() {
        return width * height;
    }

    public double perimeter() {
        return 2 * (width + height);
    }

    public static Shape rectangle(double width, double length) {
        return new Shape(width, length);
    }

    public static Shape square(double size) {
        return new Shape(size, size);
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Shape shape = (Shape) o;
//        return Double.compare(shape.width, width) == 0 &&
//                Double.compare(shape.height, height) == 0;
//    }
//
//    @Override
//    public int hashCode() {
//
//        return Objects.hash(width, height);
//    }

    @Override
    public int compare(Shape other) {
        return this.area() - other.area() < 0 ? -1 : (this.area() - other.area() == 0 ? 0 : 1);
    }

    @Override
    public String toString() {
        return "Shape{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
