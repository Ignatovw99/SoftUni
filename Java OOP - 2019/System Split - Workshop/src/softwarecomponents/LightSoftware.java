package softwarecomponents;

public class LightSoftware extends SoftwareImpl {
    public LightSoftware(String name, int capacityConsumption, int memoryConsumption) {
        super(name, capacityConsumption, memoryConsumption);
    }

    @Override
    protected void setCapacityConsumption(int capacityConsumption) {
        super.setCapacityConsumption(this.increaseComponentValue(capacityConsumption));
    }

    @Override
    protected void setMemoryConsumption(int memoryConsumption) {
        super.setMemoryConsumption(this.decreaseComponentValue(memoryConsumption));
    }

    @Override
    public int increaseComponentValue(int value) {
        return value + (value / 2);
    }

    private int decreaseComponentValue(int value) {
        return value - (value / 2);
    }
}
