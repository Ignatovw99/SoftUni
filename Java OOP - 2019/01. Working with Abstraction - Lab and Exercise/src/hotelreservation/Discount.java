package hotelreservation;

public enum Discount {
    VIP(20), SecondVisit(10), None(0);

    private int discount;

    Discount(int discount) {
        this.discount = discount;
    }

    public double getValue() {
        return this.discount / 100.0;
    }
}
