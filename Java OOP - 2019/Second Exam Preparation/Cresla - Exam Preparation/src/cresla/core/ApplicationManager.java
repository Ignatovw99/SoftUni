package cresla.core;

import cresla.entities.modules.CooldownSystem;
import cresla.entities.modules.CryogenRod;
import cresla.entities.modules.HeatProcessor;
import cresla.entities.reactors.CryoReactor;
import cresla.entities.reactors.HeatReactor;
import cresla.interfaces.*;
import cresla.interfaces.Module;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationManager implements Manager {
    private static int ID;
    private static final int INITIAL_ID = 1;

    private Map<Integer, Reactor> reactors;
    private Map<Integer, Module> modules;

    public ApplicationManager() {
        ApplicationManager.ID = ApplicationManager.INITIAL_ID;
        this.reactors = new HashMap<>();
        this.modules = new HashMap<>();
    }

    @Override
    public String reactorCommand(List<String> arguments) {
        String reactorType = arguments.get(0);
        int additionalParameter = Integer.parseInt(arguments.get(1));
        int moduleCapacity = Integer.parseInt(arguments.get(2));

        Reactor reactor;

        if ("Cryo".equals(reactorType)) {
            reactor = new CryoReactor(ApplicationManager.ID, moduleCapacity, additionalParameter);
        } else {
            reactor = new HeatReactor(ApplicationManager.ID, moduleCapacity, additionalParameter);
        }

        this.reactors.put(reactor.getId(), reactor);
        this.incrementId();
        return String.format("Created %s Reactor - %d", reactorType, reactor.getId());
    }

    @Override
    public String moduleCommand(List<String> arguments) {
        int reactorId = Integer.parseInt(arguments.get(0));
        String moduleType = arguments.get(1);
        int additionalParameter = Integer.parseInt(arguments.get(2));

        Module module = null;

        switch (moduleType) {
            case "CryogenRod":
                module = new CryogenRod(ApplicationManager.ID, additionalParameter);
                this.reactors.get(reactorId).addEnergyModule((EnergyModule) module);
                break;
            case "HeatProcessor":
                module = new HeatProcessor(ApplicationManager.ID, additionalParameter);
                this.reactors.get(reactorId).addAbsorbingModule((AbsorbingModule) module);
                break;
            case "CooldownSystem":
                module = new CooldownSystem(ApplicationManager.ID, additionalParameter);
                this.reactors.get(reactorId).addAbsorbingModule((AbsorbingModule) module);
                break;
        }

        this.modules.put(module.getId(), module);

        this.incrementId();

        return String.format("Added %s - %d to Reactor - %d", moduleType, module.getId(), reactorId);
    }

    @Override
    public String reportCommand(List<String> arguments) {
        int objectId = Integer.parseInt(arguments.get(0));

        String reportedMessage = "";

        if (this.reactors.containsKey(objectId)) {
            reportedMessage = this.reactors.get(objectId).toString();
        } else if (this.modules.containsKey(objectId)) {
            reportedMessage = this.modules.get(objectId).toString();
        }

        return reportedMessage;
    }

    @Override
    public String exitCommand(List<String> arguments) {
        int cryoReactorsCount = 0;
        int heatReactorsCount = 0;
        int energyModulesCount = 0;
        int absorbingModulesCount = 0;
        long totalEnergyOutput = 0;
        long totalHeatAbsorbing = 0;

        for (Map.Entry<Integer, Reactor> reactorEntry : reactors.entrySet()) {
            if (reactorEntry.getValue().getClass().getSimpleName().equals("CryoReactor")) {
                cryoReactorsCount++;
            } else {
                heatReactorsCount++;
            }
            if (reactorEntry.getValue().getTotalEnergyOutput() <= reactorEntry.getValue().getTotalHeatAbsorbing()) {
                totalEnergyOutput += reactorEntry.getValue().getTotalEnergyOutput();
            }
            totalHeatAbsorbing += reactorEntry.getValue().getTotalHeatAbsorbing();
        }

        for (Map.Entry<Integer, Module> moduleEntry : modules.entrySet()) {
            if (moduleEntry.getValue().getClass().getSuperclass().getSimpleName().equals("BaseEnergyModule")) {
                energyModulesCount++;
            } else {
                absorbingModulesCount++;
            }
        }

        return "Cryo Reactors: " + cryoReactorsCount + System.lineSeparator() +
                "Heat Reactors: " + heatReactorsCount + System.lineSeparator() +
                "Energy Modules: " + energyModulesCount + System.lineSeparator() +
                "Absorbing Modules: " + absorbingModulesCount + System.lineSeparator() +
                "Total Energy Output: " + totalEnergyOutput + System.lineSeparator() +
                "Total Heat Absorbing: " + totalHeatAbsorbing;
    }

    private void incrementId() {
        ApplicationManager.ID += 1;
    }
}
