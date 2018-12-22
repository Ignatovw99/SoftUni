import java.util.Arrays;
import java.util.Scanner;

public class Max_Sequence_of_Equal_Elements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] array = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int maxSequenceLength = 0;
        int startingIndex = 0;
        int endIndex = 0;

        for (int i = 0; i < array.length - 1; i++) {
            int element = array[i];
            int currSequenceLength = 1;
            for (int j = i + 1; j < array.length; j++) {
                if (element == array[j]){
                    currSequenceLength++;
                    if (j == array.length - 1){
                        if (currSequenceLength > maxSequenceLength){
                            maxSequenceLength = currSequenceLength;
                            startingIndex = i;
                            endIndex = j;
                            break;
                        }
                    }
                } else {
                    if (currSequenceLength > maxSequenceLength){
                        maxSequenceLength = currSequenceLength;
                        startingIndex = i;
                        endIndex = j - 1;
                    }
                    break;
                }
            }
        }

        for (int i = startingIndex; i <= endIndex; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
