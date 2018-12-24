import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Append_Arrays {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> appendArrays = new ArrayList<>();

        String[] arrays = scanner.nextLine().split("\\|");

        for (int i = arrays.length - 1; i >= 0; i--) {
            String[] currentArray = arrays[i].trim().split("\\s+");

            for (String num : currentArray) {
                if (!num.equals("")){
                    appendArrays.add(Integer.parseInt(num));
                }
            }
        }

        printAppendedArrays(appendArrays);
    }

    private static void printAppendedArrays(List<Integer> appendArrays) {
        appendArrays
                .forEach(x -> System.out.print(x + " "));
    }
}
