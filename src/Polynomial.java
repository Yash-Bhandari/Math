import java.util.LinkedList;

public class Polynomial {
    private LinkedList<Double> terms;
    private int degree;

    public Polynomial() {
        terms = new LinkedList<Double>();
    }

    public void addTerm(double coefficient, int degree) {
        if (this.degree < degree) {
            this.degree = degree;
        }
        terms.add(degree, coefficient);

    }

    public String toString() {
        String s = "";
        for (int i = terms.size() - 1; i >= 0; i--) {
            if (terms.get(i) != 0) {
                boolean plusAdded = false;
                if (i < terms.size() - 1) {
                    s += " ";
                }
                if (terms.get(i) != 1 || i == 0) {
                    if (!plusAdded)
                        s += "+ ";
                    s += String.valueOf(terms.get(i));
                }
                if (i != 0)
                    s += "x^" + i;
            }
        }
        return s;
    }

    public static void main(String[] args) {
        Polynomial p = new Polynomial();
        p.addTerm(4, 0);
        p.addTerm(3, 1);
        p.addTerm(1, 2);
        p.addTerm(2, 7);
        System.out.println(p);
    }
}
