package backend.academy.transformation;

import java.util.ArrayList;
import java.util.List;

public class AffineTransformationSet {
    private List<AffineTransformation> transformation;
    private CoefficientGenerator coefficientGenerator;


    public AffineTransformationSet(CoefficientGenerator coefficientGenerator) {
        this.transformation = new ArrayList<>();
        this.coefficientGenerator = coefficientGenerator;
    }


    public void generateEq(int count){
        for(int i = 0; i < count; i++){
            transformation.add(coefficientGenerator.generate());
        }
    }

    public AffineTransformation getRandom(int i ){
        return transformation.get(i);
    }

}
