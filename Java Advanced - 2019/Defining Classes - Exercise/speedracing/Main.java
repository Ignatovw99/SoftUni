package speedracing;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedHashMap<String, Car> cars = new LinkedHashMap<>();

        int carsCount = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < carsCount; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String model = tokens[0];
            int fuelAmount = Integer.parseInt(tokens[1]);
            double fuelCostForOneKm = Double.parseDouble(tokens[2]);

            cars.put(model, new Car(model, fuelAmount, fuelCostForOneKm));
        }

        String[] lineArguments = scanner.nextLine().split("\\s+");
        String command = lineArguments[0];

        while (!"End".equals(command)) {
            String carModel = lineArguments[1];
            int amountOfKm = Integer.parseInt(lineArguments[2]);

            if (cars.containsKey(carModel)) {
                Car currentCar = cars.get(carModel);

                if (currentCar.canTravelDistance(amountOfKm)) {
                    currentCar.drive(amountOfKm);
                } else {
                    System.out.println("Insufficient fuel for the drive");
                }
            }

            lineArguments = scanner.nextLine().split("\\s+");
            command = lineArguments[0];
        }

        cars.keySet().stream()
                .forEach(car -> System.out.println(cars.get(car).getInfo()));
    }
}
