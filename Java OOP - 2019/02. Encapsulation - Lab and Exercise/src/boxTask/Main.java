package boxTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int length = Integer.parseInt(reader.readLine());
        int width = Integer.parseInt(reader.readLine());
        int height = Integer.parseInt(reader.readLine());

        try {
            Box box = new Box(length, width, height);
            System.out.println(box.toString());
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
