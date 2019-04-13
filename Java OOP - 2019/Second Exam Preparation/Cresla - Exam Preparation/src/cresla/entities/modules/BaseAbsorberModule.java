package cresla.entities.modules;

import cresla.interfaces.AbsorbingModule;

public abstract class BaseAbsorberModule extends BaseModule implements AbsorbingModule {
    private int heatAbsorbing;

    protected BaseAbsorberModule(int id, int heatAbsorbing) {
        super(id);
        this.heatAbsorbing = heatAbsorbing;
    }

    @Override
    public int getHeatAbsorbing() {
        return this.heatAbsorbing;
    }

    @Override
    public String toString() {
        return super.toString() + System.lineSeparator() + "Heat Absorbing: " + this.getHeatAbsorbing();
    }
}
