package backend.academy;

import backend.academy.image.FractalImage;
import backend.academy.image.FractalImageUtils;
import backend.academy.services.Checker;
import backend.academy.transformation.AffineTransformation;
import backend.academy.transformation.AffineTransformationSet;
import backend.academy.transformation.CoefficientGenerator;
import backend.academy.transformation.CoefficientGeneratorImpl;
import backend.academy.transformation.Transformation;
import backend.academy.transformation.NonLinearTransformationSet;
import lombok.Getter;
import java.security.SecureRandom;
import java.util.List;

@Getter
public class FractalRender {
    private FractalImageUtils config;
    private NonLinearTransformationSet nonLinearTransformationSet = new NonLinearTransformationSet();
    private AffineTransformationSet affineTransformationSet;
    private CoefficientGenerator coefficientGenerator = new CoefficientGeneratorImpl();
    private Rect viewport;
    private FractalImage fractalImage;
    private SecureRandom random = new SecureRandom();

    public FractalRender(FractalImageUtils config, Rect viewport) {
        this.config = config;
        this.viewport = viewport;
        this.fractalImage = FractalImage.init(config.xRes(), config.yRes());
    }

    public void render(int iterations, List<Transformation> transformations) {
        SecureRandom random = new SecureRandom();
        // Генерируем аффинные преобразования
        affineTransformationSet = new AffineTransformationSet(coefficientGenerator);
        affineTransformationSet.generateEq(config.eqCount());

        for (int num = 0; num < iterations; num++) {

            int i = random.nextInt(transformations.size());

            double xmin = viewport.x();
            double xmax = viewport.x() + viewport.width();
            double ymin = viewport.y();
            double ymax = viewport.y() + viewport.height();

            Point point  = viewport.randomPoint(random);

            double newX = point.x();
            double newY = point.y();

            for (int step = -20; step < config.iterations(); step++) {

                // Выбираем случайное аффинное преобразование
                AffineTransformation affineTransformation = affineTransformationSet.getRandom(i);

                point = affineTransformation.apply(newX, newY);

                // Применяем случайное нелинейное преобразование
                Transformation nonlinearTransformation = transformations.get(i);

                point = nonlinearTransformation.apply(point);

                // Проверяем, попадает ли точка в область просмотра и начинается ли отрисовка
                if (step >= 0 && Checker.boundCheck(point.x(), viewport.x(), viewport().x() + viewport.width())
                    && Checker.boundCheck(point.y(), viewport.y(), viewport().y() + viewport.height())) {
                    // Преобразуем координаты точки в пиксельные координаты изображения

                     point = config.convertCoordinatePixelImage(viewport, point);

                    int x = (int) point.x();
                    int y = (int) point.y();

                    // Если точка попадает в границы изображения
                    if (fractalImage.contains(x, y)) {

                        Pixel pixel = fractalImage.getPixel(x, y);
                        if (pixel != null) {
                            pixel.paintPixelHitCount(affineTransformation.color());
                        }
                    }
                }
            }
        }
    }
}
