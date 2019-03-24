import animalmodels.Animal;
import foodmodels.Food;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Animal> animals = new ArrayList<>();

        while (true) {
            String line = reader.readLine();
            if ("End".equals(line)) {
                break;
            }

            String[] tokens = line.split("\\s+");

            Animal animal = AnimalFactory.getAnimal(tokens[0], Arrays.stream(tokens).skip(1).toArray(String[]::new));
            System.out.println(animal.makeSound());
            animals.add(animal);

            tokens = reader.readLine().split("\\s+");
            Food food = FoodFactory.getFood(tokens[0], Integer.parseInt(tokens[1]));

            try {
                animal.eat(food);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }

        animals.forEach(animal -> System.out.println(animal.toString()));
    }
}
