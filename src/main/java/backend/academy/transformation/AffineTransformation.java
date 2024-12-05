package backend.academy.transformation;

import backend.academy.Point;
import backend.academy.services.Constant;
import java.awt.Color;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Getter;

@Getter
public class AffineTransformation {
    private Map<String, Double> coefficients;
    private Color color;

    public AffineTransformation(double a, double b, double c, double d, double e, double f) {
        this.coefficients = Map.of(
            "a", a,
            "b", b,
            "c", c,
            "d", d,
            "e", e,
            "f", f
        );
        this.color = new Color(
            ThreadLocalRandom.current().nextInt(Constant.MAXVALUECOLOR),
            ThreadLocalRandom.current().nextInt(Constant.MAXVALUECOLOR),
            ThreadLocalRandom.current().nextInt(Constant.MAXVALUECOLOR)
        );
    }

    public Point apply(double x, double y) {
        return new Point(
            coefficients.get("a") * x + coefficients.get("b") * y + coefficients.get("e"),
            coefficients.get("c") * x + coefficients.get("d") * y + coefficients.get("f")
        );
    }

}
