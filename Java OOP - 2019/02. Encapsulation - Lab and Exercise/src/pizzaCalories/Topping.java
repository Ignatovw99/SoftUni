package pizzaCalories;

public class Topping {
    private static double BASE_CALORIES_PER_GRAM = 2;

    private String toppingType;
    private double weight;

    public Topping(String toppingType, double weight) {
        this.setToppingType(toppingType);
        this.setWeight(weight);
    }

    private void setToppingType(String toppingType) {
        try {
            ToppingType.valueOf(toppingType);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Cannot place " + toppingType + " on top of your pizza.");
        }

        this.toppingType = toppingType;
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 50) {
            throw new IllegalArgumentException(this.toppingType + " weight should be in the range [1..50].");
        }

        this.weight = weight;
    }

    public double calculateCalories() {
        return (this.weight * Topping.BASE_CALORIES_PER_GRAM) * ToppingType.valueOf(this.toppingType).getModifier();
    }
}
