package carsalesman;

public class Car {
    private final static String DEFAULT_STATE_VALUE = "n/a";
    private final static int DEFAULT_WEIGHT_VALUE = -1;

    private String model;
    private Engine engine;
    private int weight;
    private String color;

    public Car(String model, Engine engine) {
        this.model = model;
        this.engine = engine;
        this.weight = DEFAULT_WEIGHT_VALUE;
        this.color = DEFAULT_STATE_VALUE;
    }

    public Car(String model, Engine engine, int weight) {
        this(model, engine);
        this.weight = weight;
    }

    public Car(String model, Engine engine, String color) {
        this(model, engine);
        this.color = color;
    }

    public Car(String model, Engine engine, int weight, String color) {
        this(model, engine, weight);
        this.color = color;
    }

    @Override
    public String toString() {
        return String.format("%s:%n%sWeight: %s%nColor: %s", this.model,
                this.engine,
                this.weight == DEFAULT_WEIGHT_VALUE ? DEFAULT_STATE_VALUE : String.valueOf(this.weight),
                this.color);
    }
}
