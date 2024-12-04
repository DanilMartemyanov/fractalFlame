package backend.academy.image;

import backend.academy.Pixel;
import backend.academy.Point;
import backend.academy.Rect;
import java.util.ArrayList;
import java.util.List;

public record FractalImageUtils(int xRes, int yRes, int eqCount, int iterations) {

    public  Point convertCoordinatePixelImage(Rect viewport, Point point) {

        double xmax = viewport.x() + viewport.width();
        double ymax = viewport.y() + viewport.height();

        int x1 =
            (xRes() - (int) (((xmax - point.x()) / viewport.width()) * xRes()));
        int y1 =
            (yRes() - (int) (((ymax - point.y()) / viewport.height()) * yRes()));

        return new Point(x1, y1);
    }



}
