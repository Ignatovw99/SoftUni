import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Miner_Task {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> resources = new LinkedHashMap<>();

        String cmd = scanner.nextLine();

        while (!cmd.equalsIgnoreCase("stop")){
            String resource = cmd;
            int quantity = Integer.parseInt(scanner.nextLine());
            if (!resources.containsKey(resource)){
                resources.put(resource, quantity);
            } else {
                resources.put(resource, resources.get(resource) + quantity);
            }
            cmd = scanner.nextLine();
        }

        resources
                .forEach((resource, quantity) -> System.out.println(String.format("%s -> %d", resource, quantity)));
    }
}
