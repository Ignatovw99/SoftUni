import java.util.Arrays;
import java.util.Scanner;

public class LadyBugs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int fieldSize = Integer.parseInt(scanner.nextLine());
        int[] field = new int[fieldSize];

        int[] ladybugsPositions = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        for (int i = 0; i < ladybugsPositions.length; i++) {
            if (ladybugsPositions[i] >= 0 && ladybugsPositions[i] < fieldSize){
                field[ladybugsPositions[i]] = 1;
            }
        }

        String command = scanner.nextLine();

        while (!command.equalsIgnoreCase("end")){
            String[] tokens = command.split("\\s+");
            int ladybugIndex = Integer.parseInt(tokens[0]);
            String direction = tokens[1];
            int flyLength = Integer.parseInt(tokens[2]);

            if (ladybugIndex >= 0 && ladybugIndex < fieldSize && field[ladybugIndex] == 1){
                if (flyLength < 0) {
                    if (direction.equals("right")) {
                        direction = "left";
                    } else if (direction.equals("left")) {
                        direction = "right";
                    }
                    flyLength = flyLength * (-1);
                }

                switch (direction){
                    case "right":
                        flyRight(field, ladybugIndex, flyLength);
                        break;
                    case "left":
                        flyLeft(field, ladybugIndex, flyLength);
                        break;
                }
            }

            command = scanner.nextLine();
        }
        Arrays.stream(field)
                .forEach(l -> System.out.print(l + " "));
    }

    private static void flyLeft(int[] field, int ladybugIndex, int flyLength) {
        field[ladybugIndex] = 0;
        int index = ladybugIndex;
        do {
            index = index - flyLength;
            if (index >= 0 && index < field.length){
                if (field[index] == 0){
                    field[index] = 1;
                    break;
                }
            }
        } while (index >= 0);
    }

    private static void flyRight(int[] field, int ladybugIndex, int flyLength) {
        field[ladybugIndex] = 0;
        int index = ladybugIndex;
        do {
            index = index + flyLength;
            if (index >= 0 && index < field.length){
                if (field[index] == 0){
                    field[index] = 1;
                    break;
                }
            }
        } while (index < field.length);
    }
}
