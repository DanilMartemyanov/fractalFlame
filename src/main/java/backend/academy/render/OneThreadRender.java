package backend.academy.render;

import backend.academy.Rect;
import backend.academy.image.FractalImage;
import backend.academy.image.FractalImageUtils;
import backend.academy.transformation.Transformation;
import java.util.List;

public class OneThreadRender extends AbstractRender {
    public OneThreadRender(List<Transformation> transformations, FractalImageUtils config) {
        super(transformations, config);
    }

    public void renderAllImage(FractalImage fractalImage, Rect viewport) {
        for (int num = 0; num < 5; num++) {
            super.renderImage(fractalImage, viewport);
        }
    }

}
