package models.boat;

import models.engine.Engine;

public abstract class Motorboat extends Boat {
    private Engine[] engines;

    public Motorboat(String model, int weight, Engine... engines) {
        super(model, weight);
        this.engines = engines;
    }

    public Engine[] getEngines() {
        return this.engines;
    }
}
