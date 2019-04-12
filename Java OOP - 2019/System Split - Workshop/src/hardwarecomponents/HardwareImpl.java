package hardwarecomponents;

import contracts.Hardware;
import contracts.Software;
import softwarecomponents.ExpressSoftware;
import softwarecomponents.LightSoftware;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public abstract class HardwareImpl implements Hardware {
    private String name;
    private int maximumCapacity;
    private int maximumMemory;
    private Set<Software> installedSoftwareComponents;
    private int usedCapacity;
    private int usedMemory;

    protected HardwareImpl(String name, int maximumCapacity, int maximumMemory) {
        this.name = name;
        this.setMaximumCapacity(maximumCapacity);
        this.setMaximumMemory(maximumMemory);
        this.installedSoftwareComponents = new LinkedHashSet<>();
        this.usedCapacity = 0;
        this.usedMemory = 0;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getMaximumCapacity() {
        return this.maximumCapacity;
    }

    @Override
    public int getMaximumMemory() {
        return this.maximumMemory;
    }

    @Override
    public int getUsedCapacity() {
        return this.usedCapacity;
    }

    @Override
    public int getUsedMemory() {
        return this.usedMemory;
    }

    protected void setMaximumCapacity(int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
    }

    protected void setMaximumMemory(int maximumMemory) {
        this.maximumMemory = maximumMemory;
    }

    @Override
    public Set<Software> getInstalledSoftwareComponents() {
        return this.installedSoftwareComponents;
    }

    @Override
    public void installSoftware(Software software) {
        if (this.hasFreeCapacity(software.getCapacityConsumption())
                && this.hasFreeMemory(software.getMemoryConsumption())) {
            this.installedSoftwareComponents.add(software);
            this.increaseUsedCapacity(software.getCapacityConsumption());
            this.increaseUsedMemory(software.getMemoryConsumption());
        }
    }

    @Override
    public void releaseSoftware(String softwareName) {
        Software softwareInstalledOnHardware = this.installedSoftwareComponents.stream()
                .filter(software -> software.getName().equals(softwareName))
                .findAny()
                .orElse(null);
        if (softwareInstalledOnHardware != null) {
            this.installedSoftwareComponents.remove(softwareInstalledOnHardware);
            this.decreaseUsedCapacity(softwareInstalledOnHardware.getCapacityConsumption());
            this.decreaseUsedMemory(softwareInstalledOnHardware.getMemoryConsumption());
        }

    }

    private void increaseUsedMemory(int memory) {
        this.usedMemory += memory;
    }

    private void decreaseUsedMemory(int memory) {
        this.usedMemory -= memory;
    }

    private void increaseUsedCapacity(int capacity) {
        this.usedCapacity += capacity;
    }

    private void decreaseUsedCapacity(int capacity) {
        this.usedCapacity -= capacity;
    }

    private boolean hasFreeCapacity(int capacity) {
        return this.maximumCapacity - (this.usedCapacity + capacity) >= 0;
    }

    private boolean hasFreeMemory(int memory) {
        return this.maximumMemory - (this.usedMemory + memory) >= 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hardware)) return false;
        HardwareImpl hardware = (HardwareImpl) o;
        return name.equals(hardware.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    private int getExpressSoftwareCount() {
        return (int) this.installedSoftwareComponents.stream()
                .filter(x -> x instanceof ExpressSoftware)
                .count();
    }

    private int getLightSoftwareCount() {
        return (int) this.installedSoftwareComponents.stream()
                .filter(x -> x instanceof LightSoftware)
                .count();
    }

    private String getInstalledSoftwareComponentsMessage() {
        StringBuilder sb = new StringBuilder();
        for (Software softwareComponent : this.installedSoftwareComponents) {
            sb.append(softwareComponent.getName()).append(", ");
        }
        String result;
        if (this.installedSoftwareComponents.size() == 0) {
            result = "None";
        } else {
            result = sb.substring(0, sb.lastIndexOf(", "));
        }
        return result;
    }

    @Override
    public String toString() {

        return String.format("Hardware Component - %s%n" +
                "Express Software Components - %d%n" +
                "Light Software Components - %d%n" +
                "Memory Usage: %d / %d%n" +
                "Capacity Usage: %d / %d%n" +
                "Type: %s%n" +
                "Software Components: %s",
                this.getName(),
                this.getExpressSoftwareCount(),
                this.getLightSoftwareCount(),
                this.getUsedMemory(),
                this.maximumMemory,
                this.getUsedCapacity(),
                this.maximumCapacity,
                this.getClass().getSimpleName().substring(0, this.getClass().getSimpleName().indexOf("Hardware")),
                this.getInstalledSoftwareComponentsMessage());
    }
}
