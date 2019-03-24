package animalmodels;

import foodmodels.Food;
import foodmodels.Vegetable;

public class Mouse extends Mammal {
    public Mouse(String animalName, Double animalWeight, String livingRegion) {
        super(animalName, animalWeight, livingRegion);
    }

    @Override
    public String makeSound() {
        return "SQUEEEAAAK!";
    }

    @Override
    public void eat(Food food) {
        if (!(food instanceof Vegetable)) {
            throw new IllegalArgumentException("Mice " + getInvalidFoodMessage());
        }
        super.eat(food);
    }
}
