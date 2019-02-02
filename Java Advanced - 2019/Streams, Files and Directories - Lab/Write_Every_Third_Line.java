import java.io.*;

public class Write_Every_Third_Line {
    public static void main(String[] args) throws IOException {
        String basePath = "D:\\Software University\\Java Development\\Java Advanced\\" +
                "Streams, Files and Directories\\" +
                "04. Java-Advanced-Files-and-Streams-Lab-Resources";

        String inputPath = basePath + "\\input.txt";
        String outputPath = basePath + "\\05.WriteEveryThirdLineOutput.txt";

        BufferedReader bufferedReader = null;
        PrintWriter writer = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(inputPath));
            writer = new PrintWriter(new FileWriter(outputPath));

            String line = bufferedReader.readLine();
            int row = 1;
            while (line != null) {
                if (row % 3 == 0) {
                    writer.println(line);
                }
                line = bufferedReader.readLine();
                row++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
    }
}
