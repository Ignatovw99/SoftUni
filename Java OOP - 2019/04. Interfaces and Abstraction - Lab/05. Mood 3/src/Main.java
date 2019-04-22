import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = reader.readLine().split(" \\| ");

        String username = tokens[0];
        String type = tokens[1];
        int level = Integer.parseInt(tokens[3]);

        Character hero;
        if (type.equals("Demon")) {
            hero = new Demon(username, level, Double.parseDouble(tokens[2]));
        } else {
            hero = new Archangel(username, level, Integer.parseInt(tokens[2]));
        }

        System.out.println(hero.toString());
    }
}
