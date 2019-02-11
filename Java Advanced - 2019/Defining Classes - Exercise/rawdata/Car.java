package rawdata;

public class Car {
    private String model;
    private Engine engine;
    private Cargo cargo;
    private Tire[] tires;

    public Car(String model, Engine engine, Cargo cargo, Tire[] tires) {
        this.model = model;
        this.engine = new Engine(engine);
        this.cargo = new Cargo(cargo);
        this.tires = Tire.initializeAllTires(tires);
    }

    public Cargo getCargo() {
        return this.cargo;
    }

    public Tire[] getTires() {
        return this.tires;
    }
    public Engine getEngine() {
        return this.engine;
    }

    @Override
    public String toString() {
        return this.model;
    }
}
