package carsalesman;

public class Engine {
    private final static String DEFAULT_STATE_VALUE = "n/a";
    private final static int DEFAULT_DISPLACEMENT_VALUE = -1;
    private final static String DEFAULT_EFFICIENCY_VALUE = "!";

    private String model;
    private int power;
    private int displacement;
    private String efficiency;

    public Engine(String model, int power) {
        this.model = model;
        this.power = power;
        this.displacement = DEFAULT_DISPLACEMENT_VALUE;
        this.efficiency = DEFAULT_EFFICIENCY_VALUE;
    }

    public Engine(String model, int power, int displacement) {
        this(model, power);
        this.displacement = displacement;
    }

    public Engine(String model, int power, String efficiency) {
        this(model, power);
        this.efficiency = efficiency;
    }

    public Engine(String model, int power, int displacement, String efficiency) {
        this(model, power, displacement);
        this.efficiency = efficiency;
    }

    public String getModel() {
        return this.model;
    }

    @Override
    public String toString() {
        return String.format("%s:%nPower: %d%nDisplacement: %s%nEfficiency: %s%n",
                this.model,
                this.power,
                this.displacement == DEFAULT_DISPLACEMENT_VALUE ? DEFAULT_STATE_VALUE : String.valueOf(this.displacement),
                this.efficiency.equals(DEFAULT_EFFICIENCY_VALUE) ? DEFAULT_STATE_VALUE : this.efficiency);
    }
}
