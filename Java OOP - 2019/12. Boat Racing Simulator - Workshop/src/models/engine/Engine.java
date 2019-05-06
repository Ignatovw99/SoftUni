package models.engine;

import contracts.Modelable;
import application.Validator;

import java.util.Objects;

public abstract class Engine implements Modelable {
    private String model;
    private int horsepower;
    private int displacement;

    protected Engine(String model, int horsepower, int displacement) {
        this.setModel(model);
        this.setHorsepower(horsepower);
        this.setDisplacement(displacement);
    }

    @Override
    public String getModel() {
        return this.model;
    }

    private void setModel(String model) {
        Validator.validateStringValue(model, 3);
        this.model = model;
    }

    public int getHorsepower() {
        return this.horsepower;
    }

    private void setHorsepower(int horsepower) {
        Validator.validateNumericalValue(horsepower, "Horsepower");
        this.horsepower = horsepower;
    }

    public int getDisplacement() {
        return this.displacement;
    }

    private void setDisplacement(int displacement) {
        Validator.validateNumericalValue(displacement, "Displacement");
        this.displacement = displacement;
    }

    public abstract int getOutput();

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Engine)) return false;
        Engine engine = (Engine) other;
        return model.equals(engine.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model);
    }
}
