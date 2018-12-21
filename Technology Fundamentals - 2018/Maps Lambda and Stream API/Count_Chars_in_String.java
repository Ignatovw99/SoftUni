import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Count_Chars_in_String {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Character, Integer> occurrences = new LinkedHashMap<>();

        String inputLine = scanner.nextLine();

        for (int i = 0; i < inputLine.length(); i++) {
            char currentChar = inputLine.charAt(i);

            if (!Character.isWhitespace(currentChar)){
                if (!occurrences.containsKey(currentChar)){
                    occurrences.put(currentChar, 0);
                }
                occurrences.put(currentChar, occurrences.get(currentChar) + 1);
            }
        }

        occurrences
                .forEach((character, count) -> System.out.println(String.format("%c -> %d", character, count)));
    }
}
