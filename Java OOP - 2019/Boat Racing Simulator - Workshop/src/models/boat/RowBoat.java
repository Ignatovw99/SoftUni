package models.boat;

import models.Race;
import application.Validator;

public class RowBoat extends Boat {
    private int oars;

    public RowBoat(String model, int weight, int oars) {
        super(model, weight);
        this.setOars(oars);
    }

    private void setOars(int oars) {
        Validator.validateNumericalValue(oars, "Oars");
        this.oars = oars;
    }

    @Override
    public double calculateSpeed(Race race) {
        return (this.oars * 100.0) - this.getWeight() + race.getWaterCurrentSpeed();
    }
}
