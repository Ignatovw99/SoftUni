import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;

public class Knights_of_Honor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Consumer<String> print = str -> System.out.println(String.format("Sir %s", str));

        Arrays.stream(scanner.nextLine().split("\\s+"))
                .forEach(print);
    }
}
