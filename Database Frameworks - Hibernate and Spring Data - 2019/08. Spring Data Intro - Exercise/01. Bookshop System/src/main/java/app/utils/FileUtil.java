package app.utils;

import java.io.IOException;

public interface FileUtil {

    String[] getFileContent(String path) throws IOException;
}
