package backend.academy;

import backend.academy.image.FractalImage;
import backend.academy.image.FractalImageUtils;
import backend.academy.image.GammaCorrection;
import backend.academy.logic.CommandLineArgs;
import backend.academy.render.MultiThreadRender;
import backend.academy.render.OneThreadRender;
import backend.academy.services.FileManager;
import backend.academy.transformation.Transformation;
import backend.academy.transformation.TransformationSet;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {

    public static void main(String[] args) {
        float startTimeOneThread = System.currentTimeMillis();
        float endTimeOneThread = System.currentTimeMillis();
        float startTimeMultiThread = System.currentTimeMillis();
        float endTimeMultiThread = System.currentTimeMillis();
        TransformationSet transformationSet = new TransformationSet();
        CommandLineArgs commandLineArgs = CommandLineArgs.parseArguments(args);
        GammaCorrection gammaCorrectionOneThread = new GammaCorrection();
        GammaCorrection gammaCorrectionMultiThreads = new GammaCorrection();
        // параметры
        int width = commandLineArgs.width();
        int height = commandLineArgs.height();
        int eqCount = commandLineArgs.eqCount();
        int iterations = commandLineArgs.iterations();
        int saturations = commandLineArgs.saturations();
        List<Transformation> transformations = transformationSet.getTransformations(commandLineArgs.transformations());
        String fileFormat = commandLineArgs.fileFormat();
        // Создаем конфигурацию
        FractalImageUtils config = new FractalImageUtils(width, height, eqCount, iterations, saturations);
        // Создание однопоточого и многопоточного режима
        OneThreadRender oneThreadRender = new OneThreadRender(transformations, config);
        MultiThreadRender multiThreadRender = new MultiThreadRender(transformations, config);
        //
        FractalImage oneThreadImage = oneThreadRender.render();
        FractalImage multiThreadImage = multiThreadRender.render();
        //гамма коррекция
        gammaCorrectionOneThread.process(oneThreadImage);
        gammaCorrectionMultiThreads.process(multiThreadImage);


        FileManager.save(oneThreadImage, "oneThreadImage." + fileFormat);
        FileManager.save(multiThreadImage, "multiThreadImage." + fileFormat);



    }
}


