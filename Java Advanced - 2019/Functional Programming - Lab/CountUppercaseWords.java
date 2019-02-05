import java.util.Arrays;
        import java.util.Scanner;
        import java.util.function.Consumer;
        import java.util.function.Function;
        import java.util.function.Predicate;

public class CountUppercaseWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words = scanner.nextLine().split("\\s+");

        Predicate<String> isNumber = s -> Character.isDigit(s.charAt(0));
        Predicate<String> isCharacterWithCapital = s -> s.charAt(0) == s.toUpperCase().charAt(0)
                && !isNumber.test(s);

        Consumer<String> print = System.out::println;

        String[] uppercaseWords = Arrays.stream(words)
                .filter(isCharacterWithCapital)
                .toArray(String[]::new);

        System.out.println(uppercaseWords.length);
        Arrays.stream(uppercaseWords)
                .forEach(print);
    }
}
