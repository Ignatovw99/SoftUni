import core.interfaces.MachinesManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Engine {
    private BufferedReader reader;
    private MachinesManager machinesManager;

    public Engine(MachinesManager machinesManager) {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.machinesManager = machinesManager;
    }

    public void run() throws IOException { //Check exception later
        String inputLine = this.reader.readLine();
        while (!"Over".equals(inputLine)) {
            String[] tokens = inputLine.split("\\s+");
            String command = tokens[0];

            String outputMassage = null;

            switch (command) {
                case "Hire":
                    String name = tokens[1];
                    outputMassage = this.machinesManager.hirePilot(name);
                    break;
                case "Report":
                    String pilotName = tokens[1];
                    outputMassage = this.machinesManager.pilotReport(pilotName);
                    break;
                case "ManufactureTank":
                    String tankName = tokens[1];
                    double attackPoints = Double.parseDouble(tokens[2]);
                    double defensePoints = Double.parseDouble(tokens[3]);
                    outputMassage = this.machinesManager.manufactureTank(tankName, attackPoints, defensePoints);
                    break;
                case "ManufactureFighter":
                    String fighterName = tokens[1];
                    attackPoints = Double.parseDouble(tokens[2]);
                    defensePoints = Double.parseDouble(tokens[3]);
                    outputMassage = this.machinesManager.manufactureFighter(fighterName, attackPoints, defensePoints);
                    break;
                case "Engage":
                    pilotName = tokens[1];
                    String machineName = tokens[2];
                    outputMassage = this.machinesManager.engageMachine(pilotName, machineName);
                    break;
                case "Attack":
                    String attackingMachine = tokens[1];
                    String defendingMachine = tokens[2];
                    outputMassage = this.machinesManager.attackMachines(attackingMachine, defendingMachine);
                    break;
                case "AggressiveMode":
                    machineName = tokens[1];
                    outputMassage = this.machinesManager.toggleFighterAggressiveMode(machineName);
                    break;
                case "DefenseMode":
                    machineName = tokens[1];
                    outputMassage = this.machinesManager.toggleTankDefenseMode(machineName);
                    break;
            }

            if (outputMassage != null) {
                System.out.println(outputMassage);
            }
            inputLine = this.reader.readLine();
        }
    }
}
