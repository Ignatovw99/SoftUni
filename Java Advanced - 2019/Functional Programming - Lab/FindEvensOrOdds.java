import java.util.Arrays;
import java.util.Scanner;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;

public class FindEvensOrOdds {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ToIntFunction<String> parseInt = Integer::parseInt;
        int[] rangeBound = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(parseInt)
                .toArray();
        int lowerBound = rangeBound[0];
        int upperBound = rangeBound[1];

        String numbersType = scanner.nextLine();
        Predicate<Integer> filterEven = x -> x % 2 == 0;
        Predicate<Integer> filterOdd = x -> x % 2 != 0;
        BiPredicate<Integer, String> byType = (number, type) -> type.equals("even") ?
                filterEven.test(number) : filterOdd.test(number);

        Consumer<Integer> print = x -> System.out.print(x + " ");

        IntStream
                .rangeClosed(lowerBound, upperBound)
                .boxed()
                .filter(number -> byType.test(number, numbersType))
                .forEach(print);
    }
}
