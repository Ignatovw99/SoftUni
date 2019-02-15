import java.util.Arrays;
import java.util.Scanner;

public class Parking_System {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] parkingLotDimensions = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        boolean[][] parkingLotState = new boolean[parkingLotDimensions[0]][parkingLotDimensions[1]];

        String command = scanner.nextLine();

        while (!"stop".equals(command)) {
            int[] tokens = Arrays.stream(command.split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            parkCar(parkingLotState, tokens);

            command = scanner.nextLine();
        }
    }

    private static void parkCar(boolean[][] parkingLotState, int[] tokens) {
        int entryRow = tokens[0];
        int desiredRow = tokens[1];
        int desiredCol = tokens[2];
        int distanceToParkingSpot;
        if (isDesiredParkingSpotFree(parkingLotState, desiredRow, desiredCol)) {
            parkingLotState[desiredRow][desiredCol] = true;
            distanceToParkingSpot = calculateDistance(entryRow, desiredRow, desiredCol);
            System.out.println(distanceToParkingSpot);
        } else {
            if (areAllParkingSpotsOnRowBusy(parkingLotState, desiredRow)) {
                System.out.println(String.format("Row %d full", desiredRow));
            } else {
                //The first index is for row and the second one is for col in the matrix
                int[] coordinatesOfClosestSpot = findClosestParkingSpot(parkingLotState, desiredRow, desiredCol);
                parkingLotState[coordinatesOfClosestSpot[0]][coordinatesOfClosestSpot[1]] = true;
                distanceToParkingSpot = calculateDistance(entryRow, coordinatesOfClosestSpot[0], coordinatesOfClosestSpot[1]);
                System.out.println(distanceToParkingSpot);
            }
        }
    }

    private static int calculateDistance(int entryRow, int row, int col) {
        return Math.abs(entryRow - row) + col + 1;
    }

    private static int[] findClosestParkingSpot(boolean[][] parking, int row, int col) {
        int[] coordinates = new int[2];
        int distanceToLeftClosestSpot = 0;
        boolean areThereFreeSpotLeft = false;
        boolean areThereFreeSpotRight = false;
        int distanceToRightClosestSpot = 0;
        //Check for closest free parking spot right from initial spot
        for (int i = col + 1; i < parking[row].length; i++) {
            if (!parking[row][i]) {
                distanceToRightClosestSpot = Math.abs(col - i);
                areThereFreeSpotRight = true;
                break;
            }
        }
        //Check for closest free parking spot left from initial spot
        for (int i = col - 1; i >= 1; i--) {
            if (!parking[row][i]) {
                distanceToLeftClosestSpot = Math.abs(col - i);
                areThereFreeSpotLeft = true;
                break;
            }
        }

        if (areThereFreeSpotLeft && areThereFreeSpotRight) {
            //If the free closest parking spot is on the left side from initial spot or the distance between left and right spot is equal
            if (distanceToLeftClosestSpot <= distanceToRightClosestSpot) {
                coordinates[0] = row;
                coordinates[1] = col - distanceToLeftClosestSpot;
            } else {
                coordinates[0] = row;
                coordinates[1] = col + distanceToRightClosestSpot;
            }
        } else if (areThereFreeSpotLeft) {
            coordinates[0] = row;
            coordinates[1] = col - distanceToLeftClosestSpot;
        } else {
            coordinates[0] = row;
            coordinates[1] = col + distanceToRightClosestSpot;
        }

        return coordinates;
    }

    private static boolean areAllParkingSpotsOnRowBusy(boolean[][] parking, int row) {
        for (int i = 1; i < parking[row].length; i++) {
            if (!parking[row][i]) {
                return false;
            }
        }
        return true;
    }

    private static boolean isDesiredParkingSpotFree(boolean[][] parking, int row, int col) {
        return !parking[row][col];
    }
}
