package cresla.entities.reactors;

import cresla.entities.containers.ModuleContainer;
import cresla.interfaces.AbsorbingModule;
import cresla.interfaces.Container;
import cresla.interfaces.EnergyModule;
import cresla.interfaces.Reactor;

public abstract class BaseReactor implements Reactor {
    private int id;
    private Container modelContainer;

    protected BaseReactor(int id, int moduleCapacity) {
        this.id = id;
        this.modelContainer = new ModuleContainer(moduleCapacity);
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public long getTotalEnergyOutput() {
        return this.modelContainer.getTotalEnergyOutput();
    }

    @Override
    public long getTotalHeatAbsorbing() {
        return this.modelContainer.getTotalHeatAbsorbing();
    }

    @Override
    public int getModuleCount() {
        return this.modelContainer.getModuleByInputCount();
    }

    @Override
    public void addEnergyModule(EnergyModule energyModule) {
        this.modelContainer.addEnergyModule(energyModule);
    }

    @Override
    public void addAbsorbingModule(AbsorbingModule absorbingModule) {
        this.modelContainer.addAbsorbingModule(absorbingModule);
    }

    @Override
    public String toString() {
        return String.format("%s - %d%n" +
                "Energy Output: %d%n" +
                "Heat Absorbing: %d%n" +
                "Modules: %d",
                this.getClass().getSimpleName(), this.getId(),
                this.isOverheated() ? 0 : this.getTotalEnergyOutput(),
                this.getTotalHeatAbsorbing(),
                this.getModuleCount());
    }

    private boolean isOverheated() {
        return this.getTotalEnergyOutput() > this.getTotalHeatAbsorbing();
    }
}
