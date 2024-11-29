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

            for (int step = -20; step < config.iterations(); step++) {

                double xmin = -1.777;
                double xmax = 1.777;
                double ymin = -1.0;
                double ymax = 1.0;

                double newX = random.nextDouble(xmin, xmax);
                double newY = random.nextDouble(ymin, ymax);

                // Выбираем случайное аффинное преобразование
                AffineTransformation affineTransformation = affineTransformationSet.getRandom(i);

                Point point = affineTransformation.createPoint(newX, newY);

                // Применяем случайное нелинейное преобразование
                Transformation nonlinearTransformation = nonLinearTransformationSet.getRandomTransformation();

                point = nonlinearTransformation.apply(point);


                // Проверяем, попадает ли точка в область просмотра и начинается ли отрисовка
                if (step >= 0 && Checker.boundCheck(point.x(), xmin, xmax)
                    && Checker.boundCheck(point.y(), ymin, ymax)) {
                    // Преобразуем координаты точки в пиксельные координаты изображения
                    int x1 = (int) (config.xRes() - ((xmax - point.x()) / (xmax - xmin)) * config.xRes());
                    int y1 = (int) (config.yRes() - ((ymax - point.y()) / (ymax - ymin)) * config.yRes());

                    // Если точка попадает в границы изображения
                    if (fractalImage.contains(x1, y1)) {
                        // Получаем текущий пиксель
                        Pixel pixel = fractalImage.getPixel(x1, y1);

                        int newR;
                        int newG;
                        int newB;

                        if (pixel.hitCount() == 0) {
                            newR = affineTransformation.color().getRed();
                            newG = affineTransformation.color().getGreen();
                            newB = affineTransformation.color().getBlue();

                        } else {
                            newR = (pixel.r() + affineTransformation.color().getRed()) / 2;
                            newG = (pixel.g() + affineTransformation.color().getGreen()) / 2;
                            newB = (pixel.b() + affineTransformation.color().getBlue()) / 2;
                        }

                        // Создаем новый пиксель с обновленными значениями
                        Pixel newPixel = new Pixel(
                            newR,
                            newG,
                            newB,
                            pixel.hitCount() + 1
                        );


                        fractalImage.setPixel(x1, y1, newPixel);
                    }
                }
            }
        }
    }
}
