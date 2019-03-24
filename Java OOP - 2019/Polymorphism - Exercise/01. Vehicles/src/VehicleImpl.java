import java.text.DecimalFormat;

public abstract class VehicleImpl implements Vehicle {
    private double fuelQuantity;
    private double fuelConsumption;

    protected VehicleImpl(double fuelQuantity, double fuelConsumption) {
        this.fuelQuantity = fuelQuantity;
        this.setFuelConsumption(fuelConsumption);
    }

    protected void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
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
        this.increaseFuelQuantity(fuelLiters);
    }

    private void increaseFuelQuantity(double fuelLiters) {
        this.fuelQuantity += fuelLiters;
    }

    private void decreaseFuelQuantity(double fuelNeededToDrive) {
        this.fuelQuantity -= fuelNeededToDrive;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f", this.getClass().getSimpleName(), this.fuelQuantity);
    }
}
