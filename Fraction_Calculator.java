import java.util.Scanner;
import java.util.regex.*;
import java.util.ArrayList;

class Fraction {
    private int n, d;

    //Создание простой дроби (с заданным числителем и знаменателем)
    public Fraction(int n, int d) throws Exception {
        if (d == 0) {throw new Exception("Некорректный параметр");}
        else
            this.n = n;
            this.d = d;
    }
    //Создание простой дроби по умолчанию (числитель 1, знаменатель 1)
    public Fraction(){
        this.n = 1;
        this.d = 1;
    }

    public String toString() {return n + "/" + d;};

    public int getN(){return n;}
    public void setN(int n){this.n = n;}

    public int getD(){return d;}
    public void setD(int d){this.d = d;}

    //Из строковых элементов вида "n/d" получаем объект Fraction
    public static Fraction toFraction(String x){
        Fraction r = new Fraction();

        String[] X = x.split("/");

        r.setN(Integer.parseInt(X[0]));
        r.setD(Integer.parseInt(X[1]));

        return r;
    }

    //Поиск НОД
    public static int NOD(int n, int d){
        while (d != 0){
            int div = n % d;
            n = d;
            d = div;
        }
        return n;
    }

    public static Fraction Simp(Fraction r){
        int n = r.getN();
        int d = r.getD();
        int div = NOD(n, d);

        if(n > 0 && d < 0 || n < 0 && d > 0){
            n /= -div;
            d /= -div;
        }else{
            n /= div;
            d /= div;
        }

        r.setN(n);
        r.setD(d);

        return r;
    }

    //Сумма
    public static Fraction Sum(Fraction f1, Fraction f2){
        Fraction r = new Fraction();

        r.setN(f1.getN() * f2.getD() + f1.getD() * f2.getN());
        r.setD(f1.getD() * f2.getD());

        return Simp(r);
    }
/*
    //Разность
    public static Fraction Dis(Fraction f1, Fraction f2){
        int n, d;
        Fraction r = new Fraction();

        r.setN(f1.getN() * f2.getD() - f1.getD() * f2.getN());
        r.setD(f1.getD() * f2.getD());

        return Simp(r);
    }
*/
    //Умножение
    public static Fraction Mul(Fraction f1, Fraction f2){
        Fraction r = new Fraction();

        r.setN(f1.getN() * f2.getN());
        r.setD(f1.getD() * f2.getD());

        return Simp(r);
    }

    //Деление
    public static Fraction Div (Fraction f1, Fraction f2) {
        Fraction r = new Fraction();

        r.setN(f1.getN() * f2.getD());
        r.setD(f1.getD() * f2.getN());

        return Simp(r);
    }
}


public class Fraction_Calculator {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Введите выражение: ");
        String str = in.nextLine();

        String pattern = "^-?[0-9]+/-?[0-9]+(( \\+ | - | \\* | : ){1}+-?[0-9]+/-?[0-9])+$";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);

        ArrayList<Fraction> numbers = new ArrayList<Fraction>();
        ArrayList<String> signs = new ArrayList<String>();

        if (m.matches()) {
            String elements[] = str.split(" ");

            for (int i = 0; i < elements.length; i++) {
                if (i % 2 == 0) {
                    numbers.add(Fraction.toFraction(elements[i]));
                } else
                    signs.add(elements[i]);
            }
            /*
            for (int i = 0; i< numbers.size(); i++){
                System.out.println(numbers.get(i).toString());
            }
             */

            for (int i = 0; i < signs.size(); i++) {
                if (signs.get(i).equals("-")) {
                    signs.set(i, "+");
                    numbers.get(i + 1).setN(-numbers.get(i + 1).getN());
                }
            }

            while (signs.size() > 0) {
                for (int i = 0; i < signs.size(); i++) {
                    if (signs.get(i).equals(":")) {
                        if (numbers.get(i + 1).getN() == 0){
                            signs.clear();
                            numbers.clear();
                        }else {
                            signs.remove(i);
                            numbers.set(i, Fraction.Div(numbers.get(i), numbers.get(i + 1)));
                            numbers.remove(i + 1);
                        }
                    }
                }

                for (int i = 0; i < signs.size(); i++) {
                    if (signs.get(i).equals("*")) {
                        signs.remove(i);
                        numbers.set(i, Fraction.Mul(numbers.get(i), numbers.get(i + 1)));
                        numbers.remove(i + 1);
                    }
                }

                for (int i = 0; i < signs.size(); i++) {
                    if (signs.get(i).equals("+")) {
                        signs.remove(i);
                        numbers.set(i, Fraction.Sum(numbers.get(i), numbers.get(i + 1)));
                        numbers.remove(i + 1);
                    }
                }
            }
            if(numbers.isEmpty()){
                System.out.println("Ошибка. Деление на 0.");
            }else
            System.out.println(numbers.get(0).toString());
        } else System.out.println("Ошибка. Некорректное выражение.");
    in.close();
    }
}