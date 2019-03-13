package shoppingSpree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        LinkedHashMap<String, Person> people = new LinkedHashMap<>();
        LinkedHashMap<String, Product> products = new LinkedHashMap<>();

        String[] inputPeople = reader.readLine().split(";");

        for (String personTokens : inputPeople) {
            String personName = personTokens.split("=")[0];
            double money = Double.parseDouble(personTokens.split("=")[1]);

            try {
                if (!people.containsKey(personName)) {
                    people.put(personName, new Person(personName, money));
                }
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
                return;
            }
        }

        String[] productsInput = (reader.readLine().split(";"));

        for (String productTokens : productsInput) {
            String productName = productTokens.split("=")[0];
            double cost = Double.parseDouble(productTokens.split("=")[1]);

            try {
                if (!products.containsKey(productName)) {
                    products.put(productName, new Product(productName, cost));
                }
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
                return;
            }
        }

        String command = reader.readLine();

        while (!"END".equals(command)) {
            String[] tokens = command.split(" ");
            String personName = tokens[0];
            String productName = tokens[1];

            if (people.containsKey(personName) && products.containsKey(productName)) {
                Person person = people.get(personName);
                Product product = products.get(productName);

                try {
                    person.buyProduct(product);
                    System.out.println(String.format("%s bought %s",
                            personName, productName));
                } catch (IllegalArgumentException exception) {
                    System.out.println(exception.getMessage());
                }
            }

            command = reader.readLine();
        }

        for (Person person : people.values()) {
            System.out.println(person.toString());
        }
    }
}
