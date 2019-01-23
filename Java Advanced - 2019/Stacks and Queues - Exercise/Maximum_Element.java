import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class Maximum_Element {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int commandsCount = Integer.parseInt(scanner.nextLine());
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < commandsCount; i++) {
            int[] tokens = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int codeOfCommand = tokens[0];

            switch (codeOfCommand) {
                case 1:
                    int element = tokens[1];
                    stack.push(element);
                    break;
                case 2:
                    stack.pop();
                    break;
                case 3:
                    printMaximumElement(stack);
                    break;
            }
        }
    }

    private static void printMaximumElement(ArrayDeque<Integer> stack) {
        int[] elementsArr = stack.stream().mapToInt(e -> e).toArray();
        int maximumElement = Integer.MIN_VALUE;

        for (int i = 0; i < elementsArr.length; i++) {
            if (elementsArr[i] > maximumElement) {
                maximumElement = elementsArr[i];
            }
        }

        System.out.println(maximumElement);
    }
}
