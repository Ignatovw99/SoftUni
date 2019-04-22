package animals;

public class AnimalFactory {
    public static Animal getAnimal(String type, String[] animalTokens) {
        String animalName = animalTokens[0];
        int age = Integer.parseInt(animalTokens[1]);
        String gender = animalTokens[2];

        switch (type.toLowerCase()) {
            case "dog":
                return new Dog(animalName, age, gender);
            case "cat":
                return new Cat(animalName, age, gender);
            case "frog":
                return new Frog(animalName, age, gender);
            case "kitten":
                return new Kitten(animalName, age, gender);
            case "tomcat":
                return new Tomcat(animalName, age, gender);
                default:
                    return new Animal(animalName, age, gender);
        }
    }
}
