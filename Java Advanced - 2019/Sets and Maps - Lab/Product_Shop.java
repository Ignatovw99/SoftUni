import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Product_Shop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Map<String, Double>> productsByShops = new TreeMap<>();

        String command = scanner.nextLine();

        while (!"Revision".equals(command)) {
            String[] tokens = command.split(", ");
            String shop = tokens[0];
            String product = tokens[1];
            double price = Double.parseDouble(tokens[2]);

            if (!productsByShops.containsKey(shop)) {
                productsByShops.put(shop, new LinkedHashMap<>());
            }
            productsByShops.get(shop).put(product, price);
            command = scanner.nextLine();
        }

        for (Map.Entry<String, Map<String, Double>> entry : productsByShops.entrySet()) {
            System.out.println(String.format("%s->", entry.getKey()));
            for (Map.Entry<String, Double> products : productsByShops.get(entry.getKey()).entrySet()) {
                System.out.println(String.format("Product: %s, Price: %.1f", products.getKey(), products.getValue()));
            }
        }
    }
}
