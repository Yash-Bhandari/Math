
/**
 * This class provides static methods for many mathematical operations,
 * including some not included in the default java Math class, as well as some
 * simple calculus.
 * 
 * @author Yash Bhandari
 *
 */

public class MathA {

	public static final double PI = 3.14159265359;
	public static final double e = 2.71828182845;

	/**
	 * Returns a double equal to the base raised to the given integer power
	 * 
	 * @param base  a double representing the base
	 * @param power an integer representing the base is to be raised to
	 * @return base^power
	 */
	public static double exp(double base, int power) {
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
	 * Returns a double equal to euler's number raised to the given rational power
	 * 
	 * @param power a double representing to power e will be raised to
	 * @return e^power
	 */
	public static double exp(double power) {
		double total = 0;
		int nearest = round(power);
		for (int i = 0; i < 13; i++) {
			total += exp(e, nearest) * exp((power - nearest), i) / factorial(i);
		}
		return total;
	}

	/**
	 * Returns a double equal to the base raised to the given rational power
	 * 
	 * @param base  a double representing the base
	 * @param power a double representing the power the base is raised to
	 * @return base^power
	 */
	public static double exp(double base, double power) {
		if (base == e)
			return exp(power);
		double total = 0;
		int nearest = round(power);
		double log = ln(base);
		for (int i = 0; i < 8; i++) {
			total += exp(base, nearest) * exp((log * (power - nearest)), i) / factorial(i);
		}
		return total;
	}

	/**
	 * Returns a double equal to the natural logarithm of the argument
	 * 
	 * @param x a double representing the argument
	 * @return ln(x)
	 */
	public static double ln(double x) {
		if (x == 1)
			return 0;
		double high;
		double low;
		if (x > 1) {
			high = high(x);
			low = low(x);
		} else {
			high = low(x);
			low = high(x);
		}

		while (true) {
			double guess = exp((high + low) / 2);
			if (abs(guess - x) < 0.00000000001)
				return (high + low) / 2;
			if (guess > x)
				high = (high + low) / 2;
			else
				low = (high + low) / 2;
		}
	}

	// returns lower bound for natural log binary search
	private static int high(double x) {
		int low = 0;
		double temp = 1;

		if (x < 1) {
			while (temp >= x) {
				temp = temp / 2;
				low--;
			}
			return low;
		}
		while (temp <= x) {
			temp = temp * 2;
			low++;
		}
		return low;
	}

	// returns upper bound for natural log binary search
	private static int low(double x) {
		int high = 0;
		double temp = 1;
		if (x < 1) {
			while (temp / 3 >= x) {
				temp = temp / 3;
				high--;
			}
			return high;
		}
		while (temp * 3 <= x) {
			temp = temp * 3;
			high++;
		}
		return high;
	}

	/**
	 * Returns an integer equal to n factorial (n*(n-1)*(n-2)*(n-3)*...*1). Accurate
	 * only up to and including n = 12
	 * 
	 * @param n a positive integer greater than 0 but less than 13
	 * @return n!
	 */
	public static int factorial(int n) {
		if (n > 12 || n < 0)
			throw new IllegalArgumentException("n must be between 0 and 12, inclusive");
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
			double add = exp(-1, n) * exp(theta, 2 * n) / factorial((double) (2 * n));
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
			double add = exp(-1, n + 1) * exp(theta, 2 * n - 1) / factorial((double) (2 * n - 1));
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
		while (theta < -PI) {
			theta += 2 * PI;
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
		while (theta < -PI) {
			theta += 2 * PI;
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
		while (theta < -PI) {
			theta += 2 * PI;
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
		while (theta < -PI) {
			theta += 2 * PI;
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
		if (abs(a) < exp(1, -5))
			return a;
		if (a > exp(1, 5))
			return PI / 2 - 1 / a;
		if (a < -exp(1, 5))
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
	 * @param a a positive or negative double
	 * @return a if a is positive, -a if a is negative
	 */

	public static double abs(double a) {
		if (a < 0)
			return -a;
		return a;
	}

	/**
	 * Returns the absolute value of the given integer
	 *
	 * @param a a positive or negative integer
	 * @return a if a is positive, -a if a is negative
	 */

	public static int abs(int a) {
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

	/**
	 * Returns the value of n choose r as described by the formula nCr =
	 * n!/((n-r)!r!)
	 * 
	 * @param n a double representing the total number of elements
	 * @param r a double representing the number of elements to choose
	 * @return a double representing the number of ways to choose r elements from a
	 *         set of n elements
	 */
	public static double choose(double n, double r) {
		if (n == r || r == 0)
			return 1;
		return choose(n - 1, r - 1) + choose(n - 1, r);
	}

	/**
	 * Returns a polynomial representation of the provided binomial raised to the
	 * nth power.
	 * 
	 * @param aCoeff the coefficient of the first term
	 * @param aPower the degree of the first term
	 * @param bCoeff the coefficient of the second term
	 * @param bPower the degree of the second term
	 * @param n      the power the binomial should be raised to
	 * @return (aCoeff * x^aPower + bCoeff * x^bPower)^n
	 */
	public static Polynomial binomExp(double aCoeff, int aPower, double bCoeff, int bPower, int n) {
		Polynomial p = new Polynomial();
		for (int k = 0; k < n + 1; k++) {
			p.addTerm(choose(n, k) * exp(aCoeff, n - k) * exp(bCoeff, k), aPower * (n - k) + bPower * (k));
		}
		return p;
	}

	/**
	 * Rounds a double up if its decimal portion is greater than or equal to 0.5,
	 * down if it is less than 0.5
	 * 
	 * @param a the double to be rounded
	 * @return the rounded value
	 */
	public static int round(double a) {
		if (a - (int) a < 0.5)
			return (int) a;
		return (int) a + 1;
	}

	public static void main(String[] args) {
		/*double startTime = System.currentTimeMillis();
		for (int i = 0; i < 40000; i++) {
			double a = exp(0.42314 + i / 1000, 0.41302 + i / 2000);
		}
		System.out.println((System.currentTimeMillis() - startTime) / 1000);*/
		double startTime = System.currentTimeMillis();
		for (int i = 0; i < 40000; i++) {
			double a = Math.pow(0.42314 + i / 1000, 0.41302 + i / 2000);
		}
		System.out.println((System.currentTimeMillis() - startTime) / 1000);
		// System.out.println(exp(3.1));
		// System.out.println(ln(0.4));
		// check();
		// System.out.println(exp(2.1, 0.034));
		// System.out.println(exp(3.1, 2.1));
		// System.out.println(choose(37, 7));
	}

	private static void check() {
		double maxDif = 0;
		double refI = 0.1;
		double index = 0.1;
		while (index <= PI) {
			double dif = Math.log(index) - ln(index);
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
