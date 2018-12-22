import java.util.Arrays;
import java.util.Scanner;

public class Top_Integers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] array = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        for (int i = 0; i < array.length - 1; i++){
            int element = array[i];
            boolean isGreater = true;
            for (int j = i + 1; j < array.length; j++){
                if (element <= array[j]){
                    isGreater = false;
                    break;
                }
            }
            if (isGreater){
                System.out.print(element + " ");
            }
        }
        System.out.println(array[array.length - 1]);
    }
}
