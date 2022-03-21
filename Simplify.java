public class Simplify {

    public static int Simp(int n, int d){
        int div = 0;

        for (int i = n; i >= 1; i--) {
            if (n % i == 0 && d % i == 0){
                div = i;
                break;
            }
        }
        return div;
    }

    public static void main (String[] args) {
        int n = 1;
        int d = 5;

        System.out.println("До "+ n + "/" + d);

        int div = Simp(n, d);

        System.out.println(div);
        n /= div;
        d /= div;

        System.out.println("После "+ n + "/" + d);

        System.out.println(9%(-3));
    }
}
