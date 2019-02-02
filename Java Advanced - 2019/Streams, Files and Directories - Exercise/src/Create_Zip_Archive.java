import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Create_Zip_Archive {
    public static void main(String[] args) throws IOException {
        FileOutputStream outputStream = new FileOutputStream("files.zip");
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);

        File file = new File("Exercises Resources");
        File[] filesToZip = file.listFiles();

        for (int i = 0; i < filesToZip.length; i++) {
            File currentFile = filesToZip[i];
            if (!currentFile.isDirectory()) {
                FileInputStream inputStream = new FileInputStream(currentFile.getPath());
                zipOutputStream.putNextEntry(new ZipEntry(currentFile.getName()));
                zipOutputStream.write(inputStream.readAllBytes());
                inputStream.close();
            }
        }
        zipOutputStream.close();
    }
}
