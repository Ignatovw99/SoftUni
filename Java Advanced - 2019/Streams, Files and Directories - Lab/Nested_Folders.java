import java.io.File;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;

public class Nested_Folders {
    public static void main(String[] args) {
        String path = "D:\\Software University\\Java Development\\Java Advanced\\" +
                "Streams, Files and Directories\\" +
                "04. Java-Advanced-Files-and-Streams-Lab-Resources\\" +
                "Files-and-Streams";

        File inputFile = new File(path);
        ArrayDeque<File> filesToTraverse = new ArrayDeque<>(Collections.singletonList(inputFile));
        int folderCounter = 0;

        while (!filesToTraverse.isEmpty()) {
            File currentFile = filesToTraverse.poll();
            if (currentFile.isDirectory()) {
                folderCounter++;
                filesToTraverse.addAll(Arrays.asList(currentFile.listFiles()));

                System.out.println(currentFile.getName());
            }
        }
        System.out.println(folderCounter + " folders");
    }
}
