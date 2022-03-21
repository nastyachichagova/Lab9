abstract class Figure {
	String name;
	
	Figure(String name) {
		this.name = name;
	}
	
	abstract double square();

}

class Triangle extends Figure {
	double a,b,c;
	
	public Triangle(String name, double a, double b, double c) throws Exception {
		super(name);
		if (a < 0 || b < 0 || c < 0) {
			throw new Exception("Некорректный параметр");
		} else {
			this.a = a;
			this.b = b;
			this.c = c;
		}
	}
	
	public double square() {
        double p = (a+b+c)/2;
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
	}
	
	public String toString() {
		return this.name + square();
	}
}

class Rectangle extends Figure {
	double a,b;
	
	public Rectangle(String name, double a, double b) {
		super(name);
		this.a = a;
		this.b = b;
	}
	
	public double square() {
        return a * b;
	}
}


public class UsingAbstractClasses {
	public static void main (String[] args) {
		Figure f1;
		
		try {
			f1 = new Triangle("Треугольник 1", 1, 1, 1);
			
			System.out.println(f1.square());
			System.out.println(f1.toString());
			System.out.println(f1);
		} catch(Exception e) {
			//f1 = null;
			System.out.println(e.toString());
			System.exit(-1);
		}
		

	}
}
