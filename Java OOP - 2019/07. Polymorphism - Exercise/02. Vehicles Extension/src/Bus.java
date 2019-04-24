public class Bus extends VehicleImpl {
    private static final double AIR_CONDITIONER_CONSUMPTION = 1.4;

    public Bus(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
    }

    @Override
    public String drive(double distance) {
        double normalFuelConsumption = this.getFuelConsumption();
        this.setFuelConsumption(this.getFuelConsumption() + Bus.AIR_CONDITIONER_CONSUMPTION);
        String driveMessage = this.getClass().getSimpleName() + " " + super.drive(distance);
        this.setFuelConsumption(normalFuelConsumption); //Returning normal fuel consumption value
        return driveMessage;
    }

    public String driveEmpty(double distance) {
        return this.getClass().getSimpleName() + " " + super.drive(distance);
    }
}
