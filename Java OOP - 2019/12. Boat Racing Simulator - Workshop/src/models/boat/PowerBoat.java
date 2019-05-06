package models.boat;

import models.Race;
import models.engine.Engine;

public class PowerBoat extends Motorboat {
    public PowerBoat(String model, int weight, Engine firstEngine, Engine secondEngine) {
        super(model, weight, firstEngine, secondEngine);
    }

    @Override
    public double calculateSpeed(Race race) {
        return (this.getEngines()[0].getOutput() + this.getEngines()[1].getOutput()) - this.getWeight() +
                (race.getWaterCurrentSpeed() / 5.0);
    }
}
