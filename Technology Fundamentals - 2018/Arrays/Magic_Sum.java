import java.util.Arrays;
import java.util.Scanner;

public class Magic_Sum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] array = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int number = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < array.length - 1; i++) {
            int element = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (element + array[j] == number){
                    System.out.println(String.format("%d %d", element, array[j]));
                }
            }
        }
    }
}
