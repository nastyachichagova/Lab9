abstract class Equation {
    String degree;

    Equation(String degree){this.degree = degree; }
    abstract double Value();
}

class Quadratic_equation extends Equation {
    double x, y;

    public Quadratic_equation(String degree, double x, double y){
        super(degree);
        this.x = x;
        this.y = y;
    }

    public double Value(){
        return Math.pow((x+y), 2);
    }
    public String toString() {
        return this.degree + Value();
    }
}

class Cubic_equation extends Equation {
    double x, y;

    public Cubic_equation(String degree, double x, double y) {
        super(degree);
        this.x = x;
        this.y = y;
    }

    public double Value(){
        return Math.pow((x+y), 3);
    }

}

public class Сalculations {
    public static void main (String[] args) {
        Equation e1;

        e1 = new Cubic_equation ("Квадратное уравнение 1 ", 78, 1);
        System.out.println(e1.Value());
        System.out.println(e1.toString());

    }
}
