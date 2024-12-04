package backend.academy.render;

import backend.academy.Pixel;
import backend.academy.Point;
import backend.academy.Rect;
import backend.academy.image.FractalImage;
import backend.academy.image.FractalImageUtils;
import backend.academy.services.RectUtils;
import backend.academy.transformation.AffineTransformation;
import backend.academy.transformation.Transformation;
import lombok.SneakyThrows;
import java.awt.Color;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiThreadRender extends AbstractRender {

    public MultiThreadRender(
        List<Transformation> transformations,
        FractalImageUtils config
    ) {
        super(transformations, config);
    }

    @SneakyThrows
    @Override
    public void renderAllImage(FractalImage fractalImage, Rect viewport) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        int intPart = (int) (fractalImage.height()*fractalImage.width()/5);

        // Разбиваем задачу на несколько подзадач для каждого потока
        for (int threadIndex = 0; threadIndex < 5; threadIndex++) {
            executorService.execute(() -> renderImage(fractalImage, viewport));
        }

        // Закрываем ExecutorService после завершения всех задач
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
    }

}


