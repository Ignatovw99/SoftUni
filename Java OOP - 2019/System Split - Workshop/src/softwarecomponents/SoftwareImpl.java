package softwarecomponents;

import contracts.Software;

import java.util.Objects;

public abstract class SoftwareImpl implements Software {
    private String name;
    private int capacityConsumption;
    private int memoryConsumption;

    protected SoftwareImpl(String name, int capacityConsumption, int memoryConsumption) {
        this.name = name;
        this.setCapacityConsumption(capacityConsumption);
        this.setMemoryConsumption(memoryConsumption);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getCapacityConsumption() {
        return this.capacityConsumption;
    }

    protected void setCapacityConsumption(int capacityConsumption) {
        this.capacityConsumption = capacityConsumption;
    }

    @Override
    public int getMemoryConsumption() {
        return this.memoryConsumption;
    }

    protected void setMemoryConsumption(int memoryConsumption) {
        this.memoryConsumption = memoryConsumption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Software)) return false;
        SoftwareImpl software = (SoftwareImpl) o;
        return name.equals(software.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
