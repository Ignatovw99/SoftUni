package mostwanted.util;

import java.io.*;

public class FileUtilImpl implements FileUtil {

    @Override
    public String readFile(String filePath) {
        StringBuilder lines = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                lines.append(line)
                        .append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines.toString().trim();
    }
}