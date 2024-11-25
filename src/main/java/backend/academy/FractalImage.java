package backend.academy;

public record FractalImage(Pixel[] data, int width, int height) {

    public static FractalImage init(int width, int height) {
        Pixel[] pixels = new Pixel[width * height];
        for (int i = 0; i < width * height; i++) {
            pixels[i] = new Pixel(0, 0, 0, 0);
        }
        return new FractalImage(pixels, width, height);
    }

    public boolean containsPoint(Point point) {
        return point.x() >= 0 && point.x() < width && point.y() >= 0 && point.y() < height;
    }



}
