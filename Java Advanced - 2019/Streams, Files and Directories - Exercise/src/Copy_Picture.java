import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Copy_Picture {
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("java.jpg");
        byte[] bytesBuffer = inputStream.readAllBytes();
        FileOutputStream outputStream = new FileOutputStream("copy-picture.jpg");

        outputStream.write(bytesBuffer);
        outputStream.close();
    }
}
