import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Get_Folder_Size {
    public static void main(String[] args) throws IOException {
        String path = "Exercises Resources";
        File file = new File(path);

        File[] files = file.listFiles();
        FileWriter writer = new FileWriter("folder size.txt");

        int size = 0;
        for (File currentFile : files) {
            if (currentFile.isFile()) {
                size += currentFile.length();
            }
        }
        writer.write("Folder size : ");
        writer.write(String.valueOf(size));
        writer.close();
    }
}
