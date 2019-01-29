import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Parking_Lot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        Set<String> parkingLot = new HashSet<>();

        while (!"END".equals(command)) {
            String[] tokens = command.split(", ");
            String direction = tokens[0];
            String carNumber = tokens[1];

            if (direction.equals("IN")) {
                parkingLot.add(carNumber);
            } else if (direction.equals("OUT")) {
                parkingLot.remove(carNumber);
            }
            command = scanner.nextLine();
        }

        if (!parkingLot.isEmpty()) {
            for (String carNumber : parkingLot) {
                System.out.println(carNumber);
            }
        } else {
            System.out.println("Parking Lot is Empty");
        }
    }
}
