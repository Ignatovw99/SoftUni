package carinfo;

public class Car {
    private String make;
    private String model;
    private int horsePower;

    private static final String UNKNOWN_VALUE = "unknown";
    private static final int DEFAULT_INTEGER_VALUE = -1;

    public Car(String make) {
        this.make = make;
        this.model = UNKNOWN_VALUE;
        this.horsePower = DEFAULT_INTEGER_VALUE;
    }

    public Car(String make, String model, int horsePower) {
        this(make);
        this.model = model;
        this.horsePower = horsePower;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public String getInfo() {
        return String.format("The car is: %s %s - %d HP.",
                this.getMake(), this.getModel(), this.getHorsePower());
    }
}
