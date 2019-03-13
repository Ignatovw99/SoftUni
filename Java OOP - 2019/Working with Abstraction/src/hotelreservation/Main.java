package hotelreservation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("\\s+");
        ReservationDetails reservation = ReservationDetails.initialize(input);
        double holidayPrice = PriceCalculator.calculate(reservation);
        System.out.printf("%.2f%n", holidayPrice);
    }
}
