package backend.academy.services;

import backend.academy.Pixel;
import backend.academy.image.FractalImage;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FileManager {
    public static void saveFractalImageAsJPEG(FractalImage fractalImage, String filePath) throws IOException {
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

        File outputFile = new File(filePath);
        ImageIO.write(bufferedImage, "JPEG", outputFile);
        System.out.println("Image saved as: " + filePath);
    }
}
