package hotelreservation;

public class PriceCalculator {
    public static double calculate(ReservationDetails reservation) {
        double price = reservation.getPricePerDay() * reservation.getNumberOfDays() * reservation.getSeason().getValue();
        double discountValue = price * reservation.getDiscount().getValue();
        return price - discountValue;
    }
}
