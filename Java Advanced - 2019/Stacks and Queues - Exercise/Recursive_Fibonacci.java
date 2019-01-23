import java.util.Scanner;

public class Recursive_Fibonacci {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        long[] memoization = new long[n + 1];

        long result = getFibonacciWithMemoization(n, memoization);
        System.out.println(result);
    }

    private static long getFibonacciWithMemoization(int n, long[] memo) {
        if (n <= 1) {
            return 1;
        }

        if (memo[n] != 0) {
            return memo[n];
        }

        memo[n] = getFibonacciWithMemoization(n - 1, memo) + getFibonacciWithMemoization(n - 2, memo);
        return memo[n];
    }
}
