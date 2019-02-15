import java.util.Arrays;
import java.util.Scanner;

public class The_Heigan_Dance {
    public static final int min = 0;
    public static final int max = 14;
    public static double damageToHeigan;
    public static int[] playerPosition = {7, 7};
    public static int plagueCloudDamage = 3500;
    public static int eruptionDamage = 6000;
    public static int playerPoints = 18500;
    public static double heiganPoints = 3000000;
    public static boolean wasPreviousTurnSpellCloud = false;
    public static String causeOfDead;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        damageToHeigan = Double.parseDouble(scanner.nextLine());

        while (true) {
            heiganPoints -= damageToHeigan;

            if (wasPreviousTurnSpellCloud) {
                playerPoints -= plagueCloudDamage;
                causeOfDead = "Plague Cloud";
                wasPreviousTurnSpellCloud = false;
            }

            if (playerPoints <= 0 || heiganPoints <= 0) {
                break;
            }

            String[] inputTokens = scanner.nextLine().split("\\s+");
            String spell = inputTokens[0];
            int[] hitCell = {Integer.parseInt(inputTokens[1]), Integer.parseInt(inputTokens[2])};

            int[][] damageArea = findDamageArea(hitCell);

            if (isPlayerInsideDamageArea(damageArea, playerPosition[0], playerPosition[1])) {
                movePlayer(damageArea, spell);
            }
            if (playerPoints <= 0 || heiganPoints <= 0) {
                break;
            }
        }
        printResult();
    }

    private static void printResult() {
        String heiganState = heiganPoints > 0 ? String.format("%.2f", heiganPoints) : "Defeated!";
        String playerState = playerPoints > 0 ? String.format("%d", playerPoints) : "Killed by " + causeOfDead;
        System.out.println("Heigan: " + heiganState);
        System.out.println("Player: " + playerState);
        System.out.println(String.format("Final position: %d, %d", playerPosition[0], playerPosition[1]));
    }

    private static int[][] findDamageArea(int[] hitCell) {
        int[][] matrix = new int[2][];

        //Get damaged rows
        matrix[0] = new int[3];
        for (int row = 0; row < 3; row++) {
            matrix[0][row] = hitCell[0] + row - 1;
        }

        //Get damaged cols
        matrix[1] = new int[3];
        for (int col = 0; col < 3; col++) {
            matrix[1][col] = hitCell[1] + col - 1;
        }

        return matrix;
    }

    private static boolean isPlayerInsideDamageArea(int[][] damagedArea, int playerRow, int playerCol) {
        boolean insideDamagedRows = false;
        for (int row = 0; row < damagedArea[0].length; row++) {
            if (playerRow == damagedArea[0][row]) {
                insideDamagedRows = true;
                break;
            }
        }
        boolean insideDamagedCols = false;
        for (int col = 0; col < damagedArea[1].length; col++) {
            if (playerCol == damagedArea[1][col]) {
                insideDamagedCols = true;
                break;
            }
        }

        return insideDamagedRows && insideDamagedCols;
    }

    private static void movePlayer(int[][] damageArea, String spell) {
        boolean playerCanMove = true;

        int rowAbove = playerPosition[0] - 1;
        int rowBelow = playerPosition[0] + 1;
        int rightCol = playerPosition[1] + 1;
        int leftCol = playerPosition[1] - 1;

        boolean canMoveUp = !isPlayerInsideDamageArea(damageArea, rowAbove, playerPosition[1]) && isPositionInsideChamber(rowAbove, playerPosition[1]);
        boolean canMoveRight = !isPlayerInsideDamageArea(damageArea, playerPosition[0], rightCol) && isPositionInsideChamber(playerPosition[0], rightCol);
        boolean canMoveDown = !isPlayerInsideDamageArea(damageArea, rowBelow, playerPosition[1]) && isPositionInsideChamber(rowBelow, playerPosition[1]);
        boolean canMoveLeft = !isPlayerInsideDamageArea(damageArea, playerPosition[0], leftCol) && isPositionInsideChamber(playerPosition[0], leftCol);

        if (canMoveUp) {
            playerPosition[0] = rowAbove;
        } else if (canMoveRight) {
            playerPosition[1] = rightCol;
        } else if (canMoveDown) {
            playerPosition[0] = rowBelow;
        } else if (canMoveLeft) {
            playerPosition[1] = leftCol;
        } else {
            playerCanMove = false;
        }

        //Take damage
        if (!playerCanMove) {
            switch (spell) {
                case "Cloud":
                    playerPoints -= plagueCloudDamage;
                    causeOfDead = "Plague Cloud";
                    wasPreviousTurnSpellCloud = true;
                    break;
                case "Eruption":
                    playerPoints -= eruptionDamage;
                    causeOfDead = "Eruption";
                    break;
            }
        }
    }

    private static boolean isPositionInsideChamber(int row, int col) {
        return row >= min && row <= max && col >= min && col <= max;
    }
}
