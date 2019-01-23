import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class Reverse_Numbers_with_Stack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] inputNumbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        ArrayDeque<Integer> reversedNumbers = new ArrayDeque<>();

        for (int i = 0; i < inputNumbers.length; i++) {
            reversedNumbers.push(inputNumbers[i]);
        }

        while (!reversedNumbers.isEmpty()) {
            System.out.print(reversedNumbers.pop() + " ");
        }
    }
}
