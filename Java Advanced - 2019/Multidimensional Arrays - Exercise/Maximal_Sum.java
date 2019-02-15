import java.util.Arrays;
import java.util.Scanner;

public class Maximal_Sum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] input = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int rows = input[0];
        int cols = input[1];

        int[][] matrix = new int[rows][cols];

        fillMatrix(matrix, scanner);

        printMaximalSum(matrix);
    }

    private static void printMaximalSum(int[][] matrix) {
        int maximalSum = Integer.MIN_VALUE;
        int currentSum = 0;
        int[][] submatrix = new int[3][3];
        for (int row = 1; row < matrix.length - 1; row++) {
            for (int col = 1; col < matrix[row].length - 1; col++) {
                currentSum = getElementsSumOfCurrentMatrix(matrix, row, col);
                if (currentSum > maximalSum) {
                    maximalSum = currentSum;
                    rewriteSubmatrix(matrix, submatrix, row, col);
                }
            }
        }

        System.out.println("Sum = " + maximalSum);

        for (int row = 0; row < submatrix.length; row++) {
            for (int col = 0; col < submatrix[row].length; col++) {
                System.out.print(submatrix[row][col] + " ");
            }
            System.out.println();
        }
    }

    private static void rewriteSubmatrix(int[][] matrix, int[][] submatrix, int centralRow, int centralCol) {
        int submatrixRow = 0;
        int submatrixCol = 0;
        for (int row = centralRow - 1; row <= centralRow + 1; row++) {
            submatrixCol = 0;
            for (int col = centralCol - 1; col <= centralCol + 1; col++) {
                submatrix[submatrixRow][submatrixCol] = matrix[row][col];
                submatrixCol++;
            }
            submatrixRow++;
        }
    }

    private static int getElementsSumOfCurrentMatrix(int[][] matrix, int centralRow, int centralCol) {
        return matrix[centralRow - 1][centralCol - 1] + matrix[centralRow][centralCol - 1] + matrix[centralRow + 1][centralCol - 1]
                + matrix[centralRow - 1][centralCol] + matrix[centralRow][centralCol] + matrix[centralRow + 1][centralCol]
                + matrix[centralRow - 1][centralCol + 1] + matrix[centralRow][centralCol + 1] + matrix[centralRow + 1][centralCol + 1];
    }

    private static void fillMatrix(int[][] matrix, Scanner scanner) {
        for (int row = 0; row < matrix.length; row++) {
            int[] elements = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int col = 0; col < matrix[row].length; col++) {
                matrix[row][col] = elements[col];
            }
        }
    }
}
