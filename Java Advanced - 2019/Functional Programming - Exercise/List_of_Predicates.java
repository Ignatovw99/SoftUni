import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class List_of_Predicates {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int upperBound = Integer.parseInt(scanner.nextLine());

        int[] sequence = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        Predicate<Integer> isDivisibleBySequence = number -> {
            int[] divisibleElements = Arrays.stream(sequence)
                    .filter(e -> number % e == 0)
                    .toArray();

            return divisibleElements.length == sequence.length;
        };
        Consumer<Integer> printer = num -> System.out.print(num + " ");

        int lowerBound = getMaxDivisibleNumber(sequence);
        IntStream
                .rangeClosed(lowerBound, upperBound)
                .boxed()
                .filter(isDivisibleBySequence)
                .forEach(printer);
    }

    private static int getMaxDivisibleNumber(int[] sequence) {
        return Arrays.stream(sequence).max().getAsInt();
    }
}
