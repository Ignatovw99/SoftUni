import java.util.*;
import java.util.stream.Collectors;

public class Basic_Stack_Operations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] tokens = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(e -> Integer.parseInt(e))
                .toArray();

        List<Integer> inputNumbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int countOfElementsToPush = tokens[0];
        int countOfElementsToPop = tokens[1];
        int elementToCheck = tokens[2];

        for (int i = 0; i < countOfElementsToPush; i++) {
            stack.push(inputNumbers.get(i));
        }

        for (int i = 0; i < countOfElementsToPop; i++) {
            stack.pop();
        }

        if (stack.contains(elementToCheck)) {
            System.out.println(true);
        } else {
            if (stack.isEmpty()) {
                System.out.println(0);
            } else {
                int smallestElement = Integer.MAX_VALUE;
                while (!stack.isEmpty()) {
                    int current = stack.pop();
                    if (current < smallestElement) {
                        smallestElement = current;
                    }
                }
                System.out.println(smallestElement);
            }
        }
    }
}
