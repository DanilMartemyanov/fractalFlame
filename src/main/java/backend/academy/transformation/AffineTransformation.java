package backend.academy.transformation;

import backend.academy.models.Point;
import backend.academy.services.Constant;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.awt.Color;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Getter;

@Getter
@SuppressFBWarnings("PREDICTABLE_RANDOM")
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
            ThreadLocalRandom.current().nextInt(Constant.MAX_VALUE_COLOR),
            ThreadLocalRandom.current().nextInt(Constant.MAX_VALUE_COLOR),
            ThreadLocalRandom.current().nextInt(Constant.MAX_VALUE_COLOR)
        );
    }

    public Point apply(double x, double y) {
        return new Point(
            coefficients.get("a") * x + coefficients.get("b") * y + coefficients.get("e"),
            coefficients.get("c") * x + coefficients.get("d") * y + coefficients.get("f")
        );
    }

}
