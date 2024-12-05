package backend.academy.services;

import backend.academy.Pixel;
import backend.academy.image.FractalImage;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

@Log4j2
@UtilityClass
public class FileManager  {
    public static BufferedImage getBufferedFractalImage(FractalImage fractalImage) throws IOException {
        int width = fractalImage.width();
        int height = fractalImage.height();

        Pixel[] pixels = fractalImage.data();

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < pixels.length; i++) {
            int x = i % width;
            int y = i / width;

            Pixel pixel = pixels[i];
            Color color = new Color(pixel.red(), pixel.green(), pixel.blue());

            bufferedImage.setRGB(x, y, color.getRGB());
        }

        return bufferedImage;

    }

    public static void save(FractalImage image, String filePath) {
        try {
            BufferedImage bufferedImage = getBufferedFractalImage(image);
            File outputFile = new File(filePath);
            String fileExtension = getFileExtension(filePath);
            ImageIO.write(bufferedImage, fileExtension, outputFile);
            log.info("Image saved as: " + filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getFileExtension(String filePath) {
        String fileName = new File(filePath).getName();

        int indexOfLastDot = fileName.lastIndexOf('.');

        if (indexOfLastDot > 0 && indexOfLastDot < fileName.length() - 1) {
            return fileName.substring(indexOfLastDot + 1);
        }

        return "";
    }
}
