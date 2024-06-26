package math.trigonmetry;

public class Trigonometry {
    // TOD4 NEW
    // Add classes here for trig related stuffs

    /**
     * Calculates law of cosines for C knowing a,b and the angle between them
     * @param a
     * @param b
     * @param rad
     * @return C
     */
    public static double lawOfCosinesC(double a, double b, double rad){
        return Math.sqrt(a*a+b*b-(2*a*b*Math.cos(rad)));
    }

    /**
     * Calculates law of sines for the angle opposite to a knowing a,b and the angle opposite to b
     * @param a
     * @param b
     * @param bAng
     * @return aAng
     */
    public static double lawOfSinesAngle(double a, double b, double bAng){
        return Math.asin(a*Math.sin(bAng)*(1/b));
    }

    /**
     * Calculates the pythagorean theorem for the c knowing a and b
     * @param a
     * @param b
     * @return c
     */
    public static double pythag(double a, double b){
        return Math.sqrt(a*a + b*b);
    }
}
