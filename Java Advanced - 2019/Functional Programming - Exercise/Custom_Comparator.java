import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.function.Consumer;

public class Custom_Comparator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Comparator<Integer> sortEvenBeforeOddNums = (first, second) -> {
            if ((first % 2 == 0 && second % 2 == 0) || (first % 2 != 0 && second % 2 != 0)) {
                return Integer.compare(first, second);
            } else if (first % 2 == 0) {
                return -1;
            } else {
                return 1;
            }
        };

        Consumer<Integer> printer = number -> System.out.print(number + " ");

        Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .boxed()
                .sorted(sortEvenBeforeOddNums)
                .forEach(printer);
    }
}
