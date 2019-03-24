public class Car extends VehicleImpl {
    private static final double INCREASED_FUEL_CONSUMPTION = 0.9;

    public Car(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
    }

    @Override
    protected void setFuelConsumption(double fuelConsumption) {
        super.setFuelConsumption(fuelConsumption + Car.INCREASED_FUEL_CONSUMPTION);
    }

    @Override
    public String drive(double distance) {
        return "Car " + super.drive(distance);
    }
}
