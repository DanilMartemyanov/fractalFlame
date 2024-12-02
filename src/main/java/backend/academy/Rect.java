package backend.academy;

public record Rect(double x, double y, double width, double height) {

    public boolean contains(Point p) {
        return p.x() >= x && p.x() <= x + width && p.y() >= y && p.y() <= y + height;
    }

    public static Point rotatePoint(Rect world, Point p, double theta) {
        double centerX = world.x() + world.width() / 2;
        double centerY = world.y() + world.height() / 2;

        double dx = p.x() - centerX;
        double dy = p.y() - centerY;

        double rotatedX = centerX + dx * Math.cos(theta) - dy * Math.sin(theta);
        double rotatedY = centerY + dx * Math.sin(theta) + dy * Math.cos(theta);

        return new Point(rotatedX, rotatedY);
    }
}
