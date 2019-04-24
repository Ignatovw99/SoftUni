package animalmodels;

public class Cat extends Felime {
    private String breed;
    public Cat(String animalName, Double animalWeight, String livingRegion, String breed) {
        super(animalName, animalWeight, livingRegion);
        this.breed = breed;
    }

    @Override
    public String makeSound() {
        return "Meowwww";
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(super.toString());
        builder.insert(builder.indexOf(",") + 2, this.breed + ", ");
        return builder.toString();
    }
}
