package hardwarecomponents;

public class HeavyHardware extends HardwareImpl {
    public HeavyHardware(String name, int maximumCapacity, int maximumMemory) {
        super(name, maximumCapacity, maximumMemory);
    }

    @Override
    protected void setMaximumCapacity(int maximumCapacity) {
        super.setMaximumCapacity(this.increaseComponentValue(maximumCapacity));
    }

    @Override
    protected void setMaximumMemory(int maximumMemory) {
        super.setMaximumMemory(this.decreaseComponentValue(maximumMemory));
    }

    @Override
    public int decreaseComponentValue(int value) {
        return value - (value / 4); //It decreases memory with 25%
    }

    @Override
    public int increaseComponentValue(int value) {
        return value * 2; //It doubles capacity
    }
}
