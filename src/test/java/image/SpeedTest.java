package image;

import backend.academy.image.FractalImageUtils;
import backend.academy.render.MultiThreadRender;
import backend.academy.render.OneThreadRender;
import backend.academy.transformation.Handkerchief;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class SpeedTest {
    private FractalImageUtils config;
    private double timeWorkOneThread;
    private double timeWorkMultiThread;

    @BeforeEach
    void setUp() {

        // параметры
        int width = 1920;
        int height = 1080;
        int eqCount = 10;
        int iterations = 10_000_000;
        int saturations = 5;
        config = new FractalImageUtils(width, height, eqCount, iterations, saturations);
    }

    @Test
    void speedTest() {
        OneThreadRender oneThreadRender = new OneThreadRender(List.of(new Handkerchief()), config);
        MultiThreadRender multiThreadRender = new MultiThreadRender(List.of(new Handkerchief()), config);

        double startTimeOneThread = System.currentTimeMillis();
        oneThreadRender.render();
        double endTimeOneThread = System.currentTimeMillis();
        timeWorkOneThread = (endTimeOneThread - startTimeOneThread) / 1000;

        double startTimeMultiThread = System.currentTimeMillis();
        multiThreadRender.render();
        double endTimeMultiThread = System.currentTimeMillis();

        timeWorkMultiThread = (endTimeMultiThread - startTimeMultiThread) / 1000;

        Assertions.assertTrue(timeWorkOneThread > timeWorkMultiThread);
    }

}
