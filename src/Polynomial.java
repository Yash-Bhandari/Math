import java.util.LinkedList;

public class Polynomial {
	private double[] terms;
	private int degree;

	public Polynomial() {
		terms = new double[1];
		degree = -1;
	}

	/**
	 * Adds a term with the coefficient and degree specified to the polynomial
	 * 
	 * @param coefficient The coefficient of the term
	 * @param degree      The power that x is raised to
	 */
	public void addTerm(double coefficient, int degree) {
		if (this.degree < degree) {
			this.degree = degree;
			if (this.degree + 1 > terms.length)
				resizeUp(this.degree + 1);
		}
		terms[degree] = coefficient;
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
					if (!plusAdded & !leading)
						s += "+ ";
					s += String.valueOf(terms[i]);
				}
				if (i != 0) {
					if (!plusAdded & !leading)
						s += "+ ";
					s += "x^" + i;
				}
				leading = false;
			}
		}
		return s;
	}

	public static void main(String[] args) {
		Polynomial p = new Polynomial();
		p.addTerm(2, 7);
		p.addTerm(4, 0);
		p.addTerm(3, 1);
		p.addTerm(1, 2);
		System.out.println(p);
	}

	private void resizeUp(int min) {
		double[] larger = new double[Math.max(min, terms.length * 2)];
		for (int i = 0; i < terms.length; i++) {
			larger[i] = terms[i];
		}
		terms = larger;
	}
}
