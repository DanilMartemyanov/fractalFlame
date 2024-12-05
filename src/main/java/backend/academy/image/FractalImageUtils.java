package backend.academy.image;

import backend.academy.models.Point;
import backend.academy.models.Rect;

public record FractalImageUtils(int xRes, int yRes, int eqCount, int iterations, int saturations) {

    public Point convertCoordinatePixelImage(Rect viewport, Point point) {

        double xmax = viewport.x() + viewport.width();
        double ymax = viewport.y() + viewport.height();

        int x1 =
            (xRes() - (int) (((xmax - point.x()) / viewport.width()) * xRes()));
        int y1 =
            (yRes() - (int) (((ymax - point.y()) / viewport.height()) * yRes()));

        return new Point(x1, y1);
    }

    @Override
    public String toString() {
        return "width: " + xRes + " height: " + yRes + " iterations: " + iterations + " saturations: " + saturations
            + " eqCount: " + eqCount;
    }
}
