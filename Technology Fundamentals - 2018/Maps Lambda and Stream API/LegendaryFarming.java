import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class LegendaryFarming {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        boolean isObtained = false;

        Map<String, Integer> keyMaterials = new HashMap<>();
        Map<String, Integer> junk = new TreeMap<>();

        keyMaterials.put("shards", 0);
        keyMaterials.put("fragments", 0);
        keyMaterials.put("motes", 0);

        while (!isObtained){
            String[] tokens = scanner.nextLine().split("\\s+");

            for (int i = 0; i < tokens.length; i+=2) {
                int quantity = Integer.parseInt(tokens[i]);
                String material = tokens[i + 1].toLowerCase();

                if (material.equals("shards") || material.equals("fragments") || material.equals("motes")){
                    keyMaterials.put(material, keyMaterials.get(material) + quantity);

                    if (keyMaterials.get(material) >= 250){
                        keyMaterials.put(material, keyMaterials.get(material) - 250);

                        if (material.equals("motes")){
                            System.out.println("Dragonwrath obtained!");
                        } else if (material.equals("shards")){
                            System.out.println("Shadowmourne obtained!");
                        } else {
                            System.out.println("Valanyr obtained!");
                        }

                        isObtained = true;
                        break;
                    }
                } else{
                    if (!junk.containsKey(material)){
                        junk.put(material, 0);
                    }

                    junk.put(material, junk.get(material) + quantity);
                }
            }
        }

        keyMaterials.entrySet().stream()
                .sorted((e1, e2) -> {
            int compareResult = e2.getValue().compareTo(e1.getValue());
            if (compareResult == 0){
                return e1.getKey().compareTo(e2.getKey());
            }
            return compareResult;
        })
                .forEach(entry -> System.out.println(String.format("%s: %d", entry.getKey(), entry.getValue())));

        junk.forEach((key , value) -> System.out.println(String.format("%s: %d", key, value)));
    }
}
