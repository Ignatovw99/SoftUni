package core;

import contracts.Hardware;
import contracts.Software;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Consumer;

public class System {
    private Set<Hardware> hardwareComponents;

    public System() {
        this.hardwareComponents = new LinkedHashSet<>();
    }

    public void addHardware(Hardware hardware) {
        this.hardwareComponents.add(hardware);
    }

    public void addSoftwareComponentToHardware(String hardwareName, Software software) {
        this.hardwareComponents.stream()
                .filter(hardware -> hardware.getName().equals(hardwareName))
                .findAny()
                .ifPresent(hardwareFromSystem -> hardwareFromSystem.installSoftware(software));
    }

    public void releaseSoftwareComponent(String hardwareName, String softwareName){
        this.hardwareComponents.stream()
                .filter(hardware -> hardware.getName().equals(hardwareName))
                .findAny()
                .ifPresent(hardwareFromSystem -> hardwareFromSystem.releaseSoftware(softwareName));
    }

    public Hardware removeHardwareComponent(String hardwareComponentName) {
        Hardware hardwareComponentToRemove = this.hardwareComponents.stream()
                .filter(hardware -> hardware.getName().equals(hardwareComponentName))
                .findAny().orElse(null);
        this.hardwareComponents.remove(hardwareComponentToRemove);
        return hardwareComponentToRemove;
    }

    public String analyze() {
        String separator = java.lang.System.lineSeparator();

        StringBuilder sb = new StringBuilder("System Analysis");
        sb.append(separator);
        sb.append("Hardware Components: ")
                .append(this.hardwareComponents.size())
                .append(separator);
        sb.append("Software Components: ")
                .append(this.getAllSoftwareComponentsCount())
                .append(separator);
        sb.append("Total Operational Memory: ")
                .append(this.getTotalMemoryInUse())
                .append(" / ")
                .append(this.getMaximumMemory())
                .append(separator);
        sb.append("Total Capacity Taken: ")
                .append(this.getTakenCapacity())
                .append(" / ")
                .append(this.getMaximumCapacity());

        return sb.toString();
    }

    private int getMaximumCapacity() {
        int maxCapacity = 0;
        for (Hardware hardwareComponent : this.hardwareComponents) {
            maxCapacity += hardwareComponent.getMaximumCapacity();
        }
        return maxCapacity;
    }

    private int getTakenCapacity() {
        int takenCapacity = 0;
        for (Hardware hardwareComponent : this.hardwareComponents) {
            takenCapacity += hardwareComponent.getUsedCapacity();
        }
        return takenCapacity;
    }

    private int getMaximumMemory() {
        int maximumMemory = 0;
        for (Hardware hardwareComponent : this.hardwareComponents) {
            maximumMemory += hardwareComponent.getMaximumMemory();
        }
        return maximumMemory;
    }

    private int getTotalMemoryInUse() {
        int memoryInUse = 0;
        for (Hardware hardwareComponent : this.hardwareComponents) {
            memoryInUse += hardwareComponent.getUsedMemory();
        }
        return memoryInUse;
    }

    private int getAllSoftwareComponentsCount() {
        int softwareComponentsCount = 0;
        for (Hardware hardwareComponent : this.hardwareComponents) {
            softwareComponentsCount += hardwareComponent.getInstalledSoftwareComponents().size();
        }
        return softwareComponentsCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Consumer<Hardware> appendHardware = hardware -> sb.append(hardware.toString())
                .append(java.lang.System.lineSeparator());

        this.hardwareComponents.stream()
                .filter(hardware -> hardware.getClass().getSimpleName().equals("PowerHardware"))
                .forEach(appendHardware);

        this.hardwareComponents.stream()
                .filter(hardware -> hardware.getClass().getSimpleName().equals("HeavyHardware"))
                .forEach(appendHardware);

        return sb.toString();
    }
}
