package core;

import contracts.Hardware;
import contracts.Software;
import hardwarecomponents.HeavyHardware;
import hardwarecomponents.PowerHardware;
import softwarecomponents.ExpressSoftware;
import softwarecomponents.LightSoftware;

import java.util.HashSet;
import java.util.Set;

public class Dump {
    private Set<Hardware> removedHardwareComponents;
    private System system;

    public Dump() {
        this.removedHardwareComponents = new HashSet<>();
    }

    public void setSystem(System system) {
        this.system = system;
    }

    public void throwHardware(String hardwareComponentName) {
        Hardware removedHardwareComponent = this.system.removeHardwareComponent(hardwareComponentName);
        this.removedHardwareComponents.add(removedHardwareComponent);
    }

    public void restoreHardwareOnSystem(String hardwareName) {
        Hardware hardwareToRestore = this.getHardwareComponent(hardwareName);
        this.system.addHardware(hardwareToRestore);
    }

    public void destroyHardware(String hardwareName) {
        Hardware hardwareToDestroy = this.getHardwareComponent(hardwareName);
        this.removedHardwareComponents.remove(hardwareToDestroy);
    }

    private Hardware getHardwareComponent(String hardwareName) {
        return this.removedHardwareComponents.stream()
                .filter(hardware -> hardware.getName().equals(hardwareName))
                .findAny().orElse(null);
    }

    public String analyze() {
        String separator = java.lang.System.lineSeparator();

        StringBuilder sb = new StringBuilder("Dump Analysis");
        sb.append(separator);
        sb.append("Power Hardware Components: ").append(this.getPowerHardwareCount()).append(separator);
        sb.append("Heavy Hardware Components: ").append(this.getHeavyHardwareCount()).append(separator);
        sb.append("Express Software Components: ").append(this.getExpressSoftwareCount()).append(separator);
        sb.append("Light Software Components: ").append(this.getLightSoftwareCount()).append(separator);
        sb.append("Total Dumped Memory: ").append(this.getTotalDumpedMemory()).append(separator);
        sb.append("Total Dumped Capacity: ").append(this.getTotalDumpedCapacity());

        return sb.toString();
    }

    private int getTotalDumpedCapacity() {
        int dumpedCapacity = 0;
        for (Hardware removedHardware : this.removedHardwareComponents) {
            dumpedCapacity += removedHardware.getUsedCapacity();
        }
        return dumpedCapacity;
    }

    private int getTotalDumpedMemory() {
        int dumpedMemory = 0;
        for (Hardware removedHardware : this.removedHardwareComponents) {
            dumpedMemory += removedHardware.getUsedMemory();
        }
        return dumpedMemory;
    }

    private int getLightSoftwareCount() {
        int lightSoftwareCount = 0;
        for (Hardware removedHardware : this.removedHardwareComponents) {
            lightSoftwareCount += (int) removedHardware.getInstalledSoftwareComponents().stream()
                    .filter(software -> software instanceof LightSoftware)
                    .count();
        }
        return lightSoftwareCount;
    }

    private int getExpressSoftwareCount() {
        int expressSoftwareCount = 0;
        for (Hardware removedHardware : this.removedHardwareComponents) {
            expressSoftwareCount += (int) removedHardware.getInstalledSoftwareComponents().stream()
                    .filter(software -> software instanceof ExpressSoftware)
                    .count();
        }
        return expressSoftwareCount;
    }

    private int getHeavyHardwareCount() {
        return (int) this.removedHardwareComponents.stream()
                .filter(hardware -> hardware instanceof HeavyHardware)
                .count();
    }

    private int getPowerHardwareCount() {
        return (int) this.removedHardwareComponents.stream()
                .filter(hardware -> hardware instanceof PowerHardware)
                .count();
    }
}
