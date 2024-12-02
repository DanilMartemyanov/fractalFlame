package backend.academy;

import backend.academy.image.FractalImage;
import backend.academy.image.FractalImageUtils;
import backend.academy.image.GammaCorrection;

import backend.academy.services.FileManager;

import backend.academy.transformation.Handkerchief;
import lombok.experimental.UtilityClass;
import java.io.IOException;
import java.util.List;

@UtilityClass
public class Main {

    public static void main(String[] args) {
        int width = 760;
        int height = 680;
        int eqCount = 6;
        int iterations = 100000;
        FractalImageUtils parameters = new FractalImageUtils(width, height, eqCount, iterations);
        Handkerchief handkerchief = new Handkerchief();

        Rect viewport = new Rect(-1.777, -1, 3.554, 2);

        FractalRender render = new FractalRender(parameters, viewport);
        long startTime = System.nanoTime();
        render.render(iterations, List.of(handkerchief));
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


