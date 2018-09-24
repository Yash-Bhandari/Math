/**
 * This class provides static methods that perform mathematical operations
 * 
 * @author Yash Bhandari
 *
 */

public class MathA {

    public static final double PI = 3.14159265359;
    
    /**
     * Returns a double equal to the base raised to the given power
     * 
     * @param base
     *            a double equal to the base
     * @param power
     *            an integer representing the base is to be raised to
     * @return base^power
     */
    public static double exponent(double base, int power) {
        double output = base;
        if (power < 1) {
            base = 1 / base;
            output = 1;
            power = -power + 1;
        }
        for (int i = 1; i < power; i++) {
            output = output * base;
        }
        return output;
    }

    /**
     * Returns an integer equal to n factorial (n*(n-1)*(n-2)*(n-3)*...*1)
     * 
     * @param n a positive integer greater than 0
     * @return n!
     */
    public static int factorial(int n) {
        int total = 1;
        for (int i = n; i > 1; i--) {
            total = total * i;
        }
        return total;
    }

    /**
     * Returns a double approximation of cosine(theta) found with an 8th degree
     * MacLaurin polynomial
     * 
     * @param theta
     *            the angle in radians
     * @return cosine(theta)
     */
    public static double cos(double theta) {
        if (theta > Math.PI);
        double approx = 1;
        for (int n = 1; n <= 6; n++) {
            approx += exponent(-1, n) * exponent(theta, 2*n) / factorial(2*n);
        }
        return approx;
    }

    public static void main(String[] args) {
        System.out.println(cos(PI));
        System.out.println(cos(Math.PI));
        int n = 8;
        System.out.println(exponent(-1, n) * exponent(PI, 2*n) / factorial(2*n));
    }
}
