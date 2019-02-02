import java.io.*;

public class Line_Numbers {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("inputLineNumbers.txt");
        BufferedReader reader = new BufferedReader(fileReader);
        FileWriter writer = new FileWriter("output.txt");

        int lineCounter = 1;
        String line = reader.readLine();

        while (line != null) {
            writer.write(String.format("%d. %s", lineCounter, line) + System.lineSeparator());
            lineCounter++;
            line = reader.readLine();
        }
        fileReader.close();
        writer.close();
    }
}
