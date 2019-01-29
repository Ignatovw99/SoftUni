import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Count_Real_Numbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[] numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToDouble(Double::parseDouble)
                .toArray();

        Map<Double, Integer> occurrences = new LinkedHashMap<>();

        for (int i = 0; i < numbers.length; i++) {
            if (occurrences.containsKey(numbers[i])) {
                int count = occurrences.get(numbers[i]) + 1;
                occurrences.put(numbers[i], count);
            } else {
                occurrences.put(numbers[i], 1);
            }
        }

        for (Map.Entry<Double, Integer> entry : occurrences.entrySet()) {
            System.out.println(String.format("%.1f -> %d", entry.getKey(), entry.getValue()));
        }
    }
}
