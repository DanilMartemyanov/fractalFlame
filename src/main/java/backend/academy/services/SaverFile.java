package backend.academy.services;

import backend.academy.image.FractalImage;


public interface SaverFile {
    void save(FractalImage image, String filePath);
}
