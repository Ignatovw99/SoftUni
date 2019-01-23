import java.util.ArrayDeque;
import java.util.Scanner;

public class Decimal_To_Binary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = Integer.parseInt(scanner.nextLine());

        if (number == 0) {
            System.out.println(0);
            return;
        }

        ArrayDeque<Integer> binaryDigits = new ArrayDeque<>();

        while (number > 0) {
            binaryDigits.push(number % 2);
            number /= 2;
        }

        while (!binaryDigits.isEmpty()) {
            System.out.print(binaryDigits.pop());
        }
    }
}
