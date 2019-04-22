package pizzaCalories;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String[] pizzaTokens = Arrays.stream(reader.readLine().split("\\s+"))
                    .skip(1)
                    .toArray(String[]::new);

            Pizza pizza = new Pizza(pizzaTokens[0], Integer.parseInt(pizzaTokens[1]));

            String[] doughTokens = Arrays.stream(reader.readLine().split("\\s+"))
                    .skip(1)
                    .toArray(String[]::new);

            Dough dough = new Dough(doughTokens[0], doughTokens[1], Double.parseDouble(doughTokens[2]));

            pizza.setDough(dough);

            int numberOfToppings = Integer.parseInt(pizzaTokens[1]);

            for (int i = 0; i < numberOfToppings; i++) {
                String[] toppingTokens = Arrays.stream(reader.readLine().split("\\s+"))
                        .skip(1)
                        .toArray(String[]::new);

                Topping topping = new Topping(toppingTokens[0], Double.parseDouble(toppingTokens[1]));

                pizza.addTopping(topping);
            }

            System.out.println(pizza);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }


    }
}
