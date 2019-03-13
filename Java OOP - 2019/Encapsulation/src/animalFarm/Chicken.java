package animalFarm;

import java.text.DecimalFormat;

public class Chicken {
    private String name;
    private int age;

    public Chicken(String name, int age) {
        this.setName(name);
        this.setAge(age);
    }

    private void setName(String name) {
        if (!this.isNameValid(name)) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }

    private String getName() {
        return this.name;
    }

    private boolean isNameValid(String name) {
        return name != null && name.length() > 0 && !Character.isWhitespace(name.charAt(0));
    }

    private void setAge(int age) {
        if (age < 0 || age > 15) {
            throw new IllegalArgumentException("Age should be between 0 and 15.");
        }
        this.age = age;
    }

    private int getAge() {
        return this.age;
    }

    public double productPerDay() {
        return this.calculateProductPerDay();
    }

    private double calculateProductPerDay() {
        if (this.getAge() >= 0 && this.getAge() <= 5) {
            return 2;
        } else if (this.getAge() >= 6 && this.getAge() <= 11) {
            return 1;
        } else {
            return 0.75;
        }
    }

    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("0.##");
        return String.format("Chicken %s (age %d) can produce %s eggs per day.",
                this.getName(), this.getAge(), format.format(this.productPerDay()));
    }
}
