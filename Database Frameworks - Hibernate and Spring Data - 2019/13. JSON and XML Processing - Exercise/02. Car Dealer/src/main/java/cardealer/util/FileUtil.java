package cardealer.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public final class FileUtil {

    private FileUtil() {
    }

    public static String readFile(String path) {
        StringBuilder builderLines = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader.lines()
                    .forEach(builderLines::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builderLines.toString();
    }

    public static void writeToFile(String text, String path) {
        File file = new File(path);
        try {
            boolean isFileCreated = file.createNewFile();
            if (isFileCreated) {
                System.out.println(String.format("A file with name \"%s\" was created", path.substring(path.lastIndexOf("\\") + 1)));
            }
            Files.write(Path.of(path), text.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
