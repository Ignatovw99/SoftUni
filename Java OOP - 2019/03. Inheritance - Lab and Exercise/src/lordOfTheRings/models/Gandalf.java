package lordOfTheRings.models;

import lordOfTheRings.factories.FoodFactory;
import lordOfTheRings.factories.MoodFactory;
import lordOfTheRings.models.food.Food;

public class Gandalf {
    private static final int DEFAULT_HAPPINESS_POINT = 0;

    private int foodPoints;

    public Gandalf() {
        this.foodPoints = Gandalf.DEFAULT_HAPPINESS_POINT;
    }

    public int getFoodPoints() {
        return this.foodPoints;
    }

    public void eatFood(String food) {
        Food eatenFood = FoodFactory.getFood(food);
        this.foodPoints += eatenFood.getHappinessPoints();
    }

    public String getMood() {
        return MoodFactory.getMood(this.foodPoints).toString();
    }
}
