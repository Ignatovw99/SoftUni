package rawdata;

public class Engine {
    private int speed;
    private int power;

    public Engine(int speed, int power) {
        this.speed = speed;
        this.power = power;
    }

    public Engine(Engine engine) {
        this.speed = engine.speed;
        this.power = engine.power;
    }

    public int getPower() {
        return this.power;
    }
}
