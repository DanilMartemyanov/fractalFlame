package backend.academy.models;


import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.concurrent.ThreadLocalRandom;

@SuppressFBWarnings("PREDICTABLE_RANDOM")
public record Rect(double x, double y, double width, double height) {


    public boolean contains(Point p) {
        return p.x() >= x && p.x() <= x + width && p.y() >= y && p.y() <= y + height;
    }

    public Point randomPoint() {
        double newX = x + ThreadLocalRandom.current().nextDouble() * width;
        double newY = y + ThreadLocalRandom.current().nextDouble() * height;
        return new Point(newX, newY);

    }

    @Override
    public String toString() {
        return "x: " + x + ", y: " + y + ", width: " + width + ", height: " + height;
    }
}
