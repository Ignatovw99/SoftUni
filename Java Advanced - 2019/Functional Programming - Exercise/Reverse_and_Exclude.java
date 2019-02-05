import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Reverse_and_Exclude {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> inputNums = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int n = Integer.parseInt(scanner.nextLine());
        Predicate<Integer> isDivisible = number -> number % n != 0;
        Consumer<Integer> printer = x -> System.out.print(x + " ");

        Collections.reverse(inputNums);

        inputNums.stream()
                .filter(isDivisible)
                .forEach(printer);
    }
}
