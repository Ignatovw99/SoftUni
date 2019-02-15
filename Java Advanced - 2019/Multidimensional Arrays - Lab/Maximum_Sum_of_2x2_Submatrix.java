import java.util.Arrays;
import java.util.Scanner;

public class Maximum_Sum_of_2x2_Submatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = Arrays.stream(scanner.nextLine().split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[][] matrix = readMatrix(dimensions, scanner);

        int biggestSum = Integer.MIN_VALUE;
        int currentSum = 0;
        int[] topLeftIndex = new int[2];

        for (int row = 0; row < matrix.length - 1; row++) {
            for (int col = 0; col < matrix[row].length - 1; col++) {
                currentSum = matrix[row][col] + matrix[row][col + 1] + matrix[row + 1][col] + matrix[row + 1][col + 1];

                if (currentSum > biggestSum) {
                    biggestSum = currentSum;
                    topLeftIndex[0] = row;
                    topLeftIndex[1] = col;
                }
            }
        }

        printSubmatrix(matrix, topLeftIndex);
        System.out.println(biggestSum);
    }

    private static void printSubmatrix(int[][] matrix, int[] topLeftIndex) {
        int row = topLeftIndex[0];
        int col = topLeftIndex[1];
        for (int i = row; i <= row + 1; i++) {
            for (int j = col; j <= col + 1; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int[][] readMatrix(int[] dimensions, Scanner scanner) {
        int rows = dimensions[0];
        int cols = dimensions[1];

        int[][] matrix = new int[rows][cols];

        for (int row = 0; row < matrix.length; row++) {
            int[] lineElements = Arrays.stream(scanner.nextLine().split(", "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int col = 0; col < matrix[row].length; col++) {
                matrix[row][col] = lineElements[col];
            }
        }

        return matrix;
    }
}
