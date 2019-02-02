import java.io.FileInputStream;
import java.io.IOException;

public class Read_File {
    public static void main(String[] args) throws IOException{
        String path = "D:\\Software University\\Java Development\\Java Advanced\\" +
                "Streams, Files and Directories\\" +
                "04. Java-Advanced-Files-and-Streams-Lab-Resources\\input.txt";

        FileInputStream inputStream = null;

        try {
            inputStream = new FileInputStream(path);
            int byteData = inputStream.read();

            while (byteData >= 0) {
                System.out.printf("%s ", Integer.toBinaryString(byteData));
                byteData = inputStream.read();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
}
