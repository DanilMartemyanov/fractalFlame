package backend.academy;

import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

public record Rect(double x, double y, double width, double height) {


    public boolean contains(Point p) {
        return p.x() >= x && p.x() <= x + width && p.y() >= y && p.y() <= y + height;
    }

    public Point randomPoint(SecureRandom random) {
        double newX = random.nextDouble(x, x + width);
        double newY = random.nextDouble(y, y + height);
        return new Point(newX, newY);

    }
}
