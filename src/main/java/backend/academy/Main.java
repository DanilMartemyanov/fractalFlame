package backend.academy;

import backend.academy.image.FractalImage;
import backend.academy.image.FractalImageConfig;
import backend.academy.image.GammaCorrection;

import backend.academy.services.FileManager;

import lombok.experimental.UtilityClass;
import java.io.IOException;

@UtilityClass
public class Main {

    public static void main(String[] args) {
        int width = 760;
        int height = 680;
        int eqCount = 6;
        int iterations = 1_00_000;
        FractalImageConfig parameters = new FractalImageConfig(width, height, eqCount, iterations);

        Rect viewport = new Rect(-1.777, -1, 3.554, 2);

        FractalRender render = new FractalRender(parameters, viewport);
        long startTime = System.nanoTime();
        render.render(1_00_000);
        long endTime = System.nanoTime();

        System.out.println(endTime - startTime / (6 * 1000000000));

        GammaCorrection gammaCorrection = new GammaCorrection();
        FractalImage image = render.fractalImage();


        System.out.println("Fractal rendered with " + parameters.xRes() + "x" + parameters.yRes() + " resolution.");

        try {
            gammaCorrection.process(image);
            FileManager.saveFractalImageAsJPEG(image, "test.jpg");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


