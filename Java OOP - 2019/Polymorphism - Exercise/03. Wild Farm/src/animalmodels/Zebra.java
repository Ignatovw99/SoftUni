package animalmodels;

import foodmodels.Food;
import foodmodels.Vegetable;

public class Zebra extends Mammal {
    public Zebra(String animalName, Double animalWeight, String livingRegion) {
        super(animalName, animalWeight, livingRegion);
    }

    @Override
    public String makeSound() {
        return "Zs";
    }

    @Override
    public void eat(Food food) {
        if (!(food instanceof Vegetable)) {
            throw new IllegalArgumentException("Zebras " + getInvalidFoodMessage());
        }
        super.eat(food);
    }
}
