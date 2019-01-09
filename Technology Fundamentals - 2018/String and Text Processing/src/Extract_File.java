import java.util.Scanner;

public class Extract_File {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        String fileName = getFileName(path);
        String fileExtension = getFileExtension(path);
        System.out.printf("File name: %s\n", fileName);
        System.out.printf("File extension: %s", fileExtension);
    }

    private static String getFileExtension(String path) {
        int beginIndex = path.lastIndexOf(".") + 1;
        return path.substring(beginIndex);
    }

    private static String getFileName(String path) {
        int beginIndex = path.lastIndexOf("\\") + 1;
        int endIndex = path.lastIndexOf(".");
        return path.substring(beginIndex, endIndex);
    }
}
