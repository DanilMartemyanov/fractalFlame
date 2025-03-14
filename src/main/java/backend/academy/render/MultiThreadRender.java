package backend.academy.render;


import backend.academy.image.FractalImage;
import backend.academy.image.FractalImageUtils;
import backend.academy.models.Rect;
import backend.academy.transformation.Transformation;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

@Log4j2
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
        log.info("Количество потоков: " +  Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < config.saturations(); i++) {
            executorService.execute(() -> renderImage(fractalImage, viewport));
        }

        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
    }

}


