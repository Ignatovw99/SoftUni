import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Copy_Bytes {
    public static void main(String[] args) throws IOException {
        String basePath = "D:\\Software University\\Java Development\\Java Advanced\\" +
                "Streams, Files and Directories\\" +
                "04. Java-Advanced-Files-and-Streams-Lab-Resources";

        String inputPath = basePath + "\\input.txt";
        String outputPath = basePath + "\\03.CopyBytesOutput.txt";

        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;

        try {
            inputStream = new FileInputStream(inputPath);
            outputStream = new FileOutputStream(outputPath);

            int byteData = inputStream.read();
            while (byteData >= 0) {
                if (byteData == ' ' || byteData == '\n') {
                    outputStream.write(byteData);
                } else {
                    String number = String.valueOf(byteData);
                    for (int i = 0; i < number.length(); i++) {
                        outputStream.write(number.charAt(i));
                    }
                }
                byteData = inputStream.read();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
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
