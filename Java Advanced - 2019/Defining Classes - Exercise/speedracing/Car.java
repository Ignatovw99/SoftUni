package speedracing;

public class Car {
    private String model;
    private double fuelAmount;
    private double fuelCostForOneKm;
    private int traveledDistance;

    public Car(String model, int fuelAmount, double fuelCostForOneKm) {
        this.model = model;
        this.fuelAmount = fuelAmount;
        this.fuelCostForOneKm = fuelCostForOneKm;
        this.traveledDistance = 0;
    }

    public boolean canTravelDistance(int distance) {
        return distance * this.fuelCostForOneKm <= this.fuelAmount;
    }

    public void drive(int distance) {
        this.fuelAmount -= distance * this.fuelCostForOneKm;
        this.traveledDistance += distance;
    }

    public String getInfo() {
        return String.format("%s %.2f %d", this.model, this.fuelAmount, this.traveledDistance);
    }
}
