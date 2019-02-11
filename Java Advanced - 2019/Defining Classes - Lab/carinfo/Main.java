package carinfo;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int carNumber = Integer.parseInt(scanner.nextLine());

        ArrayList<Car> cars = new ArrayList<>();

        for (int i = 0; i < carNumber; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String make = tokens[0];
            Car car;
            if (tokens.length == 3) {
                String model = tokens[1];
                int horsePower = Integer.parseInt(tokens[2]);
                car = new Car(make, model, horsePower);
            } else {
                car = new Car(make);
            }

            cars.add(car);
        }

        cars
                .forEach(car -> System.out.println(car.getInfo()));
    }
}
