package backend.academy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Pixel{
    private int r;
    private int g;
    private int b;
    private int hitCount;
    private double normal;

    public Pixel (int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.hitCount = 0;
        this.normal = 0;
    }


    @Override
    public String toString() {
        return "Pixel: " + "red: " + r + ", green: " + g + ", b: " + b + ", hitCount: " + hitCount + ", normal: " + normal;
    }
}
