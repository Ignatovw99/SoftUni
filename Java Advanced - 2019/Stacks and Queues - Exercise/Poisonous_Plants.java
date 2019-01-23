import java.util.*;

public class Poisonous_Plants {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int plantsCount = Integer.parseInt(scanner.nextLine());

        int[] plants = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();


        int[] daysPlantsDie = new int[plantsCount];
        ArrayDeque<Integer> previousPlants = new ArrayDeque<>();
        previousPlants.push(0);

        for (int i = 1; i < plantsCount; i++) {
            int lastDay = 0;

            while (previousPlants.size() > 0 && plants[previousPlants.peek()] >= plants[i]) {
                lastDay = Math.max(lastDay, daysPlantsDie[previousPlants.pop()]);
            }

            if (previousPlants.size() > 0) {
                daysPlantsDie[i] = lastDay + 1;
            }
            previousPlants.push(i);
        }

        System.out.println(Arrays.stream(daysPlantsDie).max().getAsInt());
    }
}
