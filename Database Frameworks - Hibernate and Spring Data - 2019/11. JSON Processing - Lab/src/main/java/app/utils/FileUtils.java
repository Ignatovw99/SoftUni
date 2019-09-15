package app.utils;

import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public final class FileUtils {

    private FileUtils(){
    }

    public static String readFile(String path) {
        StringBuilder lines = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(path)))){
            reader.lines()
                    .forEach(lines::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines.toString();
    }

    public static void writeToFile(String json, String path) {
        try {
            Files.write(
                    Path.of(path),
                    json.getBytes(StandardCharsets.UTF_8)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
