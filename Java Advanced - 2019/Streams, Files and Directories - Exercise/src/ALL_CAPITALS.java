import java.io.*;

public class ALL_CAPITALS {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = null;
        PrintWriter writer = null;
        try {
            fileReader = new FileReader("input.txt");
            BufferedReader reader = new BufferedReader(fileReader);
            writer = new PrintWriter("output.txt");

            String line = reader.readLine();

            while (line != null) {
                writer.println(line.toUpperCase());
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null) {
                fileReader.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
    }
}
