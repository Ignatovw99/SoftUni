package core;

import hardwarecomponents.HeavyHardware;
import hardwarecomponents.PowerHardware;
import softwarecomponents.ExpressSoftware;
import softwarecomponents.LightSoftware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Engine {
    private static final String END_COMMAND = "System Split";
    private System system;
    private Dump dump;

    public Engine(System system, Dump dump) {
        this.system = system;
        this.dump = dump;
        this.dump.setSystem(system);
    }

    public void run() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(java.lang.System.in));
        String input = bufferedReader.readLine();
        while (!Engine.END_COMMAND.equals(input)) {
            String command = input.substring(0, input.indexOf("("));
            String[] commandArgs = input
                    .substring(input.indexOf("(") + 1, input.length() - 1)
                    .split(", ");
            interpretCommand(command, commandArgs);
            input = bufferedReader.readLine();
        }
        java.lang.System.out.print(this.system.toString());
    }

    private void interpretCommand(String command, String[] commandArgs) {
        switch (command) {
            case "RegisterPowerHardware":
                this.system.addHardware(new PowerHardware(
                        commandArgs[0],
                        Integer.parseInt(commandArgs[1]),
                        Integer.parseInt(commandArgs[2])
                ));
                break;
            case "RegisterHeavyHardware":
                this.system.addHardware(new HeavyHardware(
                        commandArgs[0],
                        Integer.parseInt(commandArgs[1]),
                        Integer.parseInt(commandArgs[2])
                ));
                break;
            case "RegisterExpressSoftware":
                String hardwareName = commandArgs[0];
                this.system.addSoftwareComponentToHardware(hardwareName, new ExpressSoftware(
                        commandArgs[1],
                        Integer.parseInt(commandArgs[2]),
                        Integer.parseInt(commandArgs[3])
                ));
                break;
            case "RegisterLightSoftware":
                hardwareName = commandArgs[0];
                this.system.addSoftwareComponentToHardware(hardwareName, new LightSoftware(
                        commandArgs[1],
                        Integer.parseInt(commandArgs[2]),
                        Integer.parseInt(commandArgs[3])
                ));
                break;
            case "ReleaseSoftwareComponent":
                hardwareName = commandArgs[0];
                String softwareName = commandArgs[1];
                this.system.releaseSoftwareComponent(hardwareName, softwareName);
                break;
            case "Analyze":
                java.lang.System.out.println(this.system.analyze());
                break;
            case "Dump":
                hardwareName = commandArgs[0];
                this.dump.throwHardware(hardwareName);
                break;
            case "Restore":
                hardwareName = commandArgs[0];
                this.dump.restoreHardwareOnSystem(hardwareName);
                break;
            case "Destroy":
                hardwareName = commandArgs[0];
                this.dump.destroyHardware(hardwareName);
                break;
            case "DumpAnalyze":
                java.lang.System.out.println(this.dump.analyze());
                break;
        }
    }
}
