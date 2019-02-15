import java.util.Arrays;
import java.util.Scanner;

public class Diagonal_Difference {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());

        int[][] matrix = new int[size][size];

        fillTheMatrix(matrix, scanner);

        int diagonalDifference = getDiagonalDifference(matrix);

        System.out.println(diagonalDifference);
    }

    private static int getDiagonalDifference(int[][] matrix) {
        int primaryDiagonal = 0;
        int secondaryDiagonal = 0;

        int col = 0;

        for (int row = 0; row < matrix.length; row++) {
            primaryDiagonal += matrix[row][col];
            secondaryDiagonal += matrix[matrix.length - 1 - row][col];
            col += 1;
        }

        return Math.abs(primaryDiagonal - secondaryDiagonal);
    }

    private static void fillTheMatrix(int[][] matrix, Scanner sc) {
        for (int row = 0; row < matrix.length; row++) {
            int[] lineElements = Arrays.stream(sc.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            for (int col = 0; col < matrix[row].length; col++) {
                matrix[row][col] = lineElements[col];
            }
        }
    }
}
