package backend.academy.services;

import backend.academy.Rect;
import backend.academy.image.FractalImageUtils;
import java.util.ArrayList;
import java.util.List;

public class RectUtils {

    public static Rect create(FractalImageUtils config){
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

    public static List<Rect> split(Rect viewport, int numParts) {
        List<Rect> subRectangles = new ArrayList<>();
        double partHeight = viewport.height() / numParts;

        for (int i = 0; i < numParts; i++) {
            double y = viewport.y() + i * partHeight;
            double height = (i == numParts - 1) ? viewport.height() - i * partHeight : partHeight;
            subRectangles.add(new Rect(viewport.x(), y, viewport.width(), height));
        }

        return subRectangles;
    }

}
