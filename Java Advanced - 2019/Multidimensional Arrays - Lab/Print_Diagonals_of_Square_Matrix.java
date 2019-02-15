import java.util.Arrays;
import java.util.Scanner;

public class Print_Diagonals_of_Square_Matrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        int[][] matrix = readMatrix(n, scanner);

        printMatrixDiagonals(matrix);
    }

    private static void printMatrixDiagonals(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(matrix[i][i] + " ");
        }
        System.out.println();
        for (int i = matrix.length - 1; i >= 0; i--) {
            System.out.print(matrix[i][matrix.length - 1 - i] + " ");
        }
        System.out.println();
    }

    private static int[][] readMatrix(int n, Scanner scanner) {
        int[][] matrix = new int[n][];

        for (int i = 0; i < matrix.length; i++) {
            int[] line = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            matrix[i] = line;
        }

        return matrix;
    }
}
