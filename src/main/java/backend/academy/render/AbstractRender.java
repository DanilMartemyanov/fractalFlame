package backend.academy.render;

import backend.academy.Pixel;
import backend.academy.Point;
import backend.academy.Rect;
import backend.academy.image.FractalImage;
import backend.academy.image.FractalImageUtils;
import backend.academy.services.Checker;
import backend.academy.services.RectUtils;
import backend.academy.transformation.AffineTransformation;
import backend.academy.transformation.AffineTransformationSet;
import backend.academy.transformation.Transformation;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class AbstractRender {
    private List<Transformation> transformations;
    protected FractalImageUtils config;



    public AbstractRender(List<Transformation> transformations, FractalImageUtils config) {
        this.transformations = transformations;
        this.config = config;

    }

    public FractalImage render(int width, int height) {
        FractalImage fractalImage = FractalImage.init(width, height);
        Rect rect = RectUtils.create(config);
        renderAllImage(fractalImage, rect);
        return fractalImage;
    }

    public abstract void renderAllImage(FractalImage fractalImage, Rect viewport);

    protected void renderImage(FractalImage fractalImage, Rect viewport) {
        System.out.println("Поток пошел");

        for (int num = 0; num < 5; num++) {
            int i = ThreadLocalRandom.current().nextInt(transformations.size());
            AffineTransformationSet affineTransformationSet =
                new AffineTransformationSet(new ArrayList<>(), config.eqCount());
            for (int step = -20; step < config.iterations(); step++) {

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
                if (step >= 0 && Checker.boundCheck(point.x(), viewport.x(), viewport.x() + viewport.width())
                    && Checker.boundCheck(point.y(), viewport.y(), viewport.y() + viewport.height())) {
                    // Преобразуем координаты точки в пиксельные координаты изображения

                    point = config.convertCoordinatePixelImage(viewport, point);

                    int x = (int) point.x();
                    int y = (int) point.y();

                    // Если точка попадает в границы изображения
                    paintPixel(x, y, affineTransformation.color(), fractalImage);
                }
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
