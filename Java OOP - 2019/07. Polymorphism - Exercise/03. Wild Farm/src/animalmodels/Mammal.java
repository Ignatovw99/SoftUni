package animalmodels;

import foodmodels.Food;

public abstract class Mammal extends Animal {
    private static final String INVALID_FOOD_MESSAGE = "are not eating that type of food!";

    private String livingRegion;

    public Mammal(String animalName, Double animalWeight, String livingRegion) {
        super(animalName, animalWeight);
        this.livingRegion = livingRegion;
    }

    public static String getInvalidFoodMessage() {
        return Mammal.INVALID_FOOD_MESSAGE;
    }

    @Override
    public void eat(Food food) {
        this.increaseAmountOfEatenFoodWith(food.getQuantity());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(super.toString());
        builder.insert(builder.lastIndexOf(",") + 2, this.livingRegion + ", ");
        return builder.toString();
    }
}
