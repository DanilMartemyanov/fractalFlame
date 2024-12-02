package backend.academy;

import backend.academy.image.FractalImage;
import backend.academy.image.FractalImageConfig;
import backend.academy.services.Checker;
import backend.academy.transformation.AffineTransformation;
import backend.academy.transformation.AffineTransformationSet;
import backend.academy.transformation.CoefficientGenerator;
import backend.academy.transformation.CoefficientGeneratorImpl;
import backend.academy.transformation.Transformation;
import backend.academy.transformation.NonLinearTransformationSet;
import lombok.Getter;
import java.security.SecureRandom;

@Getter
public class FractalRender {
    private FractalImageConfig config;
    private NonLinearTransformationSet nonLinearTransformationSet = new NonLinearTransformationSet();
    private AffineTransformationSet affineTransformationSet;
    private CoefficientGenerator coefficientGenerator = new CoefficientGeneratorImpl();
    private Rect viewport;
    private FractalImage fractalImage;
    private SecureRandom random = new SecureRandom();

    public FractalRender(FractalImageConfig config, Rect viewport) {
        this.config = config;
        this.viewport = viewport;
        this.fractalImage = FractalImage.init(config.xRes(), config.yRes());
    }

    public void render(int n) {
        SecureRandom random = new SecureRandom();
        // Генерируем аффинные преобразования
        affineTransformationSet = new AffineTransformationSet(coefficientGenerator);
        affineTransformationSet.generateEq(config.eqCount());

        for (int num = 0; num < n; num++) {

            int i = random.nextInt(config.eqCount());

            double xmin = viewport.x();
            double xmax = viewport.x() + viewport.width();
            double ymin = viewport.y();
            double ymax = viewport.y() + viewport.height();

            double newX = random.nextDouble(viewport.x(), viewport.width() + viewport.x());
            double newY = random.nextDouble(viewport.y(), viewport.height() + viewport.y());

            for (int step = -20; step < config.iterations(); step++) {

                // Выбираем случайное аффинное преобразование
                AffineTransformation affineTransformation = affineTransformationSet.getRandom(i);

                Point point = affineTransformation.apply(newX, newY);

                // Применяем случайное нелинейное преобразование
                Transformation nonlinearTransformation = nonLinearTransformationSet.getRandomTransformation();

                point = nonlinearTransformation.apply(point);

                // Проверяем, попадает ли точка в область просмотра и начинается ли отрисовка
                if (step >= 0 && Checker.boundCheck(point.x(), xmin, xmax)
                    && Checker.boundCheck(point.y(), ymin, ymax)) {
                    // Преобразуем координаты точки в пиксельные координаты изображения
                    int x1 =
                        (config.xRes() - (int) (((xmax - point.x()) / viewport.width()) * config.xRes()));
                    int y1 =
                        (config.yRes() - (int) (((ymax - point.y()) / viewport.height()) * config.yRes()));

                    // Если точка попадает в границы изображения
                    if (fractalImage.contains(x1, y1)) {

                        Pixel pixel = fractalImage.getPixel(x1,y1);

                        if (pixel.hitCount() == 0) {
                            pixel.r(affineTransformation.color().getRed());
                            pixel.g(affineTransformation.color().getGreen());
                            pixel.b(affineTransformation.color().getBlue());

                        } else {
                             pixel.r((pixel.r() + affineTransformation.color().getRed()) / 2);
                             pixel.g((pixel.g() + affineTransformation.color().getGreen()) / 2);
                             pixel.b((pixel.b() + affineTransformation.color().getBlue()) / 2);
                        }

                        pixel.hitCount(pixel.hitCount() + 1);
                    }
                }
            }
        }
    }
}
