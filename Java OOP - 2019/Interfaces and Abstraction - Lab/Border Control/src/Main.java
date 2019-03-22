import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Identifiable> residents = new ArrayList<>();

        String input = reader.readLine();

        while (!"End".equals(input)) {
            String[] tokens = input.split("\\s+");

            Identifiable resident = null;
            if (tokens.length == 3) {
                resident = new Citizen(tokens[0], Integer.parseInt(tokens[1]), tokens[2]);
            } else {
                resident = new Robot(tokens[0], tokens[1]);
            }
            residents.add(resident);
            input = reader.readLine();
        }

        String fakeIdCharacterization = reader.readLine();

        residents.stream()
                .filter(r -> r.isRebel(fakeIdCharacterization))
                .forEach(r -> System.out.println(r.getId()));
    }
}
