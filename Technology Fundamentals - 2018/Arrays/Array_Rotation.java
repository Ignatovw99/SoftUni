import java.util.Arrays;
import java.util.Scanner;

public class Array_Rotation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] inputArray = Arrays.stream(scanner.nextLine()
                .split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int rotations = Integer.parseInt(scanner.nextLine());

        int[] outputArray = new int[inputArray.length];

        for (int i = 0; i < inputArray.length; i++) {
            int index = (i + rotations) % inputArray.length;
            outputArray[i] = inputArray[index];
        }

        Arrays.stream(outputArray)
                .forEach(e -> System.out.print(e + " "));
    }
}
