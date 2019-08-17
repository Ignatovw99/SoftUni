package core;

import common.OutputMessages;
import core.interfaces.MachineFactory;
import core.interfaces.PilotFactory;
import core.interfaces.MachinesManager;

import entities.FighterImpl;
import entities.interfaces.Fighter;
import entities.interfaces.Machine;
import entities.interfaces.Pilot;
import entities.interfaces.Tank;

import java.time.temporal.TemporalAccessor;
import java.util.Map;

public class MachinesManagerImpl implements MachinesManager {
    private PilotFactory pilotFactory;
    private MachineFactory machineFactory;
    private Map<String, Pilot> pilots;
    private Map<String, Machine> machines;

    public MachinesManagerImpl(PilotFactory pilotFactory, MachineFactory machineFactory, Map<String, Pilot> pilots, Map<String, Machine> machines) {
        this.pilotFactory = pilotFactory;
        this.machineFactory = machineFactory;
        this.pilots = pilots;
        this.machines = machines;
    }


    @Override
    public String hirePilot(String name) {
        if (this.pilots.containsKey(name)) {
            return String.format(OutputMessages.pilotExists, name);
        }
        Pilot pilot = this.pilotFactory.createPilot(name);
        this.pilots.put(name, pilot);
        return String.format(OutputMessages.pilotHired, name);
    }

    @Override
    public String manufactureTank(String name, double attackPoints, double defensePoints) {
        if (this.machines.containsKey(name)) {
            return String.format(OutputMessages.machineExists, name);
        }
        Machine tank = this.machineFactory.createTank(name, attackPoints, defensePoints);
        this.machines.put(name, tank);
        return String.format(OutputMessages.tankManufactured, name, attackPoints, defensePoints);
    }

    @Override
    public String manufactureFighter(String name, double attackPoints, double defensePoints) {
        if (this.machines.containsKey(name)) {
            return String.format(OutputMessages.machineExists, name);
        }
        Machine fighter = this.machineFactory.createFighter(name, attackPoints, defensePoints);
        this.machines.put(name, fighter);
        return String.format(OutputMessages.fighterManufactured, name, attackPoints, defensePoints);
    }

    @Override
    public String engageMachine(String selectedPilotName, String selectedMachineName) {
        //check if pilot and machine exist
        if (!this.machines.containsKey(selectedMachineName)) {
            return String.format(OutputMessages.machineNotFound, selectedMachineName);
        }
        Machine machine = this.machines.get(selectedMachineName);
        if (this.isMachineEngaged(machine)) {
            return String.format(OutputMessages.machineHasPilotAlready, selectedMachineName);
        }
        if (!this.pilots.containsKey(selectedPilotName)) {
            return String.format(OutputMessages.pilotNotFound, selectedPilotName);
        }
        Pilot pilot = this.pilots.get(selectedPilotName);
        pilot.addMachine(machine);
        return String.format(OutputMessages.machineEngaged, selectedPilotName, selectedMachineName);
    }

    private boolean isMachineEngaged(Machine selectedMachine) {
        return this.pilots.values().stream().anyMatch(pilot -> pilot.getMachines().contains(selectedMachine));
    }

    @Override
    public String attackMachines(String attackingMachineName, String defendingMachineName) {
        if (!this.machines.containsKey(attackingMachineName)) {
            return String.format(OutputMessages.machineNotFound, attackingMachineName);
        }
        if (!this.machines.containsKey(defendingMachineName)) {
            return String.format(OutputMessages.machineNotFound, defendingMachineName);
        }

        Machine attackingMachine = this.machines.get(attackingMachineName);
        Machine defendingMachine = this.machines.get(defendingMachineName); //Check if the health must be decreased

        attackingMachine.attack(defendingMachineName);
        defendingMachine.setHealthPoints(defendingMachine.getHealthPoints() - attackingMachine.getAttackPoints());//Check it
        return String.format(OutputMessages.attackSuccessful, defendingMachineName, attackingMachineName, defendingMachine.getHealthPoints());
    }

    @Override
    public String pilotReport(String pilotName) {
        if (!this.pilots.containsKey(pilotName)) {
            return String.format(OutputMessages.pilotNotFound, pilotName);
        }
        return this.pilots.get(pilotName).report();
    }

    @Override
    public String toggleFighterAggressiveMode(String fighterName) {
        if (!(this.machines.get(fighterName) instanceof Fighter)) {
            return String.format(OutputMessages.notSupportedOperation, fighterName);
        }
        Fighter machine = (Fighter) this.machines.get(fighterName);
        machine.toggleAggressiveMode();
        return String.format(OutputMessages.fighterOperationSuccessful, fighterName);
    }

    @Override
    public String toggleTankDefenseMode(String tankName) {
        if (!(this.machines.get(tankName) instanceof Tank)) {
            return String.format(OutputMessages.notSupportedOperation, tankName);
        }
        Tank machine = (Tank) this.machines.get(tankName);
        machine.toggleDefenseMode();
        return String.format(OutputMessages.tankOperationSuccessful, tankName);
    }
}
