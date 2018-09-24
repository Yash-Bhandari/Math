/**
 * This class provides static methods for standard mathematical operations and
 * simple calculus
 * 
 * @author Yash Bhandari
 *
 */

public class MathA {

	public static final double PI = 3.14159265359;

	/**
	 * Returns a double equal to the base raised to the given power
	 * 
	 * @param base  a double equal to the base
	 * @param power an integer representing the base is to be raised to
	 * @return base^power
	 */
	public static double exponent(double base, int power) {
		boolean reduced = false;
		double coeffecient = 1;
		if (power == 0)
			return 1;
		while (!reduced) {
			if (power % 2 == 0 && (power > 1 || power < -1)) {
				base = base * base;
				power = power / 2;
			} else if (power > 1 || power < -1) {
				if (power > 1) {
					coeffecient = coeffecient * base;
					power--;
				} else {
					coeffecient = coeffecient / base;
					power++;
				}
			} else
				reduced = true;
		}
		double output = base;
		if (power < 1) {
			base = 1 / base;
			output = 1;
			power = -power + 1;
		}
		for (int i = 1; i < power; i++) {
			System.out.println("called");
			output = output * base;
		}
		return output * coeffecient;
	}

	/**
	 * Returns an integer equal to n factorial (n*(n-1)*(n-2)*(n-3)*...*1). Accurate
	 * only up to and including n = 12
	 * 
	 * @param n a positive integer greater than 0 but less than 13
	 * @return n!
	 */
	public static int factorial(int n) {
		if (n > 12 || n < 1)
			throw new IllegalArgumentException("n must be between 1 and 12, inclusive");
		int total = 1;
		for (int i = n; i > 1; i--) {
			total = total * i;
		}
		return total;
	}

	/**
	 * Returns a double equal to n factorial (n*(n-1)*(n-2)*(n-3)*...*1). Accurate
	 * up to and including n = 170
	 * 
	 * 
	 * @param n a positive double greater than 0 that will be rounded down
	 * @return n!
	 */
	public static double factorial(double n) {
		if (n > 170 || n < 1)
			throw new IllegalArgumentException("n must be between 1 and 170, inclusive");
		int a = (int) n;
		double total = 1;
		for (int i = (int) a; i > 1; i--) {
			total = total * i;
		}
		return total;
	}

	/**
	 * Returns a double approximation of cosine(theta) found with a 26th degree
	 * MacLaurin polynomial. Accurate up to 10 decimals places.
	 * 
	 * @param theta the angle in radians
	 * @return cos(theta)
	 */
	public static double cos(double theta) {
		while (theta > PI) {
			theta -= 2 * PI;
		}
		while (theta < -PI) {
			theta += 2 * PI;
		}
		double approx = 1;
		for (int n = 1; n <= 13; n++) {
			double add = exponent(-1, n) * exponent(theta, 2 * n) / factorial((double) (2 * n));
			approx += add;
		}
		return approx;
	}

	/**
	 * Returns a double approximation of sine(theta) found with a 26th degree
	 * MacLaurin polynomial. Accurate up to 10 decimals places.
	 * 
	 * @param theta the angle in radians
	 * @return sin(theta)
	 */
	public static double sin(double theta) {
		while (theta > PI) {
			theta -= 2 * PI;
		}
		while (theta < -PI) {
			theta += 2 * PI;
		}
		double approx = 0;
		for (int n = 1; n <= 13; n++) {
			double add = exponent(-1, n + 1) * exponent(theta, 2 * n - 1) / factorial((double) (2 * n - 1));
			approx += add;
		}
		return approx;
	}

	/**
	 * Returns a double approximation of tangent(theta) found with two 26th degree
	 * MacLaurin polynomials for sine and cosine. Accurate up to 10 decimal places
	 * 
	 * @param theta the angle in radians, cannot equal PI/2 + n*PI where n is an
	 *              integer
	 * @return tan(theta)
	 */
	public static double tan(double theta) {
		while (theta > PI) {
			theta -= 2 * PI;
		}
		if (abs(theta) == PI / 2)
			throw new IllegalArgumentException("Outside of domain");
		return sin(theta) / cos(theta);
	}

	/**
	 * Returns a double approximation of secant(theta) found with a 26th degree
	 * MacLaurin polynomial for cosine.
	 * 
	 * @param theta the angle in radians, cannot equal PI/2 + n*PI where n is an
	 *              integer
	 * @return sec(theta)
	 */
	public static double sec(double theta) {
		while (theta > PI) {
			theta -= 2 * PI;
		}
		if (abs(theta) == PI / 2)
			throw new IllegalArgumentException("Outside of domain");
		return 1.0 / cos(theta);
	}

	/**
	 * Returns a double approximation of cosecant(theta) found with a 26th degree
	 * MacLaurin polynomial for sine.
	 * 
	 * @param theta the angle in radians, cannot equal n*PI where n is an integer
	 * @return csc(theta)
	 */
	public static double csc(double theta) {
		while (theta > PI) {
			theta -= 2 * PI;
		}
		if (abs(theta) == PI || abs(theta) == 0)
			throw new IllegalArgumentException("Outside of domain");
		return 1.0 / sin(theta);
	}

	/**
	 * Returns a double approximation of cotangent(theta) found with two 26th degree
	 * MacLaurin polynomials for sine and cosine.
	 * 
	 * @param theta the angle in radians, cannot equal n*PI where n is an integer
	 * @return cot(theta)
	 */
	public static double cot(double theta) {
		while (theta > PI) {
			theta -= 2 * PI;
		}
		if (abs(theta) == PI || abs(theta) == 0)
			throw new IllegalArgumentException("Outside of domain");
		return cos(theta) / sin(theta);
	}

	/**
	 * 
	 * Returns a double that is the absolute value of the parameter
	 * 
	 * @param a
	 * @return Absolute value of a
	 */
	public static double abs(double a) {
		if (a < 0)
			return -a;
		return a;
	}

	/**
	 * Returns the first derivative of the given polynomial
	 * 
	 * @param p a polynomial representing (p)
	 * @return a polynomial representing p'
	 */
	public static Polynomial ddx(Polynomial p) {
		Polynomial pPrime = new Polynomial();
		for (int i = p.degree(); i > 0; i--) {
			if (p.getTerm(i) != 0) {
				pPrime.setTerm(p.getTerm(i) * i, i - 1);
			}
		}
		return pPrime;
	}

	/**
	 * Returns the anti-derivative of the given polynomial
	 * 
	 * @param p a polynomial representing p(x)
	 * @return the indefinite integral of p
	 */
	public static Polynomial aDeriv(Polynomial p) {
		Polynomial integral = new Polynomial();
		for (int i = 0; i <= p.degree(); i++) {
			if (p.getTerm(i) != 0) {
				integral.setTerm(p.getTerm(i) / (i + 1), i + 1);
			}
		}
		integral.setConstant(true);
		return integral;
	}

	public static void main(String[] args) {
		System.out.println(csc(0.1));
		// check();
		// System.out.println(Math.tan(PI/4));
		// System.out.println(tan(PI/4));
	}

	private static void check() {
		double maxDif = 0;
		double refI = 0.001;
		double index = -3.1;
		while (index <= PI) {
			double dif = 1 / Math.sin(index) - csc(index);
			index += 0.1;
			if (Math.abs(maxDif) < Math.abs(dif)) {
				maxDif = dif;
				refI = index - 0.1;
			}
		}
		System.out.println(maxDif);
		System.out.println(refI);
	}
}
