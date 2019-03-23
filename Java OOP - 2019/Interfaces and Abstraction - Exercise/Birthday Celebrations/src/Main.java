import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Birthable> residentsWithBirthDay = new ArrayList<>();
        ArrayList<Identifiable> residentsWithId = new ArrayList<>();

        String input = reader.readLine();
        while (!"End".equals(input)) {
            String[] tokens = input.split("\\s+");
            String type = tokens[0];

            Identifiable resident;
            Birthable residentWithBirthDay;
            switch (type.toLowerCase()) {
                case "citizen":
                    residentWithBirthDay = new Citizen(tokens[1], Integer.parseInt(tokens[2]), tokens[3], tokens[4]);
                    residentsWithBirthDay.add(residentWithBirthDay);
                    break;
                case "pet":
                    residentWithBirthDay = new Pet(tokens[1], tokens[2]);
                    residentsWithBirthDay.add(residentWithBirthDay);
                    break;
                    default:
                        resident = new Robot(tokens[1], tokens[2]);
                        residentsWithId.add(resident);
                        break;
            }

            input = reader.readLine();
        }
        String year = reader.readLine().trim();

        residentsWithBirthDay.stream()
                .filter(r -> r.isBornInThisYear(year))
                .forEach(r -> System.out.println(r.getBirthDate()));
    }
}
