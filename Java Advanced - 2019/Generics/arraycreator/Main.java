package arraycreator;

public class Main {
    public static void main(String[] args) {
        String[] strings = ArrayCreator.create(String.class, 10, "empty");

        for (String string : strings) {
            System.out.println(string);
        }

        Integer[] integers = ArrayCreator.create(10, 132);

        for (int i = 0; i < integers.length; i++) {
            System.out.println(i + " " + integers[i]);
        }
    }
}
