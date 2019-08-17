package entities;

import entities.interfaces.Tank;

public class TankImpl extends BaseMachine implements Tank {
    private boolean defenseMode;
    private final double attackPointsModifier = 40.0;
    private final double deffencePointsModifier = 30.0;

    public TankImpl(String name, double attackPoints, double defensePoints) {
        super(name, attackPoints, defensePoints, 100);
        this.defenseMode = false;
        this.toggleDefenseMode();
    }

    @Override
    public boolean getDefenseMode() {
        return this.defenseMode;
    }

    @Override
    public void toggleDefenseMode() {
        this.defenseMode = !this.getDefenseMode();

        if (this.getDefenseMode()) {
            this.decreaseAttackPoints(this.attackPointsModifier);
            this.increaseDefensePoints(this.deffencePointsModifier);
        } else {
            this.increaseAttackPoints(this.attackPointsModifier);
            this.decreaseDefensePoints(this.deffencePointsModifier);
        }
    }

    @Override
    public String toString() {
        String state = this.getDefenseMode() ? "On" : "Off";
        return super.toString() + System.lineSeparator() + String.format(" *Defense Mode(%s)", state);
    }
}
