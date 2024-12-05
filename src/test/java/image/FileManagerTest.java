package image;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import backend.academy.image.FractalImage;
import backend.academy.models.Pixel;
import backend.academy.services.FileManager;
import java.awt.Color;
import java.awt.image.BufferedImage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FileManagerTest {
    private FractalImage fractalImage;
    private Pixel[] pixels;
    private FileManager fileManager;

    @BeforeEach
    public void setUp() {
        pixels = new Pixel[4];
        pixels[0] = new Pixel(255, 0, 0);
        pixels[1] = new Pixel(0, 255, 0);
        pixels[2] = new Pixel(0, 0, 255);
        pixels[3] = new Pixel(255, 255, 0);
        fractalImage = mock(FractalImage.class);
        when(fractalImage.width()).thenReturn(2);
        when(fractalImage.height()).thenReturn(2);
        when(fractalImage.data()).thenReturn(pixels);

    }

    @Test
    public void testGetBufferedFractalImage() {
        BufferedImage bufferedImage = fileManager.getBufferedFractalImage(fractalImage);

        assertNotNull(bufferedImage);
        assertEquals(2, bufferedImage.getWidth());
        assertEquals(2, bufferedImage.getHeight());

        assertEquals(Color.RED.getRGB(), bufferedImage.getRGB(0, 0));
        assertEquals(Color.GREEN.getRGB(), bufferedImage.getRGB(1, 0));

        assertEquals(Color.BLUE.getRGB(), bufferedImage.getRGB(0, 1));
        assertEquals(new Color(255, 255, 0).getRGB(), bufferedImage.getRGB(1, 1));
    }

    @Test
    public void testGetFileExtension() {
        String filePath = "image.png";
        String extension = FileManager.getFileExtension(filePath);

        assertEquals("png", extension);
    }

    @Test
    public void testGetFileExtensionEmpty() {
        String filePath = "image";
        String extension = FileManager.getFileExtension(filePath);

        assertEquals("", extension);
    }

    @Test
    public void testGetFileExtensionNoExtension() {
        String filePath = "image.";
        String extension = FileManager.getFileExtension(filePath);

        assertEquals("", extension);
    }

}
