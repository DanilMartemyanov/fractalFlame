package backend.academy.transformation;

import backend.academy.Point;

public class AffineTransformation {
    private final double[] coefficients;

    public AffineTransformation(double a, double b, double c, double d, double e, double f) {
        this.coefficients = new double[]{a, b, c, d, e, f};
    }

    public AffineTransformation(double[] coefficients) {
        if (coefficients.length != 6) {
            throw new IllegalArgumentException("Количество коэффициентов больше 6");
        }
        this.coefficients = coefficients.clone();
    }

    public Point apply(Point point) {
        double xNew = coefficients[0] * point.x() + coefficients[1] * point.y()+ coefficients[4];
        double yNew = coefficients[2] * point.x() + coefficients[3] * point.y() + coefficients[5];
        return new Point(xNew, yNew);
    }

    public double getCoefficient(int index) {
        if (index < 0 || index >= coefficients.length) {
            throw new IndexOutOfBoundsException("Index must be in range 0-5.");
        }
        return coefficients[index];
    }

    public void setCoefficient(int index, double value) {
        if (index < 0 || index >= coefficients.length) {
            throw new IndexOutOfBoundsException("Index must be in range 0-5.");
        }
        coefficients[index] = value;
    }

    @Override
    public String toString() {
        return String.format("AffineTransformation[a=%.3f, b=%.3f, c=%.3f, d=%.3f, e=%.3f, f=%.3f]",
            coefficients[0], coefficients[1], coefficients[2], coefficients[3], coefficients[4], coefficients[5]);
    }
}
