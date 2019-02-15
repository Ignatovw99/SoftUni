import java.util.Scanner;

public class Fill_the_Matrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split(", ");

        int dimensions = Integer.parseInt(input[0]);

        int[][] matrix = new int[dimensions][dimensions];

        String pattern = input[1];

        if (pattern.equals("A")) {
            fillMatrixWithFirstPattern(matrix);
        } else if (pattern.equals("B")) {
            fillMatrixWithSecondPattern(matrix);
        }

        printMatrix(matrix);
    }

    public static void printMatrix(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }

    public static void printMatrix(String[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }

    private static void fillMatrixWithSecondPattern(int[][] matrix) {
        int value = 1;

        for (int col = 0; col < matrix[0].length; col++) {
            if (col % 2 == 0) {
                value = fillColumnNormally(matrix, col, value);
            } else {
                value = fillColumnReversely(matrix, col, value);
            }
        }
    }

    private static int fillColumnReversely(int[][] matrix, int col, int value) {
        for (int row = matrix.length - 1; row >= 0; row--) {
            matrix[row][col] = value;
            value += 1;
        }
        return value;
    }

    private static int fillColumnNormally(int[][] matrix, int col, int value) {
        for (int row = 0; row < matrix.length; row++) {
            matrix[row][col] = value;
            value += 1;
        }
        return value;
    }

    private static void fillMatrixWithFirstPattern(int[][] matrix) {
        int value = 1;
        for (int col = 0; col < matrix[0].length; col++) {
            for (int row = 0; row < matrix.length; row++) {
                matrix[row][col] = value;
                value += 1;
            }
        }
    }
}
