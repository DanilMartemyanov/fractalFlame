package backend.academy.services;

public class Checker {
    public static boolean boundCheck(double x, double boundFrom, double boundTo) {
        return x >= boundFrom && x <= boundTo;
    }
}
