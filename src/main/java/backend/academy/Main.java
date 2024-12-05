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
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    private final PrintStream printStream = new PrintStream(System.out, true, StandardCharsets.UTF_8);

    public static void main(String[] args) {
        double timeWorkOneThread;
        double timeWorkMultiThread;
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
        //Замер времени

        double startTimeOneThread = System.currentTimeMillis();
        FractalImage oneThreadImage = oneThreadRender.render();
        double endTimeOneThread = System.currentTimeMillis();
        timeWorkOneThread = (endTimeOneThread - startTimeOneThread)/1000;

        double startTimeMultiThread = System.currentTimeMillis();
        FractalImage multiThreadImage = multiThreadRender.render();
        double endTimeMultiThread = System.currentTimeMillis();

        timeWorkMultiThread = (endTimeMultiThread - startTimeMultiThread)/1000;


        //гамма коррекция
        gammaCorrectionOneThread.process(oneThreadImage);
        gammaCorrectionMultiThreads.process(multiThreadImage);


        FileManager.save(oneThreadImage, "oneThreadImage." + fileFormat);
        FileManager.save(multiThreadImage, "multiThreadImage." + fileFormat);

        printStream.println("Конфигурация системы:");
        printStream.println(config);

        printStream.println("Время работы однопоточного режима: ");
        printStream.println(timeWorkOneThread + " c");

        printStream.println("Время работы многопоточного режима: ");
        printStream.println(timeWorkMultiThread + " c");




    }
}


