package backend.academy.image;

import backend.academy.Pixel;

public record FractalImage(Pixel[] data, int width, int height) {

    public static FractalImage init(int width, int height) {
        Pixel[] pixels = new Pixel[width * height];
        for (int i = 0; i < width * height; i++) {
            pixels[i] = new Pixel(0, 0, 0);
        }
        return new FractalImage(pixels, width, height);
    }

    public boolean contains(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public Pixel getPixel(int x, int y) {
        if(contains(x, y)) {
            return data[y * width + x];
        }
        return null;
    }



}
