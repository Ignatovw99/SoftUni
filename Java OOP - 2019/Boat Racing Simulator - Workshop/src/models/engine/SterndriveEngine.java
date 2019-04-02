package models.engine;

public class SterndriveEngine extends Engine {
    public SterndriveEngine(String model, int horsepower, int displacement) {
        super(model, horsepower, displacement);
    }

    @Override
    public int getOutput() {
        return this.getHorsepower() * 7 + this.getDisplacement();
    }
}
