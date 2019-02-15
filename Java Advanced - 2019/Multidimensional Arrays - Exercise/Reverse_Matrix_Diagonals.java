import java.util.Arrays;
import java.util.Scanner;

public class Reverse_Matrix_Diagonals {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[][] matrix = fillMatrix(dimensions, scanner);

        printMatrixDiagonalsReversely(matrix);
    }

    private static void printMatrixDiagonalsReversely(int[][] matrix) {
        int startRow = matrix.length - 1;
        int startCol = matrix[0].length - 1;

        int col;
        int row;

        while (startRow >= 0) {
            col = startCol;
            row = startRow;
            while (col < matrix[0].length && row >= 0) {
                System.out.print(matrix[row--][col++] + " ");
            }
            System.out.println();

            startCol--;

            if (startCol == -1) {
                startCol = 0;
                startRow--;
            }
        }
    }

    private static int[][] fillMatrix(int[] dimensions, Scanner sc) {
        int rows = dimensions[0];
        int cols = dimensions[1];

        int[][] matrix = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            int[] elements = Arrays.stream(sc.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = elements[col];
            }
        }

        return matrix;
    }
}
