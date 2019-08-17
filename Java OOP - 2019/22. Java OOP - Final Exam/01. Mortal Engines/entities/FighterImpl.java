package entities;

import entities.interfaces.Fighter;

public class FighterImpl extends BaseMachine implements Fighter {
    private boolean aggressiveMode;
    private final double attackPointsModifier = 50.0;
    private final double deffencePointsModifier = 25.0;

    public FighterImpl(String name, double attackPoints, double defensePoints) {
        super(name, attackPoints, defensePoints, 200);
        this.aggressiveMode = false;
        this.toggleAggressiveMode();
    }

    @Override
    public boolean getAggressiveMode() {
        return this.aggressiveMode;
    }

    @Override
    public void toggleAggressiveMode() {
        this.aggressiveMode = !this.getAggressiveMode();

        if (this.getAggressiveMode()) {
            this.increaseAttackPoints(this.attackPointsModifier);
            this.decreaseDefensePoints(this.deffencePointsModifier);
        } else {
            this.decreaseAttackPoints(this.attackPointsModifier);
            this.increaseDefensePoints(this.deffencePointsModifier);
        }
    }

    @Override
    public String toString() {
        String state = this.getAggressiveMode() ? "On" : "Off";
        return super.toString() + System.lineSeparator() + String.format(" *Aggressive Mode(%s)", state);
    }
}
