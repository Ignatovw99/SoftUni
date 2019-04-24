import java.text.DecimalFormat;

public abstract class VehicleImpl implements Vehicle {
    private double fuelQuantity;
    private double fuelConsumption;
    private double tankCapacity;

    protected VehicleImpl(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        this.setFuelQuantity(fuelQuantity);
        this.setFuelConsumption(fuelConsumption);
        this.setTankCapacity(tankCapacity);
    }

    private void setFuelQuantity(double fuelQuantity) {
        this.checkFuelQuantity(fuelQuantity);
        this.fuelQuantity = fuelQuantity;
    }

    protected void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    protected double getFuelConsumption() {
        return this.fuelConsumption;
    }

    private void setTankCapacity(double tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    @Override
    public String drive(double distance) {
        double fuelNeeded = distance * this.fuelConsumption;
        String driveMessage = "needs refueling";

        DecimalFormat df = new DecimalFormat("#.##");

        if (this.fuelQuantity >= fuelNeeded) {
            driveMessage = String.format("travelled %s km", df.format(distance));
            this.decreaseFuelQuantity(fuelNeeded);
        }

        return driveMessage;
    }

    @Override
    public void refuel(double fuelLiters) {
        this.checkFuelQuantity(fuelLiters);
        this.canFuelFitInTank(fuelLiters);
        this.increaseFuelQuantity(fuelLiters);
    }

    private void increaseFuelQuantity(double fuelLiters) {
        this.fuelQuantity += fuelLiters;
    }

    private void decreaseFuelQuantity(double fuelNeededToDrive) {
        this.fuelQuantity -= fuelNeededToDrive;
    }

    private void checkFuelQuantity(double fuelQuantity) {
        if (fuelQuantity <= 0) {
            throw new IllegalArgumentException("Fuel must be a positive number");
        }
    }

    private void canFuelFitInTank(double fuelLiters) {
        if (this.fuelQuantity + fuelLiters > this.tankCapacity) {
            throw new IllegalArgumentException("Cannot fit fuel in tank");
        }
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f", this.getClass().getSimpleName(), this.fuelQuantity);
    }
}
