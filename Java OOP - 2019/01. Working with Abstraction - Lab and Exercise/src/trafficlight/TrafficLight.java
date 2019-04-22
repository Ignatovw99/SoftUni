package trafficlight;

public class TrafficLight {
    private Light light;

    public TrafficLight(Light light) {
        this.light = light;
    }

    public void update() {
        switch (this.light) {
            case RED:
                this.light = Light.GREEN;
                break;
            case YELLOW:
                this.light = Light.RED;
                break;
            case GREEN:
                this.light = Light.YELLOW;
        }
    }

    @Override
    public String toString() {
        return this.light.toString();
    }
}
