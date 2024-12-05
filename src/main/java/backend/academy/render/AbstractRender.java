package backend.academy.render;

import backend.academy.image.FractalImage;
import backend.academy.image.FractalImageUtils;
import backend.academy.models.Pixel;
import backend.academy.models.Point;
import backend.academy.models.Rect;
import backend.academy.services.RectUtils;
import backend.academy.transformation.AffineTransformation;
import backend.academy.transformation.AffineTransformationSet;
import backend.academy.transformation.Transformation;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.awt.Color;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Getter;

@Getter
@SuppressFBWarnings({"PREDICTABLE_RANDOM", "NOS_NON_OWNED_SYNCHRONIZATION"})
public abstract class AbstractRender {
    private List<Transformation> transformations;
    protected FractalImageUtils config;
    private final ConcurrentHashMap<Pixel, Boolean> lockMap = new ConcurrentHashMap<>();


    public AbstractRender(List<Transformation> transformations, FractalImageUtils config) {
        this.transformations = transformations;
        this.config = config;
    }

    public FractalImage render() {
        FractalImage fractalImage = FractalImage.init(config.xRes(), config.yRes());
        Rect rect = RectUtils.create(config);
        renderAllImage(fractalImage, rect);
        return fractalImage;
    }

    public abstract void renderAllImage(FractalImage fractalImage, Rect viewport);

    protected void renderImage(FractalImage fractalImage, Rect viewport) {

        AffineTransformationSet affineTransformationSet =
            new AffineTransformationSet(config.eqCount());
        for (int step = 0; step < config.iterations(); step++) {
            int i = ThreadLocalRandom.current().nextInt(transformations.size());
            Point point = viewport.randomPoint();

            double newX = point.x();
            double newY = point.y();

            // Выбираем случайное аффинное преобразование
            AffineTransformation affineTransformation = affineTransformationSet.getRandom(i);

            point = affineTransformation.apply(newX, newY);

            // Применяем случайное нелинейное преобразование
            Transformation nonlinearTransformation = transformations.get(i);

            point = nonlinearTransformation.apply(point);

            // Проверяем, попадает ли точка в область просмотра и начинается ли отрисовка
            if (step >= 0 && viewport.contains(point)) {
                // Преобразуем координаты точки в пиксельные координаты изображения

                point = config.convertCoordinatePixelImage(viewport, point);

                int x = (int) point.x();
                int y = (int) point.y();

                // Если точка попадает в границы изображения
                paintPixel(x, y, affineTransformation.color(), fractalImage);
            }

        }

    }

    public void paintPixel(int x, int y, Color color, FractalImage fractalImage) {
        Pixel pixel = fractalImage.getPixel(x, y);
        if (pixel != null) {
            synchronized (pixel) {
                pixel.paintPixelHitCount(color);
            }
        }
    }
}
