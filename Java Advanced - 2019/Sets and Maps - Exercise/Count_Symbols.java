import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Count_Symbols {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputText = scanner.nextLine();
        Map<Character, Integer> occurrences = new TreeMap<>();

        for (int i = 0; i < inputText.length(); i++) {
            char currentChar = inputText.charAt(i);

            if (!occurrences.containsKey(currentChar)) {
                occurrences.put(currentChar, 1);
            } else {
                occurrences.put(currentChar, occurrences.get(currentChar) + 1);
            }
        }

        for (Map.Entry<Character, Integer> entry : occurrences.entrySet()) {
            char character = entry.getKey();
            int charOccurrences = entry.getValue();
            System.out.println(String.format("%c: %d time/s", character, charOccurrences));
        }
    }
}
