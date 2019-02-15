import java.util.Arrays;
import java.util.Scanner;

public class Radioactive_Mutant_Vampire_Bunnies {
    public static char[][] lair;
    public static boolean[][]  occupiedCells;
    public static int playerRow;
    public static int playerCol;
    public static boolean isPlayerDead = false;
    public static boolean isPlayerOutsideLair = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] dimensions = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        setLair(dimensions, scanner);
        String listOfCommands = scanner.nextLine();

        for (int i = 0; i < listOfCommands.length(); i++) {
            char currentCommand = listOfCommands.charAt(i);

            movePlayer(currentCommand);
            spreadBunnies();

            if (isPlayerDead) {
                printLairState();
                System.out.println(String.format("dead: %d %d", playerRow, playerCol));
                break;
            }
            if (isPlayerOutsideLair) {
                printLairState();
                System.out.println(String.format("won: %d %d", playerRow, playerCol));
                break;
            }
        }
    }

    private static void printLairState() {
        for (int row = 0; row < lair.length; row++) {
            for (int col = 0; col < lair[row].length; col++) {
                System.out.print(lair[row][col]);
            }
            System.out.println();
        }
    }

    private static void spreadBunnies() {
        for (int row = 0; row < lair.length; row++) {
            for (int col = 0; col < lair[row].length; col++) {
                if (occupiedCells[row][col]) {
                    int upRow = row - 1;
                    if (isIndexInsideLair(upRow, col)) {
                        if (isPlayerOccupiedThisPosition(upRow, col)) {
                            isPlayerDead = true;
                        }
                        lair[upRow][col] = 'B';
                    }
                    int downRow = row + 1;
                    if (isIndexInsideLair(downRow, col)) {
                        if (isPlayerOccupiedThisPosition(downRow, col)) {
                            isPlayerDead = true;
                        }
                        lair[downRow][col] = 'B';
                    }
                    int leftCol = col - 1;
                    if (isIndexInsideLair(row, leftCol)) {
                        if (isPlayerOccupiedThisPosition(row, leftCol)) {
                            isPlayerDead = true;
                        }
                        lair[row][leftCol] = 'B';
                    }
                    int rightCol = col + 1;
                    if (isIndexInsideLair(row, rightCol)) {
                        if (isPlayerOccupiedThisPosition(row, rightCol)){
                            isPlayerDead = true;
                        }
                        lair[row][rightCol] = 'B';
                    }
                }
            }
        }
        occupyFreeCells();
    }

    private static void occupyFreeCells() {
        for (int row = 0; row < lair.length; row++) {
            for (int col = 0; col < lair[row].length; col++) {
                if (lair[row][col] == 'B') {
                    occupiedCells[row][col] = true;
                }
            }
        }
    }

    private static boolean isPlayerOccupiedThisPosition(int row, int col) {
        return row == playerRow && col == playerCol && !isPlayerOutsideLair;
    }

    private static void movePlayer(char direction) {
        int newPlayerRow = playerRow;
        int newPlayerCol = playerCol;
        switch (direction) {
            case 'U':
                newPlayerRow -= 1;
                break;
            case 'D':
                newPlayerRow += 1;
                break;
            case 'L':
                newPlayerCol -= 1;
                break;
            case 'R':
                newPlayerCol += 1;
                break;
        }

        lair[playerRow][playerCol] = '.';

        if (isPlayerNewPositionBunnyCell(newPlayerRow, newPlayerCol)) {
            playerRow = newPlayerRow;
            playerCol = newPlayerCol;
            isPlayerDead = true;
        } else {
            if (isPlayerNewPositionOutsideLair(newPlayerRow, newPlayerCol)) {
                isPlayerOutsideLair = true;
                return;
            }
            playerRow = newPlayerRow;
            playerCol = newPlayerCol;
            lair[playerRow][playerCol] = 'P';
        }
    }

    private static boolean isPlayerNewPositionOutsideLair(int newPlayerRow, int newPlayerCol) {
        return !isIndexInsideLair(newPlayerRow, newPlayerCol);
    }

    private static boolean isPlayerNewPositionBunnyCell(int newPlayerRow, int newPlayerCol) {
        if (isIndexInsideLair(newPlayerRow, newPlayerCol)) {
            if (occupiedCells[newPlayerRow][newPlayerCol]) {
                return true;
            }
        }

        return false;
    }

    private static boolean isIndexInsideLair(int row, int col) {
        return row >= 0 && row < lair.length && col >= 0 && col < lair[0].length;
    }

    private static void setLair(int[] dimensions, Scanner scanner) {
        lair = new char[dimensions[0]][dimensions[1]];
        occupiedCells = new boolean[dimensions[0]][dimensions[1]];

        for (int row = 0; row < lair.length; row++) {
            String lineElements = scanner.nextLine();
            for (int col = 0; col < lair[row].length; col++) {
                lair[row][col] = lineElements.charAt(col);
                if (lineElements.charAt(col) == 'B') {
                    occupiedCells[row][col] = true;
                } else if (lineElements.charAt(col) == 'P'){
                    playerRow = row;
                    playerCol = col;
                }
            }
        }
    }
}
