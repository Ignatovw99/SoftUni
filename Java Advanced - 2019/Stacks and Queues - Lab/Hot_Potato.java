import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Scanner;

public class Hot_Potato {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] names = scanner.nextLine().split("\\s+");
        int n = Integer.parseInt(scanner.nextLine());
        int round = 1;
        ArrayDeque<String> children = new ArrayDeque<>();
        Collections.addAll(children, names);

        while (children.size() > 1) {
            for (int i = 0; i < n - 1; i++) {
                children.offer(children.pop());
            }
            if (isPrime(round)) {
                System.out.println("Prime " + children.peek());
            } else {
                System.out.println("Removed " + children.poll());
            }
            round++;
        }
        System.out.println("Last is " + children.pop());
    }

    private static boolean isPrime(int number) {
        if (number == 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
