package transformation;

import backend.academy.transformation.AffineTransformation;
import backend.academy.transformation.CoefficientGenerator;
import backend.academy.transformation.CoefficientGeneratorImpl;
import org.junit.jupiter.api.Test;

public class CoefficientGenerationTest {

    @Test
    void generateCoefficients() {
        CoefficientGenerator coefficientGenerator = new CoefficientGeneratorImpl();
        AffineTransformation affineTransformation = coefficientGenerator.generate();
        System.out.println(affineTransformation);

    }
}
