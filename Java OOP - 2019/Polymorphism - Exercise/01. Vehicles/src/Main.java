import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] carTokens = reader.readLine().split("\\s+");
        Car car = new Car(Double.parseDouble(carTokens[1]), Double.parseDouble(carTokens[2]));

        String[] truckTokens = reader.readLine().split("\\s+");
        Truck truck = new Truck(Double.parseDouble(truckTokens[1]), Double.parseDouble(truckTokens[2]));

        int numberOfLines = Integer.parseInt(reader.readLine());

        for (int i = 0; i < numberOfLines; i++) {
            String[] tokens = reader.readLine().split("\\s+");

            String command = tokens[0];
            String vehicle = tokens[1];

            switch (command) {
                case "Drive":
                    if (vehicle.equals("Car")) {
                        System.out.println(car.drive(Double.parseDouble(tokens[2])));
                    } else {
                        System.out.println(truck.drive(Double.parseDouble(tokens[2])));
                    }
                    break;
                case "Refuel":
                    if (vehicle.equals("Car")) {
                        car.refuel(Double.parseDouble(tokens[2]));
                    } else {
                        truck.refuel(Double.parseDouble(tokens[2]));
                    }
                    break;
            }
        }
        System.out.println(car.toString());
        System.out.println(truck.toString());
    }
}
