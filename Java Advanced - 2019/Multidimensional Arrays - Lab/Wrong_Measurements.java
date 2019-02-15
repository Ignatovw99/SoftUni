import java.util.Arrays;
import java.util.Scanner;

public class Wrong_Measurements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        int[][] matrix = new int[n][];

        for (int i = 0; i < n; i++) {
            int[] row = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            matrix[i] = row;
        }

        String[] coordinates = scanner.nextLine().split("\\s+");
        int x = Integer.parseInt(coordinates[0]);
        int y = Integer.parseInt(coordinates[1]);

        int wrongValue = matrix[x][y];
        int[][] resultMatrix = new int[n][];

        for (int i = 0; i < matrix.length; i++) {
            resultMatrix[i] = new int[matrix[i].length];
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != wrongValue) {
                    resultMatrix[i][j] = matrix[i][j];
                } else {
                    resultMatrix[i][j] = getSum(matrix, i, j, wrongValue);
                }
            }
        }

        //Print result matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(resultMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int getSum(int[][] matrix, int row, int col, int wrongValue) {
        int sum = 0;

        int rowAbove = row - 1;
        int rowBelow = row + 1;
        int colRight = col + 1;
        int colLeft = col - 1;

        if (isIndexInsideMatrix(matrix, rowAbove, col) && matrix[rowAbove][col] != wrongValue) {
            sum += matrix[rowAbove][col];
        }
        if (isIndexInsideMatrix(matrix, row, colRight) && matrix[row][colRight] != wrongValue) {
            sum += matrix[row][colRight];
        }
        if (isIndexInsideMatrix(matrix, rowBelow, col) && matrix[rowBelow][col] != wrongValue) {
            sum += matrix[rowBelow][col];
        }
        if (isIndexInsideMatrix(matrix, row, colLeft) && matrix[row][colLeft] != wrongValue) {
            sum += matrix[row][colLeft];
        }

        return sum;
    }

    private static boolean isIndexInsideMatrix(int[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length;
    }
}
