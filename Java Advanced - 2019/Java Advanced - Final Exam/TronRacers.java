import java.util.Scanner;

public class TronRacers {
    private static char[][] field;
    private static int[] firstPlayerPosition;
    private static int[] secondPlayerPosition;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());

        field = initializeField(scanner, size);

        String command;

        while (true) {
            command = scanner.nextLine();
            
            String[] directions = command.split("\\s+");
            String firstPlayerDirection = directions[0];
            String secondPlayerDirection = directions[1];

            movePlayers(firstPlayerDirection, secondPlayerDirection);

            if (isFirstPlayerDead()) {
                field[firstPlayerPosition[0]][firstPlayerPosition[1]] = 'x';
                break;
            }
            field[firstPlayerPosition[0]][firstPlayerPosition[1]] = 'f';
            if (isSecondPlayerDead()) {
                field[secondPlayerPosition[0]][secondPlayerPosition[1]] = 'x';
                break;
            }
            field[secondPlayerPosition[0]][secondPlayerPosition[1]] = 's';
        }
        printField();
    }

    private static void printField() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
    }

    private static boolean isSecondPlayerDead() {
        return field[secondPlayerPosition[0]][secondPlayerPosition[1]] == 'f';
    }

    private static boolean isFirstPlayerDead() {
        return field[firstPlayerPosition[0]][firstPlayerPosition[1]] == 's';
    }

    private static void movePlayers(String firstPlayerDirection, String secondPlayerDirection) {
        int[] firstPlayerNewPosition = setPlayerPosition(firstPlayerDirection, "first player");
        int[] secondPlayerNewPosition = setPlayerPosition(secondPlayerDirection, "second player");

        firstPlayerPosition = firstPlayerNewPosition;
        secondPlayerPosition = secondPlayerNewPosition;
    }

    private static int[] setPlayerPosition(String direction, String player) {
        int[] position = new int[2];  // index 0 is for row, index 1 is for col

        if (player.equals("first player")) {
            position[0] = firstPlayerPosition[0];
            position[1] = firstPlayerPosition[1];
        } else if (player.equals("second player")) {
            position[0] = secondPlayerPosition[0];
            position[1] = secondPlayerPosition[1];
        }

        switch (direction) {
            case "up":
                position[0] -= 1;
                break;
            case "down":
                position[0] += 1;
                break;
            case "right":
                position[1] += 1;
                break;
            case "left":
                position[1] -= 1;
                break;
        }

        if (position[0] < 0) {
            position[0] = field.length - 1;
        } else if (position[0] >= field.length){
            position[0] = 0;
        }

        if (position[1] < 0) {
            position[1] = field[0].length - 1;
        } else if (position[1] >= field[0].length) {
            position[1] = 0;
        }

        return position;
    }

    private static char[][] initializeField(Scanner scanner, int size) {
        char[][] matrix = new char[size][size];

        for (int row = 0; row < matrix.length; row++) {
            String line = scanner.nextLine();
            for (int col = 0; col < matrix[0].length; col++) {
                matrix[row][col] = line.charAt(col);
                if (matrix[row][col] == 'f') {
                    firstPlayerPosition = new int[] {row, col};
                } else if (matrix[row][col] == 's') {
                    secondPlayerPosition = new int[] {row, col};
                }
            }
        }
        return matrix;
    }
}
