package game.utils;

public class MathUtils {

    public static double[] unit(double dx, double dy) {
        double len = Math.sqrt(dx*dx + dy*dy);
        if (len == 0) return new double[]{0,0};
        return new double[]{dx/len, dy/len};
    }
}
