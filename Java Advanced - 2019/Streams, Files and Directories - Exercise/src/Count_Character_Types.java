import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Count_Character_Types {
    public static void main(String[] args) throws IOException {
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        char[] punctuationMarks = {'!', ',', '.', '?'};
        FileReader reader = new FileReader("input.txt");
        BufferedReader bufferedReader = new BufferedReader(reader);

        String line = bufferedReader.readLine();
        int vowelsCount = 0;
        int consonantsCount = 0;
        int punctuationCount = 0;

        do {
            for (int i = 0; i < line.length(); i++) {
                char currentChar = line.charAt(i);
                if (currentChar == ' ') {
                    continue;
                }

                if (isCharacterVowel(vowels, currentChar)) {
                    vowelsCount++;
                } else if (isCharacterPunctuationMark(punctuationMarks, currentChar)) {
                    punctuationCount++;
                } else {
                    consonantsCount++;
                }
            }
            line = bufferedReader.readLine();
        } while (line != null);

        PrintWriter writer = new PrintWriter("output.txt");
        writer.println(String.format("Vowels: %d", vowelsCount));
        writer.println(String.format("Consonants: %d", consonantsCount));
        writer.println(String.format("Punctuation: %d", punctuationCount));

        reader.close();
        writer.close();
    }

    private static boolean isCharacterPunctuationMark(char[] punctuationMarks, char currentChar) {
        return containsElement(punctuationMarks, currentChar);
    }

    private static boolean containsElement(char[] array, char element) {
        for (char currentElement : array) {
            if (currentElement == element) {
                return true;
            }
        }
        return false;
    }

    private static boolean isCharacterVowel(char[] vowels, char currentChar) {
        return containsElement(vowels, currentChar);
    }
}
