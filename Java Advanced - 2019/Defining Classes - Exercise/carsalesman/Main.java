package carsalesman;

import com.sun.jdi.ArrayReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Engine> engines = new ArrayList<>();

        int enginesCount = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < enginesCount; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");

            switch (tokens.length) {
                case 2:
                    engines.add(new Engine(tokens[0], Integer.parseInt(tokens[1])));
                    break;
                case 3:
                    if (tryParse(tokens[2])) {
                        engines.add(new Engine(tokens[0], Integer.parseInt(tokens[1]),
                                Integer.parseInt(tokens[2])));
                    } else {
                        engines.add(new Engine(tokens[0], Integer.parseInt(tokens[1]),
                                tokens[2]));
                    }
                    break;
                case 4:
                    engines.add(new Engine(tokens[0], Integer.parseInt(tokens[1]),
                            Integer.parseInt(tokens[2]),
                            tokens[3]));
                    break;
            }
        }

        int carsCount = Integer.parseInt(scanner.nextLine());
        ArrayList<Car> cars = new ArrayList<>();

        for (int i = 0; i < carsCount; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");

            Engine engine = null;
            for (Engine currentEngine : engines) {
                if (currentEngine.getModel().equals(tokens[1])) {
                    engine = engines.get(engines.indexOf(currentEngine));
                }
            }

            switch (tokens.length) {
                case 2:
                    cars.add(new Car(tokens[0], engine));
                    break;
                case 3:
                    if (tryParse(tokens[2])) {
                        cars.add(new Car(tokens[0], engine, Integer.parseInt(tokens[2])));
                    } else {
                        cars.add(new Car(tokens[0], engine, tokens[2]));
                    }
                    break;
                case 4:
                    cars.add(new Car(tokens[0], engine, Integer.parseInt(tokens[2]), tokens[3]));
                    break;
            }
        }

        cars
                .forEach(System.out::println);
    }

    private static boolean tryParse(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
