import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Legendary_Farming {
    private static int  TARGET_MARKS = 250;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> keyMaterials = new TreeMap<>() {
            {
                put("shards", 0);
                put("fragments", 0);
                put("motes", 0);
            }
        };
        Map<String, Integer> junk = new TreeMap<>();

        while (true) {
            String[] lineElements = scanner.nextLine().split("\\s+");
            boolean isObtained = false;
            String obtainedItem = "";
            for (int i = 0; i < lineElements.length; i += 2) {
                int quantity = Integer.parseInt(lineElements[i]);
                String material = lineElements[i + 1].toLowerCase();

                if (keyMaterials.containsKey(material)) {
                    int previousQuantity = keyMaterials.get(material);
                    keyMaterials.put(material, previousQuantity + quantity);
                    if (keyMaterials.get(material) >= TARGET_MARKS) {
                        keyMaterials.put(material, keyMaterials.get(material) - TARGET_MARKS);
                        switch (material) {
                            case "shards":
                                obtainedItem = "Shadowmourne";
                                break;
                            case "fragments":
                                obtainedItem = "Valanyr";
                                break;
                            case "motes":
                                obtainedItem = "Dragonwrath";
                                break;
                        }
                        isObtained = true;
                        break;
                    }
                } else {
                    if (!junk.containsKey(material)) {
                        junk.put(material, quantity);
                    } else {
                        junk.put(material, junk.get(material) + quantity);
                    }
                }
            }
            if (isObtained) {
                System.out.println(String.format("%s obtained!", obtainedItem));
                break;
            }
        }
        keyMaterials.entrySet().stream()
                .sorted((material1, material2) -> material2.getValue().compareTo(material1.getValue()))
                .forEach(entry -> System.out.println(String.format("%s: %d", entry.getKey(), entry.getValue())));
        junk.forEach((material, quantity) -> System.out.printf("%s: %d\n", material, quantity));
    }
}
