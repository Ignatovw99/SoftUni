import java.io.*;

public class Sum_Bytes {
    public static void main(String[] args) throws IOException {
        FileReader inputStream = new FileReader("input.txt");
        BufferedReader reader = new BufferedReader(inputStream);

        String line = reader.readLine();
        long totalSum = 0;
        while (line != null) {
            int sum = 0;
            for (int i = 0; i < line.length(); i++) {
                sum += line.charAt(i);
            }
            totalSum += sum;
            line = reader.readLine();
        }
        System.out.println(totalSum);
        inputStream.close();
    }
}
