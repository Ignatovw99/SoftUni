import java.io.*;
import java.util.Scanner;

public class Extract_Integers {
    public static void main(String[] args) throws IOException {
        String basePath = "D:\\Software University\\Java Development\\Java Advanced\\" +
                "Streams, Files and Directories\\" +
                "04. Java-Advanced-Files-and-Streams-Lab-Resources";

        String inputPath = basePath + "\\input.txt";
        String outputPath = basePath + "\\04.ExtractIntegersOutput.txt";

        Scanner scanner = null;
        PrintWriter writer = null;

        try {
            scanner = new Scanner(new FileInputStream(inputPath));
            writer = new PrintWriter(new FileOutputStream(outputPath));

            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    writer.println(scanner.nextInt());
                }

                scanner.next();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (scanner != null) {
                scanner.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
    }
}
