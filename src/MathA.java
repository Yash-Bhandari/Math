
/**
 * This class provides static methods for standard mathematical operations and
 * simple calculus.
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
	 * @return cos(theta) on the domain [-PI, PI]
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
	 * Returns a double approximation of arcsin(a) accurate up to 8 decimal places
	 * 
	 * @param a a double on the domain [-1, 1]
	 * @return arcsin(a) with a range [-PI/2, PI/2]
	 */
	public static double asin(double a) {
		if (a < -1 || a > 1)
			throw new IllegalArgumentException("Outside of domain");

		double high = PI / 2;
		double low = -PI / 2;
		if (sin(high) == a)
			return high;
		if (sin(low) == a)
			return low;
		double middle;
		while (true) {
			middle = mean(high, low);
			double check = sin(middle);
			if (abs(a - check) <= 0.00000001) {
				return middle;
			}
			if (a > check) {
				low = middle;
			} else {
				high = middle;
			}

		}
	}

	/**
	 * Returns a double approximation of arccos(a) accurate up to 8 decimal places
	 * 
	 * @param a a double on the domain [-1, 1]
	 * @return arccos(a) with a range [0, PI]
	 */
	public static double acos(double a) {
		if (a < -1 || a > 1)
			throw new IllegalArgumentException("Outside of domain");
		double high = PI;
		double low = 0;
		if (cos(high) == a)
			return high;
		if (cos(low) == a)
			return low;
		double middle;
		int ticker = 0;
		while (true) {
			ticker++;
			middle = mean(high, low);
			double check = cos(middle);
			if (abs(a - check) <= 0.00000001) {
				System.out.println(ticker + " tries");
				return middle;
			}
			if (a > check) {
				high = middle;
			} else {
				low = middle;
			}
		}
	}

	/**
	 * Returns a double approximation of arctan(a) accurate up to 8 decimal places
	 * 
	 * @param a a double on the domain (-infinity, infinity)
	 * @return arccos(a) with a range (-PI/2, PI/2)
	 */
	public static double atan(double a) {
		if (abs(a) < exponent(1, -5))
			return a;
		if (a > exponent(1, 5)) 
			return PI / 2 - 1 / a;
		if (a < -exponent(1, 5))
			return PI / 2 + 1 / a;
		if (a == Double.POSITIVE_INFINITY)
			return PI / 2;
		if (a == Double.NEGATIVE_INFINITY)
			return -PI / 2;
		double high = PI / 2 - 0.000001;
		double low = -PI / 2 + 0.000001;
		if (tan(high) == a)
			return high;
		if (tan(low) == a)
			return low;
		double middle;
		int ticker = 0;
		while (true) {
			ticker++;
			middle = mean(high, low);
			double check = tan(middle);
			if (abs(a - check) <= 0.00000001) {
				return middle;
			}
			if (a > check) {
				low = middle;
			} else {
				high = middle;
			}

		}
	}

	/**
	 * Returns the absolute value of the given double
	 * 
	 * @param a
	 * @return |a|
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

	public static double defInt(Polynomial p, double lowerBound, double upperBound) {
		Polynomial integral = aDeriv(p);
		return integral.eval(upperBound) - integral.eval(lowerBound);
	}

	/**
	 * Returns the mean of two doubles
	 * 
	 * @param a first double
	 * @param b second double
	 * @return (a+b)/2
	 */
	public static double mean(double a, double b) {
		return (a + b) / 2;
	}

	/**
	 * Returns the value of n permute r as described by the formula nPr = n!/(n-r)!
	 * Beware integer overflows.
	 * 
	 * @param n an integer representing the total number of elements
	 * @param r an integer representing the number of elements to permute
	 * @return an integer representing the number of ways to permute r elements from
	 *         a set of n elements
	 */
	public static int permute(int n, int r) {
		double temp = factorial(n);
		temp = temp / factorial(n - r);
		return (int) temp;
	}

	/**
	 * Returns the value of n choose r as described by the formula nCr =
	 * n!/((n-r)!r!) Beware integer overflows.
	 * 
	 * @param n an integer representing the total number of elements
	 * @param r an integer representing the number of elements to choose
	 * @return an integer representing the number of ways to choose r elements from
	 *         a set of n elements
	 */
	public static int choose(int n, int r) {
		if (n == r || r == 0)
			return 1;
		return choose(n - 1, r - 1) + choose(n - 1, r);
	}

	public static void main(String[] args) {
		// System.out.println(tan(PI));
		System.out.println(atan(1.34E5));
		// System.out.println(acos(0.22));
		// Polynomial p = new Polynomial();
		// p.setTerm(1, 2);
		// System.out.println(defInt(p, 0, 4));
		// System.out.println(choose(37, 7));
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
