package image;

import static org.junit.jupiter.api.Assertions.assertEquals;
import backend.academy.image.FractalImageUtils;
import backend.academy.models.Point;
import backend.academy.models.Rect;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FractalImageUtilTest {
    private FractalImageUtils config;
    private Rect viewport;

    @BeforeEach
    void setUp() {
        config = new FractalImageUtils(800, 600, 5, 1000, 256);
        viewport = new Rect(0, 0, 4, 3);
    }

    @Test
    void testConvertCoordinatePixelImage() {

        Point point = new Point(2, 1.5);

        Point convertedPoint = config.convertCoordinatePixelImage(viewport, point);

        int expectedX = 400;
        int expectedY = 300;

        assertEquals(expectedX, convertedPoint.x());
        assertEquals(expectedY, convertedPoint.y());
    }

}
