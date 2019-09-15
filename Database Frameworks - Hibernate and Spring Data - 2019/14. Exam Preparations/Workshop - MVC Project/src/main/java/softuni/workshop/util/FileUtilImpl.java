package softuni.workshop.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtilImpl implements FileUtil {

    @Override
    public String readFile(String path) {
        StringBuilder fileLines = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileLines.append(line)
                        .append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileLines.toString();
    }

    @Override
    public void writeToFile(String content, String path) {
        File file = new File(path);
        try {
            boolean isFileCreated = file.createNewFile();
            Files.writeString(Path.of(path), content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
