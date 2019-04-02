package models.boat;

import application.Constants;
import models.Race;

public class SailBoat extends Boat {
    private int sailEfficiency;

    public SailBoat(String model, int weight, int sailEfficiency) {
        super(model, weight);
        this.setSailEfficiency(sailEfficiency);
    }

    private void setSailEfficiency(int sailEfficiency) {
        if (sailEfficiency < 1 || sailEfficiency > 100) {
            throw new IllegalArgumentException(Constants.IncorrectSailEfficiencyMessage);
        }
        this.sailEfficiency = sailEfficiency;
    }

    @Override
    public double calculateSpeed(Race race) {
        return (race.getWindSpeed() * (this.sailEfficiency / 100.0)) - this.getWeight() + (race.getWaterCurrentSpeed() / 2.0);
    }
}
