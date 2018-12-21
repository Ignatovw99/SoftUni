import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Orders {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> productsQuantity = new LinkedHashMap<>();
        Map<String, Double> productsPrice = new LinkedHashMap<>();

        String command = scanner.nextLine();

        while (!command.equalsIgnoreCase("buy")){
            String[] tokens = command.split("\\s+");

            String product = tokens[0];
            double price = Double.parseDouble(tokens[1]);
            int quantity = Integer.parseInt(tokens[2]);

            if (!productsQuantity.containsKey(product)){
                productsQuantity.put(product, quantity);
                productsPrice.put(product, price);
            } else {
                int oldQuantity = productsQuantity.get(product);
                productsQuantity.put(product, oldQuantity + quantity);
                productsPrice.put(product, price);
            }
            command = scanner.nextLine();
        }

        for (Map.Entry<String, Double> entry : productsPrice.entrySet()) {
            String product = entry.getKey();
            Double price = entry.getValue();
            System.out.println(String.format("%s -> %.2f", product,
                    productsQuantity.get(product) * price));
        }
    }
}
