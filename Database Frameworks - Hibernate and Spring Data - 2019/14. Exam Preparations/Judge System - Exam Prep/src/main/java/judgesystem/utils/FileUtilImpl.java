package judgesystem.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtilImpl implements FileUtil {

    @Override
    public String readFile(String filePath) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return String.join(System.lineSeparator(), lines);
    }
}
