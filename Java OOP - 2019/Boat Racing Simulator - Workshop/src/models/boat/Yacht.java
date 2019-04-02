package models.boat;

import models.Race;
import models.engine.Engine;
import application.Validator;

public class Yacht extends Motorboat {
    private int cargo;

    public Yacht(String model, int weight, Engine engine, int cargo) {
        super(model, weight, engine);
        this.setCargo(cargo);
    }

    private void setCargo(int cargo) {
        Validator.validateNumericalValue(cargo, "Cargo Weight");
        this.cargo = cargo;
    }

    @Override
    public double calculateSpeed(Race race) {
        return this.getEngines()[0].getOutput() - (this.getWeight() + cargo) + (race.getWaterCurrentSpeed() / 2.0);
    }
}
