import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Applied_Arithmetic {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        String line = scanner.nextLine();

        Function<int[], int[]> sum = arr -> Arrays.stream(arr).map(e -> ++e).toArray();
        UnaryOperator<int[]> subtract = arr -> Arrays.stream(arr).map(e -> --e).toArray();
        UnaryOperator<int[]> multiply = arr -> Arrays.stream(arr).map(e -> e *= 2).toArray();
        Consumer<int[]> printer = arr ->
                Arrays.stream(arr).forEach(e -> System.out.print(e + " "));

        while (!line.equals("end")) {
            switch (line) {
                case "add":
                    numbers = sum.apply(numbers);
                    break;
                case "subtract":
                    numbers = subtract.apply(numbers);
                    break;
                case "multiply":
                    numbers = multiply.apply(numbers);
                    break;
                case "print":
                    printer.accept(numbers);
                    System.out.println();
                    break;
            }
            line = scanner.nextLine();
        }
    }
}
