import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Predicate_for_Names {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberToCheckLength = Integer.parseInt(scanner.nextLine());

        Predicate<String> predicateForNames = name -> name.length() <= numberToCheckLength;
        Consumer<String> printer = System.out::println;

        Arrays.stream(scanner.nextLine().split("\\s+"))
                .filter(predicateForNames)
                .forEach(printer);
    }
}
