package backend.academy.transformation;

import backend.academy.services.Constant;
import java.security.SecureRandom;

public class CoefficientGeneratorImpl implements CoefficientGenerator {
    private static final SecureRandom secureRandom = new SecureRandom();

    public static double randomCoefficient(double min, double max) {
        return secureRandom.nextDouble(min, max);
    }

    public AffineTransformation generate() {
        double a = randomCoefficient(Constant.INTERVALFROM1_5, Constant.INTERVALTO1_5);
        double b = randomCoefficient(Constant.INTERVALFROM1_5, Constant.INTERVALTO1_5);
        double c = randomCoefficient(Constant.INTERVALFROM1_5, Constant.INTERVALTO1_5);
        double d = randomCoefficient(Constant.INTERVALFROM1_5, Constant.INTERVALTO1_5);

        double e = randomCoefficient(-1, 1);
        double f = randomCoefficient(-1, 1);

        while (!isValidCoefficients(a, b, c, d)) {
            a = randomCoefficient(Constant.INTERVALFROM1_5, Constant.INTERVALTO1_5);
            b = randomCoefficient(Constant.INTERVALFROM1_5, Constant.INTERVALTO1_5);
            c = randomCoefficient(Constant.INTERVALFROM1_5, Constant.INTERVALTO1_5);
            d = randomCoefficient(Constant.INTERVALFROM1_5, Constant.INTERVALTO1_5);

            e = randomCoefficient(-1, 1);
            f = randomCoefficient(-1, 1);
        }
            return new AffineTransformation(a, b, c, d, e, f);
    }

    private static boolean isValidCoefficients(double a, double b, double c, double d) {
        double adMinusBc = a * d - b * c;
        return (a * a + c * c) < 1 &&
            (b * b + d * d) < 1 &&
            (a * a + c * c + b * b + d * d) < 1 + (adMinusBc * adMinusBc) * (adMinusBc * adMinusBc);
    }
}
