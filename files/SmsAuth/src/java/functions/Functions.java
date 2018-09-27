package functions;

public class Functions {

    public static int factorial(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
    
    public static int combine(int n, int k) {
        return factorial(n) / (factorial(k) * factorial(n - k));
    }
    
    public static int randomRange(int a, int b) {
        int range = (b + 1) - a;
        int result = (int) ((Math.random() * range) + a);
        return result;
    }
    
}
