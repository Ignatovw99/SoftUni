package carTrip;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarTest {
    private final String DEFAULT_MODEL = "BMW";
    private final double DEFAULT_CAPACITY = 60;
    private final double DEFAULT_FUEL_AMOUNT = 10;
    private final double DEFAULT_FUEL_CONSUMPTION = 10;

    private Car car;

    @Before
    public void create() {
        this.car = new Car(DEFAULT_MODEL, DEFAULT_CAPACITY, DEFAULT_FUEL_AMOUNT, DEFAULT_FUEL_CONSUMPTION);
    }

    @Test
    public void getModelShouldWorkCorrectly() {
        assertEquals(DEFAULT_MODEL, this.car.getModel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setModelShouldThrowExceptionIfModelIsNull() {
        this.car.setModel(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setModelShouldThrowExceptionIfModelIsEmptyString() {
        this.car.setModel("");
    }

    @Test
    public void setModelShouldWorkCorrectlyWithAppropriateValue() {
        final String NEW_MODEL = "Mercedes";
        this.car.setModel(NEW_MODEL);
        assertEquals(NEW_MODEL, this.car.getModel());
    }

    @Test
    public void getTankCapacityShouldWorkCorrectly() {
        assertEquals(DEFAULT_CAPACITY, this.car.getTankCapacity(), 0.0);
    }

    @Test
    public void setTankCapacityShouldWorkCorrectlyWithAppropriateValue() {
        final double NEW_TANK_CAPACITY = 50;
        this.car.setTankCapacity(NEW_TANK_CAPACITY);
        assertEquals(NEW_TANK_CAPACITY, this.car.getTankCapacity(), 0.0);
    }

    @Test
    public void getFuelAmountShouldWorkCorrectly() {
        assertEquals(DEFAULT_FUEL_AMOUNT, this.car.getFuelAmount(), 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setFuelAmountShouldThrowExceptionIfAmountIsGreaterThanCapacity() {
        this.car.setFuelAmount(DEFAULT_CAPACITY + 1);
    }

    @Test
    public void setFuelAmountShouldWorkCorrectlyWithAmountLessThanCapacity() {
        final double EXPECTED_FUEL_AMOUNT = DEFAULT_CAPACITY - 1;
        this.car.setFuelAmount(DEFAULT_CAPACITY - 1);
        assertEquals(EXPECTED_FUEL_AMOUNT, this.car.getFuelAmount(), 0.0);
    }

    @Test
    public void getFuelConsumptionShouldWorkCorrectly() {
        assertEquals(DEFAULT_FUEL_CONSUMPTION, this.car.getFuelConsumptionPerKm(), 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setFuelAmountShouldThrowExceptionIfItIsNegative() {
        final double CONSUMPTION_LESS_THAN_ZERO = -1;
        this.car.setFuelConsumptionPerKm(CONSUMPTION_LESS_THAN_ZERO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setFuelAmountShouldThrowExceptionIfItIsEqualToZero() {
        final double ZERO_CONSUMPTION = 0;
        this.car.setFuelConsumptionPerKm(ZERO_CONSUMPTION);
    }

    @Test
    public void setFuelAmountShouldWorkCorrectlyIfItIsPositive() {
        final double EXPECTED_CONSUMPTION = DEFAULT_FUEL_CONSUMPTION + 1;
        this.car.setFuelConsumptionPerKm(DEFAULT_FUEL_CONSUMPTION + 1);
        assertEquals(EXPECTED_CONSUMPTION, this.car.getFuelConsumptionPerKm(), 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorShouldThrowExceptionIfModelIsNull() {
        new Car(null, DEFAULT_CAPACITY, DEFAULT_FUEL_AMOUNT, DEFAULT_FUEL_CONSUMPTION);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorShouldThrowExceptionIfModelIsEmptyString() {
        new Car("", DEFAULT_CAPACITY, DEFAULT_FUEL_AMOUNT, DEFAULT_FUEL_CONSUMPTION);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorShouldThrowExceptionIfFuelAmountIsGreaterThanCapacity() {
        new Car(DEFAULT_MODEL, DEFAULT_CAPACITY, DEFAULT_FUEL_AMOUNT + DEFAULT_CAPACITY, DEFAULT_FUEL_CONSUMPTION);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorShouldThrowExceptionIfFuelConsumptionIsNegative() {
        new Car(DEFAULT_MODEL, DEFAULT_CAPACITY, DEFAULT_FUEL_AMOUNT, DEFAULT_FUEL_CONSUMPTION * -1);
    }

    @Test
    public void constructorShouldNotThrowExceptionWithCorrectParams() {
        new Car(DEFAULT_MODEL, DEFAULT_CAPACITY, DEFAULT_FUEL_AMOUNT, DEFAULT_FUEL_CONSUMPTION);
    }

    @Test(expected = IllegalStateException.class)
    public void driveShouldThrowExceptionIfTripConsumptionIsGreaterThanFuelAmount() {
        final double LONG_DISTANCE = 10;
        this.car.drive(LONG_DISTANCE);
    }

    @Test
    public void driveShouldDecreaseFuelAmountCorrectly() {
        final double DISTANCE = 0.5;
        double expectedFuelAmount = DEFAULT_FUEL_AMOUNT - DISTANCE * DEFAULT_FUEL_CONSUMPTION;
        this.car.drive(DISTANCE);
        assertEquals(expectedFuelAmount, this.car.getFuelAmount(), 0.0);
    }

    @Test
    public void driveShouldReturnMessageIfCarWasDriven() {
        String expectedMessage = "Have a nice trip";
        double distance = 0.5;
        assertEquals(expectedMessage, this.car.drive(distance));
    }

    @Test(expected = IllegalStateException.class)
    public void refuelShouldThrowExceptionIfTotalFuelAmountIsGreaterThanTankCapacity() {
        double additionalFuelAmount = 60;
        this.car.refuel(additionalFuelAmount);
    }

    @Test
    public void refuelShouldIncreaseFuelAmountCorrectly() {
        double additionalFuelAmount = 15;
        double expectedFuelAmount = DEFAULT_FUEL_AMOUNT + additionalFuelAmount;
        this.car.refuel(additionalFuelAmount);
        assertEquals(expectedFuelAmount, this.car.getFuelAmount(), 0.0);
    }
}