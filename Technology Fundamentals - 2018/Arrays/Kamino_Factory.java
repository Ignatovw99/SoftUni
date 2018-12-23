import java.util.Arrays;
import java.util.Scanner;

public class Kamino_Factory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int DNALength = Integer.parseInt(scanner.nextLine());
        String command = scanner.nextLine();

        StringBuilder bestDNA = new StringBuilder();
        int longestSubsequence = Integer.MIN_VALUE;
        int startingIndex = Integer.MIN_VALUE;
        int bestSum = Integer.MIN_VALUE;
        int bestLine = Integer.MIN_VALUE;
        int line = 0;

        while (!command.equalsIgnoreCase("Clone them!")){
            line++;
            int[] currentDNA = Arrays.stream(command.split("\\!+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int currentStartingIndex = 0;
            int subsequenceLength = 0;

            for (int i = 0; i < currentDNA.length; i++) {
                int currentElement = currentDNA[i];

                boolean isBetter = false;

                if (currentElement == 1){
                    subsequenceLength++;
                    if (subsequenceLength > longestSubsequence){
                        isBetter = true;
                    } else if (subsequenceLength == longestSubsequence){
                        if (currentStartingIndex < startingIndex){
                            isBetter = true;
                        } else if (currentStartingIndex == startingIndex){
                            int currentSum = Arrays.stream(currentDNA).sum();
                            if (currentSum > bestSum){
                                isBetter = true;
                            }
                        }
                    }
                    if (isBetter){
                        bestDNA = new StringBuilder();
                        for (int element : currentDNA) {
                            bestDNA.append(element + " ");
                        }
                        longestSubsequence = subsequenceLength;
                        startingIndex = currentStartingIndex;
                        bestSum = Arrays.stream(currentDNA).sum();
                        bestLine = line;
                    }
                }  else {
                    currentStartingIndex = i + 1;
                    subsequenceLength = 0;
                }
            }
            command = scanner.nextLine();
        }
        System.out.println(String.format("Best DNA sample %d with sum: %d.", bestLine, bestSum));
        System.out.println(bestDNA.toString());
    }
}
