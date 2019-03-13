package rectangle;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] coordinates = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        Point bottomLeftPoint = new Point(coordinates[0], coordinates[1]);
        Point topRightPoint = new Point(coordinates[2], coordinates[3]);

        Rectangle rectangle = new Rectangle(bottomLeftPoint, topRightPoint);

        int pointsCount = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < pointsCount; i++) {
            int[] pointCoords = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            Point point = new Point(pointCoords[0], pointCoords[1]);
            System.out.println(rectangle.contains(point));
        }
    }
}
