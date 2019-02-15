import java.util.Arrays;
import java.util.Scanner;

public class Compare_Matrices {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] rowsCols = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[][] firstMatrix = readMatrix(rowsCols, scanner);

        rowsCols = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[][] secondMatrix = readMatrix(rowsCols, scanner);

        boolean areMatricesEqual = checkMatricesEquality(firstMatrix, secondMatrix);

        if (areMatricesEqual) {
            System.out.println("equal");
        } else {
            System.out.println("not equal");
        }
    }

    private static boolean checkMatricesEquality(int[][] firstMatrix, int[][] secondMatrix) {
        if (firstMatrix.length == secondMatrix.length && firstMatrix[0].length == secondMatrix[0].length) {
            for (int i = 0; i < firstMatrix.length; i++) {
                for (int j = 0; j < secondMatrix[i].length; j++) {
                    if (firstMatrix[i][j] != secondMatrix[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    private static int[][] readMatrix(int[] rowsCols, Scanner scanner) {
        int rows = rowsCols[0];
        int cols = rowsCols[1];

        int[][] matrix = new int[rows][cols];

        for(int i = 0; i < matrix.length; i++) {
            int[] lineElements = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = lineElements[j];
            }
        }

        return matrix;
    }
}
