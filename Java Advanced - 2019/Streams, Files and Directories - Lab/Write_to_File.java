import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Write_to_File {
    public static void main(String[] args) throws IOException {
        String basePath = "D:\\Software University\\Java Development\\Java Advanced\\" +
                "Streams, Files and Directories\\" +
                "04. Java-Advanced-Files-and-Streams-Lab-Resources";

        String inputPath = basePath + "\\input.txt";
        String outputPath = basePath + "\\02.WriteToFileOutput.txt";

        List<Character> punctuation = Arrays.asList(',', '.', '?', '!');

        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(inputPath);
            outputStream = new FileOutputStream(outputPath);

            int byteData = inputStream.read();
            while (byteData >= 0) {
                if (!punctuation.contains((char)byteData))
                outputStream.write(byteData);
                byteData = inputStream.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
