import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

//Result 90/100 - One test - time limit
public class The_Matrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] matrix = fillMatrix(scanner);
        char fillChar = scanner.nextLine().charAt(0);
        int[] startPosition = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int startRow = startPosition[0];
        int startCol = startPosition[1];
        char startChar = matrix[startRow][startCol];

        ArrayDeque<Cell> cells = new ArrayDeque<>();//Queue, which stores all cells equal to start cell
        cells.offer(new Cell(startRow, startCol));

        while (!cells.isEmpty()) {
            Cell currentCell = cells.poll();
            int cellRow = currentCell.getRow();
            int cellCol = currentCell.getCol();

            matrix[cellRow][cellCol] = fillChar;

            if (cellRow - 1 >= 0 && matrix[cellRow - 1][cellCol] == startChar) {
                cells.offer(new Cell(cellRow - 1, cellCol));
            }
            if (cellCol + 1 < matrix[0].length && matrix[cellRow][cellCol + 1] == startChar) {
                cells.offer(new Cell(cellRow, cellCol + 1));
            }
            if (cellRow + 1 < matrix.length && matrix[cellRow + 1][cellCol] == startChar) {
                cells.offer(new Cell(cellRow + 1, cellCol));
            }
            if (cellCol - 1 >= 0 && matrix[cellRow][cellCol - 1] == startChar) {
                cells.offer(new Cell(cellRow, cellCol - 1));
            }
        }
        printMatrix(matrix);
    }

    private static void printMatrix(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }

    private static char[][] fillMatrix(Scanner scanner) {
        int[] dimensions = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int rows = dimensions[0];
        int cols = dimensions[1];

        char[][] matrix = new char[rows][cols];

        for (int row = 0; row < matrix.length; row++) {
            String[] lineArguments = scanner.nextLine().split("\\s+");
            for (int col = 0; col < matrix[0].length; col++) {
                matrix[row][col] = lineArguments[col].charAt(0);
            }
        }
        return matrix;
    }
}

class Cell {
    private int row;
    private int col;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}