package backend.academy.transformation;


import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressFBWarnings("PCOA_PARTIALLY_CONSTRUCTED_OBJECT_ACCESS")
public class AffineTransformationSet {
    private List<AffineTransformation> transformation = new ArrayList<>();
    private CoefficientGenerator coefficientGenerator;
    private int count;

    public AffineTransformationSet(int count) {
        this.count = count;
        this.coefficientGenerator = new CoefficientGeneratorImpl();
        generateEq(this.count);
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
