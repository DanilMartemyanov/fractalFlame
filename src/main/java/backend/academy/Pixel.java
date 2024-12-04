package backend.academy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.awt.Color;

@Getter
@Setter
@AllArgsConstructor
public class Pixel {
    private int red;
    private int green;
    private int blue;
    private int hitCount;
    private double normal;

    public Pixel(int r, int g, int b) {
        this.red = r;
        this.green = g;
        this.blue = b;
        this.hitCount = 0;
        this.normal = 0;
    }



    public void paintPixelHitCount(Color color) {
        if (hitCount() == 0) {

            red = color.getRed();
            green = color.getGreen();
            blue = color.getBlue();

        } else {
            red = (red + color.getRed()) / 2;
            green = (green + color.getGreen()) / 2;
            blue = (blue + color.getBlue()) / 2;

        }

        hitCount++;
    }

    @Override
    public String toString() {
        return "Pixel: " + "red: " + red + ", green: " + green + ", b: " + blue + ", hitCount: " + hitCount +
            ", normal: " +
            normal;
    }
}
