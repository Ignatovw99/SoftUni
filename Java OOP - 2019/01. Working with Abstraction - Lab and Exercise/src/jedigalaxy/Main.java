package jedigalaxy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        Galaxy galaxy = new Galaxy(dimensions[0], dimensions[1]);

        StarsManipulator starsManipulator = new StarsManipulator(galaxy);

        String command = scanner.nextLine();
        long totalSumOfCollectedStars = 0;
        while (!command.equals("Let the Force be with you")) {
            int[] playerPosition = Arrays.stream(command.split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int[] evilPosition = Arrays.stream(scanner.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            starsManipulator.destroyStars(evilPosition);

            totalSumOfCollectedStars += starsManipulator.sumOfStars(playerPosition);

            command = scanner.nextLine();
        }

        System.out.println(totalSumOfCollectedStars);
    }
}
