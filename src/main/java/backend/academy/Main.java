package backend.academy;

import backend.academy.image.FractalImage;
import backend.academy.image.FractalImageConfig;
import backend.academy.services.FileManager;
import backend.academy.transformation.NonLinearTransformationSet;
import lombok.experimental.UtilityClass;
import java.io.IOException;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        int width = 1920;
        int height = 1080;
        int eqCount = 4;
        int iterations = 5000;
        FractalImageConfig parameters = new FractalImageConfig(width, height, eqCount, iterations);

        Rect viewport = new Rect(-1.777, -1, 3.554, 2);

        NonLinearTransformationSet nonLinearTransformationSet = new NonLinearTransformationSet();

        FractalRender render = new FractalRender(parameters, viewport);
        long startTime = System.nanoTime();
        render.render(10000);
        long endTime = System.nanoTime();

        System.out.println(endTime - startTime/(6*1000000000));

        FractalImage image = render.fractalImage();

        System.out.println("Fractal rendered with " + parameters.xRes() + "x" + parameters.yRes() + " resolution.");

        try {
            FileManager.saveFractalImageAsJPEG(image, "test.jpg");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
