package models.boat;

import application.Constants;
import contracts.Modelable;
import models.Race;
import application.Validator;

import java.util.Objects;

public abstract class Boat implements Modelable {
    private String model;
    private int weight;

    protected Boat(String model, int weight) {
        this.setModel(model);
        this.setWeight(weight);
    }

    @Override
    public String getModel() {
        return this.model;
    }

    private void setModel(String model) {
        Validator.validateStringValue(model, 5);
        this.model = model;
    }

    public int getWeight() {
        return this.weight;
    }

    private void setWeight(int weight) {
        Validator.validateNumericalValue(weight, "Weight");
        this.weight = weight;
    }

    public abstract double calculateSpeed(Race race);

    public String getInfo(Race race) {
        double raceTime = race.getDistance() / this.calculateSpeed(race);

        return String.format("%s Model: %s Time: %s",
                this.getClass().getSimpleName(),
                this.getModel(),
                raceTime <= 0 ? "Did not finish!" : String.format("%.2f sec", raceTime));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Boat)) return false;
        Boat boat = (Boat) other;
        return model.equals(boat.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model);
    }
}
