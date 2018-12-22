import java.util.Arrays;
import java.util.Scanner;

public class Equal_Sums {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] array = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        boolean areEqual = false;

        for(int i = 0; i < array.length; i++){
            int leftSum = 0;
            int leftEnd = i - 1;
            int rightSum = 0;
            int rightStart = i + 1;

            for (int j = 0; j <= leftEnd; j++) {
                leftSum += array[j];
            }
            for (int j = rightStart; j < array.length && rightStart < array.length; j++) {
                rightSum += array[j];
            }

            if (leftSum == rightSum){
                System.out.print(i);
                areEqual = true;
            }
        }

        if (!areEqual){
            System.out.println("no");
        }
    }
}
