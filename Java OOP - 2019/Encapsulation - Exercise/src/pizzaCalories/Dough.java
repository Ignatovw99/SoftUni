package pizzaCalories;

public class Dough {
    private static double BASE_CALORIES_PER_GRAM = 2;

    private String flourType;
    private String bakingTechnique;
    private double weight;

    public Dough(String flourType, String bakingTechnique, double weight) {
        this.setFlourType(flourType);
        this.setBakingTechnique(bakingTechnique);
        this.setWeight(weight);
    }

    private void setFlourType(String flourType) {
        try {
            FlourType.valueOf(flourType);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Invalid type of dough.");
        }
        this.flourType = flourType;
    }

    private void setBakingTechnique(String bakingTechnique) {
        try {
            BakingTechnique.valueOf(bakingTechnique);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Invalid type of dough.");
        }
        this.bakingTechnique = bakingTechnique;
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 200) {
            throw new IllegalArgumentException("Dough weight should be in the range [1..200].");
        }

        this.weight = weight;
    }

    public double calculateCalories() {
        return (this.weight * Dough.BASE_CALORIES_PER_GRAM)
                * FlourType.valueOf(this.flourType).getModifier() * BakingTechnique.valueOf(this.bakingTechnique).getModifier();
    }
}
