package backend.academy.transformation;

import backend.academy.Point;

public class Bent implements Transformation{
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();

        double newX;
        double newY;

        if (x >= 0 && y >= 0) {
            newX = x;
            newY = y;
        } else if (x < 0 && y >= 0) {
            newX = 2 * x;
            newY = y;
        } else if (x >= 0 && y < 0) {
            newX = x;
            newY = y / 2;
        } else {
            newX = 2 * x;
            newY = y / 2;
        }

        return new Point(newX, newY);
    }

}
