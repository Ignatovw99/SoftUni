package softuni.workshop.util;

public interface FileUtil {

    String readFile(String path);

    void writeToFile(String content, String path);
}
