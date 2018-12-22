import java.util.Arrays;
import java.util.Scanner;

public class ZigZag_Arrays {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int arrayLength = Integer.parseInt(scanner.nextLine());

        int[] firstArray = new int[arrayLength];
        int[] secondArray = new int[arrayLength];

        for (int i = 0; i < arrayLength; i++){
            int[] line = Arrays.stream(scanner.nextLine()
                    .split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            if (i % 2 == 0){
                firstArray[i] = line[0];
                secondArray[i] = line[1];
            } else {
                firstArray[i] = line[1];
                secondArray[i] = line[0];
            }
        }

        Arrays.stream(firstArray)
                .forEach(e -> System.out.print(e + " "));
        System.out.println();
        Arrays.stream(secondArray)
                .forEach(e -> System.out.print(e + " "));
    }
}
