import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Dragon_Army {
    private static final int defaultHealth = 250;
    private static final int defaultDamage = 45;
    private static final int defaultArmor = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Map<String, int[]>> dragonTypes = new LinkedHashMap<>();
        int dragonsCount = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < dragonsCount; i++) {
            String[] dragonInfo = scanner.nextLine().split("\\s+");

            String dragonType = dragonInfo[0];
            String dragonName = dragonInfo[1];
            int health = defaultHealth;
            int damage = defaultDamage;
            int armor = defaultArmor;

            if (!dragonInfo[2].equals("null")) {
                damage = Integer.parseInt(dragonInfo[2]);
            }
            if (!dragonInfo[3].equals("null")) {
                health = Integer.parseInt(dragonInfo[3]);
            }
            if (!dragonInfo[4].equals("null")) {
                armor = Integer.parseInt(dragonInfo[4]);
            }

            if (!dragonTypes.containsKey(dragonType)) {
                dragonTypes.put(dragonType, new TreeMap<>(){{put(dragonName, new int[3]);}});
                setDragonCharacteristic(dragonTypes.get(dragonType).get(dragonName), damage, health, armor);
            } else {
                if (!dragonTypes.get(dragonType).containsKey(dragonName)) {
                    dragonTypes.get(dragonType).put(dragonName, new int[3]);
                    setDragonCharacteristic(dragonTypes.get(dragonType).get(dragonName), damage, health, armor);
                } else {
                    setDragonCharacteristic(dragonTypes.get(dragonType).get(dragonName), damage, health, armor);
                }
            }
        }

        for (Map.Entry<String, Map<String, int[]>> entry : dragonTypes.entrySet()) {
            String type = entry.getKey();
            Map<String, int[]> namesAndStates = entry.getValue();
            //Get average values with Stream API -> double aver = namesAndStats.entrySet().stream().mapToDouble(x -> x.getValue()[1]).average().getAsDouble();
            double[] dragonTypeAverageStates = getDragonTypeAverageStates(namesAndStates);

            double averageDamage = dragonTypeAverageStates[0];
            double averageHealth = dragonTypeAverageStates[1];
            double averageArmor = dragonTypeAverageStates[2];

            System.out.println(String.format("%s::(%.2f/%.2f/%.2f)", type, averageDamage, averageHealth, averageArmor));
            namesAndStates.forEach((dragon, dragonCharacteristic) ->
                    System.out.println(String.format("-%s -> damage: %d, health: %d, armor: %d", dragon, dragonCharacteristic[0], dragonCharacteristic[1], dragonCharacteristic[2])));
        }
    }

    private static double[] getDragonTypeAverageStates(Map<String, int[]> namesAndStates) {
        double[] averageValues = new double[3];
        int dragonsCount = namesAndStates.size();
        double totalDamage = 0;
        double totalHealth = 0;
        double totalArmor = 0;

        for (int[] dragonCharacteristic : namesAndStates.values()) {
            totalDamage += dragonCharacteristic[0];
            totalHealth += dragonCharacteristic[1];
            totalArmor += dragonCharacteristic[2];
        }

        averageValues[0] = totalDamage / dragonsCount;
        averageValues[1] = totalHealth / dragonsCount;
        averageValues[2] = totalArmor / dragonsCount;

        return averageValues;
    }

    private static void setDragonCharacteristic(int[] array, int damage, int health, int armor) {
        array[0] = damage;
        array[1] = health;
        array[2] = armor;
    }
}
