package backend.academy.render;

import backend.academy.Rect;
import backend.academy.image.FractalImage;
import backend.academy.image.FractalImageUtils;
import backend.academy.transformation.Transformation;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;

public class MultiThreadRender extends AbstractRender {

    public MultiThreadRender(
        List<Transformation> transformations,
        FractalImageUtils config,
        int saturation
    ) {
        super(transformations, config, saturation);
    }

    @SneakyThrows
    @Override
    public void renderAllImage(FractalImage fractalImage, Rect viewport) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int i = 0; i < saturation; i++) {
            executorService.execute(() -> renderImage(fractalImage, viewport));
        }

        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
    }

}


