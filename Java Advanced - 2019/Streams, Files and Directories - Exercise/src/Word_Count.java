import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class Word_Count {
    public static void main(String[] args) throws IOException {
        FileReader fileWithWords = new FileReader("words.txt");
        BufferedReader reader = new BufferedReader(fileWithWords);
        List<String> words = Arrays.asList(reader.readLine().split("\\s+"));
        fileWithWords.close();
        Map<String, Integer> occurrences = initializeMap(words);
        FileReader inputText = new FileReader("text.txt");
        reader = new BufferedReader(inputText);
        PrintWriter writer = new PrintWriter("results.txt");

        String line = reader.readLine();

        while (line != null) {
            List<String> lineWords = Arrays.stream(line.split("[\\s+.,]"))
                    .collect(Collectors.toList());
            lineWords.removeAll(Arrays.asList("", null));
            for (int i = 0; i < lineWords.size(); i++) {
                String currentWord = lineWords.get(i);
                if (occurrences.containsKey(currentWord)) {
                    occurrences.put(currentWord, occurrences.get(currentWord) + 1);
                }
            }
            line = reader.readLine();
        }

        occurrences.entrySet().stream()
                .sorted((word1, word2) -> word2.getValue().compareTo(word1.getValue()))
                .forEach(entry -> {
                    String word = entry.getKey();
                    int wordOccurrences = entry.getValue();
                    for (String word1 : words) {
                        if (word.equalsIgnoreCase(word1)) {
                            word = word1;
                        }
                    }
                    writer.println(String.format("%s - %d", word, wordOccurrences));
                });

        inputText.close();
        writer.close();
    }

    private static Map<String, Integer> initializeMap(List<String> words) {
        Map<String, Integer> map = new HashMap<>();

        for (String word : words) {
            if (!map.containsKey(word.toLowerCase())) {
                map.put(word, 0);
            }
        }

        return map;
    }
}
