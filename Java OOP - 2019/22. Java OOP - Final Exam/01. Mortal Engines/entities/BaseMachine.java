package entities;

import entities.interfaces.Machine;
import entities.interfaces.Pilot;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMachine implements Machine {
    private String name;
    private Pilot pilot;
    private double attackPoints;
    private double defensePoints;
    private double healthPoints;
    private List<String> targets;

    protected BaseMachine(String name, double attackPoints, double defensePoints, double healthPoints) {
        this.setName(name);
        this.attackPoints = attackPoints;
        this.defensePoints = defensePoints;
        this.setHealthPoints(healthPoints);
        this.targets = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Machine name cannot be null or empty.");
        }
        this.name = name;
    }

    @Override
    public Pilot getPilot() {
        return this.pilot;
    }

    @Override
    public void setPilot(Pilot pilot) {
        if (pilot == null) {
            throw new NullPointerException("Pilot cannot be null.");
        }
        this.pilot = pilot;
    }

    @Override
    public double getHealthPoints() {
        return this.healthPoints;
    }

    @Override
    public void setHealthPoints(double healthPoints) {
        this.healthPoints = healthPoints;
    }

    @Override
    public double getAttackPoints() {
        return this.attackPoints;
    }

    @Override
    public double getDefensePoints() {
        return this.defensePoints;
    }

    @Override
    public List<String> getTargets() {
        return this.targets;
    }

    @Override
    public void attack(String target) {
        if (target == null || target.trim().isEmpty()) {
            throw new IllegalArgumentException("Attack target cannot be null or empty string.");
        }
        this.targets.add(target);
    }

    protected void increaseAttackPoints(double value) {
        this.attackPoints += value;
    }

    protected void decreaseAttackPoints(double value) {
        this.attackPoints -= value;
    }

    protected void increaseDefensePoints(double value) {
        this.defensePoints += value;
    }

    protected void decreaseDefensePoints(double value) {
        this.defensePoints -= value;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("- " + this.getName());

        String targets = this.targets.isEmpty() ? "None" : String.join(", ", this.getTargets());

        stringBuilder.append(System.lineSeparator())
                .append(String.format(" *Type: %s", this.getClass().getInterfaces()[0].getSimpleName())).append(System.lineSeparator())
                .append(String.format(" *Health: %.2f", this.getHealthPoints())).append(System.lineSeparator())
                .append(String.format(" *Attack: %.2f", this.getAttackPoints())).append(System.lineSeparator())
                .append(String.format(" *Defense: %.2f", this.getDefensePoints())).append(System.lineSeparator())
                .append(" *Targets: ").append(targets);

        return stringBuilder.toString();
    }
}
