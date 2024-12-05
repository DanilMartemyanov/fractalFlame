package backend.academy.services;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Checker {
    public static boolean checkBound(double x, double boundFrom, double boundTo) {
        return x >= boundFrom && x <= boundTo;
    }

    public static boolean checkExtensionFile(String extension) {
        switch (extension) {
            case "jpg", "bmp", "png":
                return true;
            default:
                return false;
        }
    }
}
