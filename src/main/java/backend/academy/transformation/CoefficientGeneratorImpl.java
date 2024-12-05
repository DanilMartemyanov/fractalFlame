package backend.academy.transformation;

import backend.academy.services.Constant;
import java.util.concurrent.ThreadLocalRandom;

public class CoefficientGeneratorImpl implements CoefficientGenerator {

    public static double randomCoefficient(double min, double max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    public AffineTransformation generate() {
        double a = randomCoefficient(Constant.LARGER_LOWER_BOUND, Constant.LARGER_UPPER_BOUND);
        double b = randomCoefficient(Constant.LARGER_LOWER_BOUND, Constant.LARGER_UPPER_BOUND);
        double c = randomCoefficient(Constant.LARGER_LOWER_BOUND, Constant.LARGER_UPPER_BOUND);
        double d = randomCoefficient(Constant.LARGER_LOWER_BOUND, Constant.LARGER_UPPER_BOUND);

        double e = randomCoefficient(Constant.SMALLER_LOWER_BOUND, Constant.SMALLER_UPPER_BOUND);
        double f = randomCoefficient(Constant.SMALLER_LOWER_BOUND, Constant.SMALLER_UPPER_BOUND);

        while (!isValidCoefficients(a, b, c, d)) {
            a = randomCoefficient(Constant.LARGER_LOWER_BOUND, Constant.LARGER_UPPER_BOUND);
            b = randomCoefficient(Constant.LARGER_LOWER_BOUND, Constant.LARGER_UPPER_BOUND);
            c = randomCoefficient(Constant.LARGER_LOWER_BOUND, Constant.LARGER_UPPER_BOUND);
            d = randomCoefficient(Constant.LARGER_LOWER_BOUND, Constant.LARGER_UPPER_BOUND);

            e = randomCoefficient(Constant.SMALLER_LOWER_BOUND, Constant.SMALLER_UPPER_BOUND);
            f = randomCoefficient(Constant.SMALLER_LOWER_BOUND, Constant.SMALLER_UPPER_BOUND);
        }
        return new AffineTransformation(a, b, c, d, e, f);
    }

    private static boolean isValidCoefficients(double a, double b, double c, double d) {
        double adMinusBc = a * d - b * c;
        return (a * a + c * c) < Constant.SMALLER_UPPER_BOUND
            && (b * b + d * d) < Constant.SMALLER_UPPER_BOUND
            && (a * a + c * c + b * b + d * d)
            < Constant.SMALLER_UPPER_BOUND + (adMinusBc * adMinusBc) * (adMinusBc * adMinusBc);
    }
}
