import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Buyer> buyers = new HashMap<>();

        int numberOfPeople = Integer.parseInt(reader.readLine());

        for (int i = 0; i < numberOfPeople; i++) {
            String[] tokens = reader.readLine().split("\\s+");

            if (buyers.containsKey(tokens[0])) {
                continue;
            }
            Buyer buyer = null;
            if (tokens.length == 4) {
                buyer = new Citizen(tokens[0], Integer.parseInt(tokens[1]), tokens[2], tokens[3]);
            } else {
                buyer = new Rebel(tokens[0], Integer.parseInt(tokens[1]), tokens[2]);
            }
            buyers.putIfAbsent(tokens[0], buyer);
        }

        String name = reader.readLine();

        while (!"End".equals(name)) {
            if (buyers.containsKey(name)) {
                buyers.get(name).buyFood();
            }
            name = reader.readLine();
        }

        int totalCost = buyers.values().stream()
                .mapToInt(Buyer::getFood)
                .sum();

        System.out.println(totalCost);
    }
}
