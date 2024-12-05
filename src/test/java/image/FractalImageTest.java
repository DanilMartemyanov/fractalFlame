package image;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import backend.academy.image.FractalImage;
import backend.academy.models.Pixel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FractalImageTest {
    private FractalImage fractalImage;

    @BeforeEach
    void setUp() {
        fractalImage = FractalImage.init(3, 3);

    }

    @Test
    void testGetPixelValid() {
        Pixel pixel = fractalImage.getPixel(1, 1);
        assertNotNull(pixel);
        assertEquals(0, pixel.red());

    }

    @Test
    void testContainsValidCoordinates() {
        assertTrue(fractalImage.contains(0, 0));
        assertTrue(fractalImage.contains(2, 2));
    }

}
