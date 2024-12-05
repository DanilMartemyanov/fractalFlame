package backend.academy.transformation;

import java.util.List;

public class AffineTransformationSet {
    private List<AffineTransformation> transformation;
    private int count;
    private CoefficientGenerator coefficientGenerator = new CoefficientGeneratorImpl();

    public AffineTransformationSet(List<AffineTransformation> transformation, int count) {
        this.transformation = transformation;
        generateEq(count);
    }

    public void generateEq(int count) {
        for (int i = 0; i < count; i++) {
            transformation.add(coefficientGenerator.generate());
        }
    }

    public AffineTransformation getRandom(int i) {
        return transformation.get(i);
    }

}
