import java.io.*;

public class Merge_Two_Files {
    public static void main(String[] args) throws IOException {
        FileReader firstFile = new FileReader("inputOne.txt");
        FileReader secondFile = new FileReader("inputTwo.txt");
        BufferedReader reader = new BufferedReader(firstFile);
        FileWriter writer = new FileWriter("output.txt");

        mergeFiles(reader, secondFile, writer);
        firstFile.close();
        secondFile.close();
        writer.close();
        reader.close();
    }

    private static void mergeFiles(BufferedReader reader, FileReader secondFile, FileWriter writer) throws IOException {
        printFirstFile(reader, writer);
        reader = new BufferedReader(secondFile);
        printSecondFile(reader, writer);
    }

    private static void printSecondFile(BufferedReader reader, FileWriter writer) throws IOException {
        printFile(reader, writer);
    }

    private static void printFirstFile(BufferedReader reader, FileWriter writer) throws IOException {
        printFile(reader, writer);
    }

    private static void printFile(BufferedReader reader, FileWriter writer) throws IOException {
        String line = reader.readLine();
        while (line != null) {
            writer.write(line + System.lineSeparator());
            line = reader.readLine();
        }
    }
}
