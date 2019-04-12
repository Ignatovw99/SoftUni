package hardwarecomponents;

public class PowerHardware extends HardwareImpl {
    public PowerHardware(String name, int maximumCapacity, int maximumMemory) {
        super(name, maximumCapacity, maximumMemory);
    }

    @Override
    protected void setMaximumCapacity(int maximumCapacity) {
        super.setMaximumCapacity(this.decreaseComponentValue(maximumCapacity));
    }

    @Override
    protected void setMaximumMemory(int maximumMemory) {
        super.setMaximumMemory(this.increaseComponentValue(maximumMemory));
    }

    @Override
    public int decreaseComponentValue(int value) {
        return value - (value * 3 / 4); //Decrease capacity of this Hardware with 75%
    }

    @Override
    public int increaseComponentValue(int value) {
        return value + (value * 3 / 4); //Increase memory capacity with 75%
    }
}
