import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Find_The_Smallest_Element {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Integer> numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayList::new));

        Function<ArrayList<Integer>, Integer> findSmallestElement = arr -> {
            int min = arr.stream().min(Integer::compare).get();
            return arr.lastIndexOf(min);
        };

        System.out.println(findSmallestElement.apply(numbers));
    }
}
