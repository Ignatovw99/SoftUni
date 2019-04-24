public class Truck extends VehicleImpl {
    private static final double INCREASED_FUEL_CONSUMPTION = 1.6;
    private static final double FUEL_LOST_BY_REFUELING = 0.95;

    public Truck(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
    }

    @Override
    protected void setFuelConsumption(double fuelConsumption) {
        super.setFuelConsumption(fuelConsumption + Truck.INCREASED_FUEL_CONSUMPTION);
    }

    @Override
    public String drive(double distance) {
        return "Truck " + super.drive(distance);
    }

    @Override
    public void refuel(double fuelLiters) {
        double endFuelLitersByRefueling = fuelLiters * Truck.FUEL_LOST_BY_REFUELING;
        super.refuel(endFuelLitersByRefueling);
    }
}
