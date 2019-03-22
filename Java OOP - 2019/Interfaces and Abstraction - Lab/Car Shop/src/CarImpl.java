public abstract class CarImpl implements Car {
    private String model;
    private String color;
    private int horsePower;
    private String countryProduced;

    protected CarImpl(String model, String color, Integer horsePower, String countryProduced) {
        this.model = model;
        this.color = color;
        this.horsePower = horsePower;
        this.countryProduced = countryProduced;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public Integer getHorsePower() {
        return this.horsePower;
    }

    public String getCountryProduced() {
        return this.countryProduced;
    }

    @Override
    public String toString() {
        return String.format(
                "%s is %s color and have %s horse power%n" +
                        "This is %s produced in %s and have %d tires",
                this.getModel(),
                this.getColor(),
                this.getHorsePower(),
                this.getModel(),
                this.getCountryProduced(),
                Car.TIRES);
    }
}
