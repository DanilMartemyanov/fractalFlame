package backend.academy;

import backend.academy.image.FractalImage;
import backend.academy.image.FractalImageUtils;
import backend.academy.image.GammaCorrection;
import backend.academy.render.MultiThreadRender;
import backend.academy.render.OneThreadRender;
import backend.academy.services.FileManager;
import backend.academy.transformation.Bent;
import backend.academy.transformation.Disk;
import backend.academy.transformation.Handkerchief;
import backend.academy.transformation.Heart;
import backend.academy.transformation.Polar;
import backend.academy.transformation.Sinusoidal;
import backend.academy.transformation.Spherical;
import backend.academy.transformation.TransformationSet;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {

    public static void main(String[] args) {
        int width = 1920;
        int height = 1080;
        int eqCount = 3;
        int iterations = 1_000_000;
        FractalImageUtils parameters = new FractalImageUtils(width, height, eqCount, iterations, 100);
        Handkerchief handkerchief = new Handkerchief();

        GammaCorrection gammaCorrection = new GammaCorrection();
        Heart heart = new Heart();
        Disk disk = new Disk();
        Spherical spherical = new Spherical();
        Sinusoidal sinusoidal = new Sinusoidal();
        Polar polar = new Polar();
        OneThreadRender render = new OneThreadRender(List.of(handkerchief), parameters);
        TransformationSet transformationSet = new TransformationSet();
        Bent bent = new Bent();
        MultiThreadRender multiThreadRender = new MultiThreadRender(List.of(heart), parameters);
        long startTime = System.currentTimeMillis();
        FractalImage image = multiThreadRender.render(1920, 1080);
        long endTime = System.currentTimeMillis();

        System.out.println((endTime - startTime)/1000);

        System.out.println("Fractal rendered with " + parameters.xRes() + "x" + parameters.yRes() + " resolution.");


        gammaCorrection.process(image);
        FileManager fileManager = new FileManager();
        fileManager.save(image,"test.jpg");
    }
}


