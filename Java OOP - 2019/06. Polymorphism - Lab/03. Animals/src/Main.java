public class Main {
    public static void main(String[] args) {
        Animal cat = new Cat("John", "Whiskas");
        Animal dog = new Dog("Tom", "Meat");

        System.out.println(cat.explainSelf());
        System.out.println(dog.explainSelf());
    }
}
