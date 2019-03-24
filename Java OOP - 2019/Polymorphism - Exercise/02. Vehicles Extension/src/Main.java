import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Vehicle> vehicles = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            String[] vehicleTokens = reader.readLine().split("\\s+");

            double fuelQuantity = Double.parseDouble(vehicleTokens[1]);
            double fuelConsumption = Double.parseDouble(vehicleTokens[2]);
            double tankCapacity = Double.parseDouble(vehicleTokens[3]);

            switch (vehicleTokens[0]) {
                case "Car":
                    vehicles.add(new Car(fuelQuantity, fuelConsumption, tankCapacity));
                    break;
                case "Truck":
                    vehicles.add(new Truck(fuelQuantity, fuelConsumption, tankCapacity));
                    break;
                case "Bus":
                    vehicles.add(new Bus(fuelQuantity, fuelConsumption, tankCapacity));
                    break;
            }
        }

        int numberOfLines = Integer.parseInt(reader.readLine());

        for (int i = 0; i < numberOfLines; i++) {
            String[] tokens = reader.readLine().split("\\s+");

            String command = tokens[0];
            String vehicleString = tokens[1];

            try {
                switch (command) {
                    case "Drive":
                        for (Vehicle vehicle : vehicles) {
                            if (vehicleString.equals(vehicle.getClass().getSimpleName())) {
                                System.out.println(vehicle.drive(Double.parseDouble(tokens[2])));
                                break;
                            }
                        }
                        break;
                    case "Refuel":
                        for (Vehicle vehicle : vehicles) {
                            if (vehicleString.equals(vehicle.getClass().getSimpleName())) {
                                vehicle.refuel(Double.parseDouble(tokens[2]));
                                break;
                            }
                        }
                        break;
                    case "DriveEmpty":
                        for (Vehicle vehicle : vehicles) {
                            if (vehicle instanceof Bus) {
                                System.out.println(((Bus) vehicle).driveEmpty(Double.parseDouble(tokens[2])));
                            }
                        }
                        break;
                }
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.toString());
        }
    }
}
