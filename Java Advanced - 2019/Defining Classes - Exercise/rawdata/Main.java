package rawdata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Car> cars = new ArrayList<>();

        int carsNumber = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < carsNumber; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");

            String model = tokens[0];
            String[] engineTokens = {tokens[1], tokens[2]};
            String[] cargoTokens = {tokens[3], tokens[4]};
            String[] tiresTokens = {tokens[5], tokens[6], tokens[7], tokens[8],
                                tokens[9], tokens[10], tokens[11], tokens[12]};

            Engine engine = initializeEngine(engineTokens);
            Cargo cargo = initializeCargo(cargoTokens);
            Tire[] tires = initializeFourTires(tiresTokens);

            cars.add(new Car(model, engine, cargo, tires));
        }

        String cargoCommand = scanner.nextLine();

        Predicate<Car> filterCarTires = car -> {
            Tire[] carTires = car.getTires();
            boolean isValidTirePressure = false;
            for (Tire carTire : carTires) {
                if (carTire.isPressureValid()) {
                    isValidTirePressure = true;
                }
            }
            return isValidTirePressure;
        };

        Predicate<Car> filterCarEnginePower = car -> car.getEngine().getPower() > 250;

        Predicate<Car> finalFilterPredicate;

        if (cargoCommand.equals("fragile")) {
            finalFilterPredicate = filterCarTires;
        } else {
            finalFilterPredicate = filterCarEnginePower;
        }

        cars.stream()
                .filter(car -> car.getCargo().getType().equals(cargoCommand))
                .filter(finalFilterPredicate)
                .forEach(System.out::println);

    }

    private static Tire[] initializeFourTires(String[] tiresTokens) {
        Tire[] tires = new Tire[4];
        double pressure;
        int age;

        for (int i = 0; i < tiresTokens.length; i += 2) {
            pressure = Double.parseDouble(tiresTokens[i]);
            age = Integer.parseInt(tiresTokens[i + 1]);
            tires[i / 2] = new Tire(pressure, age);
        }

        return tires;
    }

    private static Cargo initializeCargo(String[] cargoTokens) {
        int weight = Integer.parseInt(cargoTokens[0]);
        String type = cargoTokens[1];
        return new Cargo(weight, type);
    }

    private static Engine initializeEngine(String[] engineTokens) {
        int speed = Integer.parseInt(engineTokens[0]);
        int power = Integer.parseInt(engineTokens[1]);
        return new Engine(speed, power);
    }
}
