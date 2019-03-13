package personTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        List<Person> people = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] inputTokens = reader.readLine().split("\\s+");
            try {
                people.add(new Person(inputTokens[0], inputTokens[1],
                        Integer.parseInt(inputTokens[2]), Double.parseDouble(inputTokens[3])));
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }

        double bonus = Double.parseDouble(reader.readLine());

        for (Person person : people) {
            person.increaseSalary(bonus);
            System.out.println(person.toString());
        }
    }
}
