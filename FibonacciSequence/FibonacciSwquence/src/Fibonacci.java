import java.util.*;

public class Fibonacci {

    private static long[] fibonacciNumberCache;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of terms for the fibonacci sequence: ");
        int numberOfTerms = scanner.nextInt();
        scanner.close();
        fibonacciNumberCache = new long[numberOfTerms + 1];

        if (numberOfTerms <= 0) {
            System.out.println("Please enter a posative integer.");
        } else {
            System.out.println("Fibonacci sequence:");
            for (int i = 0; i < numberOfTerms; i++) {
                System.out.print(fibonacci(i) + " ");
            }
            System.out.println();
        }
    }

    public static long fibonacci(int n) {
        if (n <= 1) {
            return n;
        }

        if (fibonacciNumberCache[n] != 0) {
            return fibonacciNumberCache[n];
        }

        long nthFibonacciNumber = fibonacci(n -1) + fibonacci(n - 2);
        fibonacciNumberCache[n] = nthFibonacciNumber;

        return nthFibonacciNumber;
    }
}
