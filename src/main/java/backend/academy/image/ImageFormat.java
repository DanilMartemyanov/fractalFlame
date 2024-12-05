package backend.academy.image;

import lombok.Getter;

@Getter
public enum ImageFormat {

    JPEG("jpeg"),
    BMP("bmp"),
    PNG("png");

    private String title;

    ImageFormat(String title) {
        this.title = title;
    }
}
