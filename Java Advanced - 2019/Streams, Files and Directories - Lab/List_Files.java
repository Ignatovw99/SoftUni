import java.io.File;

public class List_Files {
    public static void main(String[] args) {
        String basePath = "D:\\Software University\\Java Development\\Java Advanced\\" +
                "Streams, Files and Directories\\" +
                "04. Java-Advanced-Files-and-Streams-Lab-Resources";

        String inputPath = basePath + "\\Files-and-Streams";

        File inputFile = new File(inputPath);

        File[] allFiles = inputFile.listFiles();

        for (File file : allFiles) {
            if (file.isFile()) {
                String fileName = file.getName();
                long fileSize = file.length();
                System.out.println(String.format("%s: [%d]", fileName, fileSize));
            }
        }
    }
}
