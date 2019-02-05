import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class AddVat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Function<String, Double> parseDouble = Double::parseDouble;
        UnaryOperator<Double> addVat = d -> d + 0.2 * d;
        Consumer<Double> print = s -> System.out.println(String.format("%.2f", s));
        String[] prices = scanner.nextLine().split(", ");

        System.out.println("Prices with VAT:");
        Arrays.stream(prices)
                .map(parseDouble)
                .map(addVat)
                .forEach(print);
    }
}
