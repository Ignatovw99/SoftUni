package rawdata;

public class Tire {
    private double pressure;
    private int age;

    public Tire(double pressure, int age) {
        this.pressure = pressure;
        this.age = age;
    }

    public double getPressure() {
        return this.pressure;
    }

    public static Tire[] initializeAllTires(Tire[] tires) {
        Tire[] newTires = new Tire[4];
        double pressure;
        int age;

        for (int i = 0; i < tires.length; i++) {
            pressure = tires[i].pressure;
            age = tires[i].age;
            newTires[i] = new Tire(pressure, age);
        }

        return newTires;
    }

    public boolean isPressureValid() {
        return this.pressure < 1;
    }
}
