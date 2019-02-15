package genericscale;

public class Main {
    public static void main(String[] args) {
        Scale<Integer> integerScale = new Scale<>(5, 5);
        System.out.println(integerScale.getHeavier());
        Scale<String> stringScale = new Scale<>("pesho", "gosho");
        System.out.println(stringScale.getHeavier());
    }
}
