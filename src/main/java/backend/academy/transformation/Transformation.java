package backend.academy.transformation;

import backend.academy.Point;

import java.util.function.Function;

@FunctionalInterface
public interface Transformation extends Function<Point, Point> {

}
