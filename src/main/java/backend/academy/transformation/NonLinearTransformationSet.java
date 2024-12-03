package backend.academy.transformation;



import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class NonLinearTransformationSet {
    private final List<Transformation> transformations;
    private Disk disk = new Disk();
    private Heart heart = new Heart();
    private Polar polar = new Polar();
    private Sinusoidal sinusoidal = new Sinusoidal();
    private Spherical spherical = new Spherical();

    public NonLinearTransformationSet() {
        this.transformations = List.of(
            disk,
            heart,
            polar,
            sinusoidal,
            spherical
        );
    }


    public void addTransformation(Transformation transformation) {
        transformations.add(transformation);
    }

    public Transformation getRandomTransformation() {
        int index = ThreadLocalRandom.current().nextInt(transformations.size());
        return transformations.get(index);
    }
}
