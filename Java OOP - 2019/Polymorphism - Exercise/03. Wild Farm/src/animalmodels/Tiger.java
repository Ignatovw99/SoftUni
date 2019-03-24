package animalmodels;

import foodmodels.Food;
import foodmodels.Meat;

public class Tiger extends Felime {
    public Tiger(String animalName,Double animalWeight, String livingRegion) {
        super(animalName, animalWeight, livingRegion);
    }

    @Override
    public String makeSound() {
        return "ROAAR!!!";
    }

    @Override
    public void eat(Food food) {
        if (!(food instanceof Meat)) {
            throw new IllegalArgumentException("Tigers " + getInvalidFoodMessage());
        }
        super.eat(food);
    }
}
