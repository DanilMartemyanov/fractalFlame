package backend.academy.transformation;

import backend.academy.Point;
import lombok.Getter;

import java.awt.Color;
import java.security.SecureRandom;
import java.util.Map;

@Getter
public class AffineTransformation {
    private Map<String, Double> coefficients;
    private Color color;
    private SecureRandom random = new SecureRandom();


    public AffineTransformation(double a, double b, double c, double d, double e, double f) {
        this.coefficients = Map.of(
            "a", a,
            "b", b,
            "c", c,
            "d", d,
            "e", e,
            "f", f
        );
        this.color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
    public Point apply(double x, double y) {
        return new Point(
            coefficients.get("a") * x + coefficients.get("b") * y + coefficients.get("e"),
            coefficients.get("c") * x + coefficients.get("d") * y + coefficients.get("f")
        );
    };
}
