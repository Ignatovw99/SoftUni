import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class Basic_Queue_Operations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] tokens = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int countOfElementsToEnqueue = tokens[0];
        int countOfElementsToDequeue = tokens[1];
        int elementToCheck = tokens[2];

        int[] inputNumbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        ArrayDeque<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < countOfElementsToEnqueue; i++) {
            queue.offer(inputNumbers[i]);
        }

        for (int i = 0; i < countOfElementsToDequeue; i++) {
            queue.poll();
        }

        if (queue.contains(elementToCheck)) {
            System.out.println(true);
        } else {
            if (queue.isEmpty()) {
                System.out.println(0);
            } else {
                int smallestElement = Integer.MAX_VALUE;
                while (!queue.isEmpty()) {
                    int current = queue.poll();
                    if (current < smallestElement) {
                        smallestElement = current;
                    }
                }
                System.out.println(smallestElement);
            }
        }
    }
}
