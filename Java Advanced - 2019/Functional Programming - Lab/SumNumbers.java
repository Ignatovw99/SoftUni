import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SumNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Function<String, Integer> parseInt = Integer::parseInt;

        List<Integer> numbers = Arrays.stream(scanner.nextLine().split(", "))
                .map(parseInt)
                .collect(Collectors.toList());
        int countElements = numbers.size();
        int sum = numbers.stream()
                .reduce(0, (x, y) -> x + y);

        System.out.println(String.format("Count = %d", countElements));
        System.out.println(String.format("Sum = %d", sum));
    }
}
