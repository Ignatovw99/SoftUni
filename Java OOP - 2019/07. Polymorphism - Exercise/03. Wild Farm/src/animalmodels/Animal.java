package animalmodels;

import interfaces.Feedable;
import interfaces.Soundable;

import java.text.DecimalFormat;

public abstract class Animal implements Feedable, Soundable {
    private String animalName;
    private Double animalWeight;
    private Integer foodEaten;

    protected Animal(String animalName, Double animalWeight) {
        this.animalName = animalName;
        this.animalWeight = animalWeight;
        this.foodEaten = 0;
    }

    protected void increaseAmountOfEatenFoodWith(Integer quantity) {
        this.foodEaten += quantity;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        return String.format("%s[%s, %s, %d]",
                this.getClass().getSimpleName(),
                this.animalName,
                df.format(this.animalWeight),
                this.foodEaten);
    }
}
