import java.util.Arrays;
import java.util.Scanner;

public class Matrix_Shuffling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] dimensions = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(e -> Integer.parseInt(e))
                .toArray();

        String[][] matrix = readMatrix(dimensions, scanner);
        String command = scanner.nextLine();

        while (!"END".equals(command)) {
            String[] inputData = command.split("\\s+");
            if (checkInputData(inputData, matrix)) {
                int rowFirstElement = Integer.parseInt(inputData[1]);
                int colFirstElement = Integer.parseInt(inputData[2]);
                int rowSecondElement = Integer.parseInt(inputData[3]);
                int colSecondElement = Integer.parseInt(inputData[4]);

                swapElements(matrix, rowFirstElement, colFirstElement, rowSecondElement, colSecondElement);

                printMatrix(matrix);
            } else {
                System.out.println("Invalid input!");
            }
            command = scanner.nextLine();
        }
    }

    private static void printMatrix(String[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }

    private static void swapElements(String[][] matrix, int rowFirstElement, int colFirstElement, int rowSecondElement, int colSecondElement) {
        String temp = matrix[rowFirstElement][colFirstElement];
        matrix[rowFirstElement][colFirstElement] = matrix[rowSecondElement][colSecondElement];
        matrix[rowSecondElement][colSecondElement] = temp;
    }

    private static boolean checkInputData(String[] inputData, String[][] matrix) {
        if (inputData.length == 5) {
            String command = inputData[0];
            if ("swap".equals(command) && tryParseInt(inputData[1]) && tryParseInt(inputData[2]) && tryParseInt(inputData[3]) &&
                    tryParseInt(inputData[4])) {
                int row1 = Integer.parseInt(inputData[1]);
                int col1 = Integer.parseInt(inputData[2]);
                int row2 = Integer.parseInt(inputData[3]);
                int col2 = Integer.parseInt(inputData[4]);

                if (isIndexInsideMatrix(row1, col1, matrix) && isIndexInsideMatrix(row2, col2, matrix)) {
                    return true;
                }
            } else {
                return false;
            }
        }

        return false;
    }

    private static boolean isIndexInsideMatrix(int row, int col, String[][] matrix) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length;
    }

    private static boolean tryParseInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static String[][] readMatrix(int[] dimensions, Scanner sc) {
        String[][] matrix = new String[dimensions[0]][dimensions[1]];

        for (int row = 0; row < matrix.length; row++) {
            String[] lineElements = sc.nextLine().split("\\s+");
            for (int col = 0; col < matrix[row].length; col++) {
                matrix[row][col] = lineElements[col];
            }
        }

        return matrix;
    }
}
