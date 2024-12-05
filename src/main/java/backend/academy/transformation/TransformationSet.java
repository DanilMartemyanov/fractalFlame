package backend.academy.transformation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Getter;

@Getter
public class TransformationSet {
    private final Map<String, Transformation> transformations;
    private Disk disk = new Disk();
    private Heart heart = new Heart();
    private Polar polar = new Polar();
    private Sinusoidal sinusoidal = new Sinusoidal();
    private Spherical spherical = new Spherical();
    private Handkerchief handkerchief = new Handkerchief();

    public TransformationSet() {
        this.transformations = Map.of(
            "disk", disk,
            "heart", heart,
            "polar", polar,
            "sinusoidal", sinusoidal,
            "spherical", spherical,
            "handkerchief", handkerchief
        );
    }

    public List<Transformation> getTransformations(List<String> transformationNames) {
        List<Transformation> nonLinTransformations = new ArrayList<>();
        for(String transformationName : transformationNames) {
            if(this.transformations.containsKey(transformationName)) {
                nonLinTransformations.add(transformations.get(transformationName));
            }
        }
        return nonLinTransformations;
    }

}
