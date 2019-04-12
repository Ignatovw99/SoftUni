package softwarecomponents;

public class ExpressSoftware extends SoftwareImpl {
    public ExpressSoftware(String name, int capacityConsumption, int memoryConsumption) {
        super(name, capacityConsumption, memoryConsumption);
    }

    @Override
    protected void setMemoryConsumption(int memoryConsumption) {
        super.setMemoryConsumption(this.increaseComponentValue(memoryConsumption));
    }

    @Override
    public int increaseComponentValue(int value) {
        return value * 2;
    }
}
