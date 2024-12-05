package backend.academy.image;

import backend.academy.Pixel;
import backend.academy.services.Constant;

public class GammaCorrection implements ImageProcessor {
    @Override
    public void process(FractalImage image) {
        double max = 0.0;
        double gamma = Constant.LARGER_UPPER_BOUND;
        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                if (image.getPixel(x, y).hitCount() != 0) {
                    image.getPixel(x, y).normal(Math.log10(image.getPixel(x, y).hitCount()));
                    if (image.getPixel(x, y).normal() > max) {
                        max = image.getPixel(x, y).normal();
                    }
                }
            }
        }

        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                Pixel pixel = image.getPixel(x, y);
                pixel.normal(image.getPixel(x, y).normal() / max);
                pixel.red((int) (pixel.red() * Math.pow(pixel.normal(), (1.0 / gamma))));
                pixel.green((int) (pixel.green() * Math.pow(pixel.normal(), (1.0 / gamma))));
                pixel.blue((int) (pixel.blue() * Math.pow(pixel.normal(), (1.0 / gamma))));
            }
        }

    }
}
