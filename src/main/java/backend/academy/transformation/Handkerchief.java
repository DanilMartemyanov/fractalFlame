package backend.academy.transformation;

import backend.academy.models.Point;

public class Handkerchief implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r = Math.sqrt(x * x + y * y);
        double theta = Math.atan2(y, x);

        double newX = r * Math.sin(theta + r);
        double newY = r * Math.cos(theta - r);

        return new Point(newX, newY);
    }
}
