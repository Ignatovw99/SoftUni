package teamTask;

import personTask.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Team team = new Team("West Ham");

        int peopleCount = Integer.parseInt(reader.readLine());

        for (int i = 0; i < peopleCount; i++) {
            String[] inputTokens = reader.readLine().split("\\s+");
            try {
                Person person = new Person(inputTokens[0], inputTokens[1],
                        Integer.parseInt(inputTokens[2]), Double.parseDouble(inputTokens[3]));
                team.addPlayer(person);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }

        System.out.println(team.toString());
    }
}
