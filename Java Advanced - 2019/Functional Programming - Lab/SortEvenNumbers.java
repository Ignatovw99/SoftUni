import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SortEvenNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numbers = Arrays.stream(scanner.nextLine().split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] evenNumbers = Arrays.stream(numbers)
                .filter(x -> x % 2 == 0)
                .toArray();

        printNumbers(evenNumbers);
        Arrays.sort(evenNumbers);
        printNumbers(evenNumbers);
    }

    private static void printNumbers(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            if (i == numbers.length - 1) {
                System.out.println(numbers[i]);
            } else {
                System.out.print(String.format("%d, ", numbers[i]));
            }
        }
    }
}
