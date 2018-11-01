/**
 * The Polynomial class provides a data type that can be used to define
 * polynomial functions and other functions with negative powers of x. Provides
 * methods for simpler evaluation and to perform calculus operations.
 * 
 * @author Yash Bhandari
 */

public class Polynomial implements Cloneable {
	private double[] terms;
	private double[] negTerms;
	private int degree;
	private int negDegree;
	private boolean constant;

	public Polynomial() {
		terms = new double[1];
		degree = -1;
		negDegree = 1;
	}

	/**
	 * Adds a term with the coefficient and degree specified to the polynomial,
	 * adding to the previous term if it existed
	 * 
	 * @param coefficient The coefficient of the term
	 * @param degree      The power that x is raised to
	 */
	public void addTerm(double coefficient, int degree) {
		if (degree < 0) {
			if (this.negDegree > degree) {
				this.negDegree = degree;
				if (-this.negDegree > terms.length) {
					resizeUp(this.degree);
				}
			}
			negTerms[-degree - 1] += coefficient;
		} else {
			if (this.degree < degree) {
				this.degree = degree;
				if (this.degree >= terms.length)
					resizeUp(this.degree + 1);
			}
			terms[degree] += coefficient;
		}
	}

	/**
	 * Sets a term with the coefficient and degree specified to the polynomial,
	 * overwriting the previous term if it existed
	 * 
	 * @param coefficient The coefficient of the term
	 * @param degree      The power that x is raised to
	 */
	public void setTerm(double coefficient, int degree) {
		if (degree < 0) {
			if (this.negDegree > degree) {
				this.negDegree = degree;
				if (-this.negDegree > terms.length) {
					resizeUp(this.degree);
				}
			}
			negTerms[-degree - 1] = coefficient;
		} else {
			if (this.degree < degree) {
				this.degree = degree;
				if (this.degree >= terms.length)
					resizeUp(this.degree + 1);
			}
			terms[degree] = coefficient;
		}
	}

	/**
	 * Evaluates the polynomial for the given value of x
	 * 
	 * @param x the value for x in the polynomial
	 * @return a double equivalent to the value of the polynomial after x has been
	 *         substituted
	 */
	public double eval(double x) {
		double total = 0;
		for (int i = 0; i <= degree; i++) {
			total += terms[i] * MathA.exponent(x, i);
		}
		return total;
	}

	/**
	 * Returns the coefficient of the term of degree i in the polynomial
	 * 
	 * @param i the degree of the term
	 * @return a double equivalent to the coefficient of the term
	 */
	public double getTerm(int i) {
		if (i > degree)
			throw new IllegalArgumentException();
		return terms[i];
	}

	/**
	 * Returns the degree of the polynomial
	 * 
	 * @return the degree of the polynomial, -1 if there are no terms
	 */
	public int degree() {
		return degree;
	}

	public String toString() {
		String s = "";
		boolean leading = true;
		for (int i = terms.length - 1; i >= 0; i--) {
			if (terms[i] != 0) {
				boolean plusAdded = false;
				if (!leading) {
					s += " ";
				}
				if (terms[i] != 1 || i == 0) {
					if (!plusAdded & !leading) {
						s += "+ ";
						plusAdded = true;
					}
					if (terms[i] % 1 == 0 && terms[i] < MathA.exponent(2, 31))
						s += String.valueOf((int) (terms[i]));
					else
						s += String.valueOf(terms[i]);
				}
				if (i != 0) {
					if (!plusAdded & !leading) {
						s += "+ ";
						plusAdded = true;
					}
					s += "x";
					if (i > 1)
						s += "^" + i;
				}
				leading = false;
			}
		}
		if (constant)
			s += " + c";
		return s;
	}

	@Override
	public Polynomial clone() {
		Polynomial clone = new Polynomial();
		for (int i = 0; i <= degree; i++) {
			clone.addTerm(this.getTerm(i), i);
		}
		return clone;
	}

	/**
	 * Clears all terms in the polynomial
	 */
	public void clear() {
		degree = -1;
		negDegree = -1;
		setConstant(false);
		terms = new double[0];
	}

	/**
	 * Integrates this polynomial
	 */
	public void integrate() {
		Polynomial old = (Polynomial) clone();
		clear();
		for (int i = 0; i <= old.degree; i++) {
			if (old.getTerm(i) != 0) {
				setTerm(old.getTerm(i) / (i + 1), i + 1);
			}
		}
		setTerm(0, 0);
		setConstant(true);
	}

	/**
	 * Differentiates this polynomial
	 */
	public void ddx() {
		Polynomial old = (Polynomial) clone();
		clear();
		for (int i = degree; i >= 1; i++) {
			if (old.getTerm(i) != 0) {
				setTerm(i * old.getTerm(i), i - 1);
			}
		}
		for (int i = -negDegree - 1; i >= 0; i++) {
		}
	}

	public void setConstant(boolean a) {
		constant = a;
	}

	public static void main(String[] args) {
		Polynomial p = new Polynomial();
		p.setTerm(2.3, 7);
		p.setTerm(4, 0);
		p.setTerm(3, 1);
		p.setTerm(1, 2);
		System.out.println("f(x) + " + p);
		// ystem.out.println(p.eval(2));
		p.integrate();
		System.out.println("The antiderivative of f(x) = " + p);
	}

	private void resizeUp(int min) {
		double[] larger = new double[Math.max(min, terms.length * 2)];
		for (int i = 0; i < terms.length; i++) {
			larger[i] = terms[i];
		}
		terms = larger;
	}
}
