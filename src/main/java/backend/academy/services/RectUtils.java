package backend.academy.services;

import backend.academy.models.Rect;
import backend.academy.image.FractalImageUtils;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RectUtils {
    public static Rect create(FractalImageUtils config) {
        double aspectRatio = (double) config.xRes() / config.yRes();
        double verticalRange = 2.0;

        // Задаём диапазон по вертикали
        double height = verticalRange;
        double width = verticalRange * aspectRatio;

        // Центрируем прямоугольник
        double x = -width / 2;
        double y = -height / 2;

        return new Rect(x, y, width, height);
    }

}
