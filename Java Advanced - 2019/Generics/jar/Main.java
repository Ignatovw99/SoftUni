package jar;

public class Main {
    public static void main(String[] args) {
        Jar<String> jar = new Jar<>();

        jar.add("Pesho");
        String name = jar.remove();
    }
}
