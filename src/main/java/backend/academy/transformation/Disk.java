package backend.academy.transformation;

import backend.academy.Point;

public class Disk implements Transformation {
    @Override
    public Point apply(Point point) {
        double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        double theta = Math.atan2(point.y(), point.x());
        return new Point(theta / Math.PI * Math.sin(r * Math.PI), theta / Math.PI * Math.cos(r * Math.PI));
    }
}
