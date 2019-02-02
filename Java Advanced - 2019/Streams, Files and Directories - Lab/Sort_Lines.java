import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class Sort_Lines {
    public static void main(String[] args) throws IOException {
        String basePath = "D:\\Software University\\Java Development\\Java Advanced\\" +
                "Streams, Files and Directories\\" +
                "04. Java-Advanced-Files-and-Streams-Lab-Resources";

        String inputPath = basePath + "\\input.txt";
        String outputPath = basePath + "\\06.SortLinesOutput.txt";

        Path filePath = Paths.get(inputPath);
        PrintWriter outputStream = null;

        try {
            outputStream = new PrintWriter(outputPath);

            List<String> allLines = Files.readAllLines(filePath);
            Collections.sort(allLines);
            for (String line : allLines) {
                outputStream.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
