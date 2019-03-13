package hotelreservation;

public class ReservationDetails {
    private double pricePerDay;
    private int numberOfDays;
    private Season season;
    private Discount discount;

    private ReservationDetails(double pricePerDay, int numberOfDays, Season season, Discount discount) {
        this.pricePerDay = pricePerDay;
        this.numberOfDays = numberOfDays;
        this.season = season;
        this.discount = discount;
    }

    public static ReservationDetails initialize(String[] args) {
        double pricePerDay = Double.parseDouble(args[0]);
        int numberOfDays = Integer.parseInt(args[1]);
        Season season = Season.valueOf(args[2]);
        Discount discount = Discount.valueOf(args[3]);

        return new ReservationDetails(pricePerDay, numberOfDays, season, discount);
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public Season getSeason() {
        return season;
    }

    public Discount getDiscount() {
        return discount;
    }
}
