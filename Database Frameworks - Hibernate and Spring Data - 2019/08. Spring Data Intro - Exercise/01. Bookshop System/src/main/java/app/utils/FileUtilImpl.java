package app.utils;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static app.constants.Global.RESOURCES_PATH;

@Component
public class FileUtilImpl implements FileUtil {

    @Override
    public String[] getFileContent(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(RESOURCES_PATH + path)
        ));

        List<String> fileLines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            if (!line.isEmpty()) {
                fileLines.add(line);
            }
        }

        return fileLines
                .toArray(String[]::new);
    }
}
