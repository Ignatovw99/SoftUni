package animals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String type = reader.readLine();

        while (!"Beast!".equals(type)) {
            try {
                String[] animalTokens = reader.readLine().split("\\s+");
                Animal animal = AnimalFactory.getAnimal(type, animalTokens);
                System.out.println(animal.toString());
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
            type = reader.readLine();
        }
    }
}
