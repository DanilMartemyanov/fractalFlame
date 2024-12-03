package backend.academy;

import backend.academy.image.FractalImage;
import backend.academy.image.FractalImageUtils;
import backend.academy.image.GammaCorrection;

import backend.academy.render.MultiThreadRender;
import backend.academy.render.OneThreadRender;
import backend.academy.services.FileManager;

import backend.academy.transformation.Handkerchief;
import backend.academy.transformation.Heart;
import backend.academy.transformation.TransformationSet;
import lombok.experimental.UtilityClass;
import java.io.IOException;
import java.util.List;

@UtilityClass
public class Main {

    public static void main(String[] args) {
        int width = 1920;
        int height = 1080;
        int eqCount = 6;
        int iterations = 10_000_000;
        FractalImageUtils parameters = new FractalImageUtils(width, height, eqCount, iterations);
        Handkerchief handkerchief = new Handkerchief();

        GammaCorrection gammaCorrection = new GammaCorrection();
        Heart heart = new Heart();
        OneThreadRender render = new OneThreadRender(List.of(handkerchief, heart ), parameters);
        TransformationSet transformationSet = new TransformationSet();
        MultiThreadRender multiThreadRender = new MultiThreadRender(transformationSet.transformations(), parameters);
        long startTime = System.currentTimeMillis();
        FractalImage image = render.render(760, 680);
        long endTime = System.currentTimeMillis();

        System.out.println((endTime - startTime)/1000);

        System.out.println("Fractal rendered with " + parameters.xRes() + "x" + parameters.yRes() + " resolution.");


        gammaCorrection.process(image);
        try {
            FileManager.saveFractalImageAsJPEG(image, "test.jpg");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


