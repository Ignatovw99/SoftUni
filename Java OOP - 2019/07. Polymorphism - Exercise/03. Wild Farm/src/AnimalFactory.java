import animalmodels.*;

public class AnimalFactory {
    public static Animal getAnimal(String animalType, String[] tokens) {
        String name = tokens[0];
        double weight = Double.parseDouble(tokens[1]);
        String livingRegion = tokens[2];
        switch (animalType) {
            case "Cat":
                return new Cat(name, weight, livingRegion, tokens[3]);
            case "Mouse":
                return new Mouse(name, weight, livingRegion);
            case "Tiger":
                return new Tiger(name, weight, livingRegion);
                default:
                    return new Zebra(name, weight, livingRegion);
        }
    }
}
