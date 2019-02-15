import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class String_Matrix_Rotation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> inputLines = new ArrayList<>();

        String command = scanner.nextLine();
        String line = scanner.nextLine();
        int longestWord = 0;

        while (!line.equals("END")) {
            inputLines.add(line);
            if (line.length() > longestWord) {
                longestWord = line.length();
            }
            line = scanner.nextLine();
        }

        char[][] matrix = fillMatrix(inputLines.size(), longestWord, inputLines);

        int rotationAngle = Integer.parseInt(command.substring(command.lastIndexOf("(") + 1, command.lastIndexOf(")"))) % 360;

        switch (rotationAngle) {
            case 90:
                rotateMatrixBy90Angles(matrix);
                break;
            case 180:
                rotateMatrixBy180Angles(matrix);
                break;
            case 270:
                rotateMatrixBy270Angles(matrix);
                break;
                default:
                    printMatrix(matrix);
                    break;
        }
    }

    private static void rotateMatrixBy270Angles(char[][] matrix) {
        char[][] rotatedMatrix = new char[matrix[0].length][matrix.length];

        for (int col = 0; col < rotatedMatrix.length; col++) {
            for (int row = 0; row < rotatedMatrix[col].length; row++) {
                rotatedMatrix[rotatedMatrix.length - 1 - col][row] = matrix[row][col];
            }
        }

        printMatrix(rotatedMatrix);
    }

    private static void printMatrix(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == '\u0000') {
                    System.out.print(" ");
                } else {
                    System.out.print(matrix[row][col]);
                }
            }
            System.out.println();
        }
    }

    private static void rotateMatrixBy180Angles(char[][] matrix) {
        char[][] rotatedMatrix = new char[matrix.length][matrix[0].length];

        for (int row = 0; row < rotatedMatrix.length; row++) {
            for (int col = 0; col < rotatedMatrix[0].length; col++) {
                rotatedMatrix[matrix.length - 1 - row][matrix[row].length - 1 - col] = matrix[row][col];
            }
        }
        printMatrix(rotatedMatrix);
    }

    private static char[][] fillMatrix(int rows, int cols, List<String> lines) {
        char[][] matrix = new char[rows][cols];

        for (int row = 0; row < rows; row++) {
            String line = lines.get(row);
            for (int col = 0; col < line.length(); col++) {
                matrix[row][col] = line.charAt(col);
            }
        }

        return matrix;
    }

    private static void rotateMatrixBy90Angles(char[][] matrix) {
        char[][] rotatedMatrix = new char[matrix[0].length][matrix.length];

        for (int row = 0; row < rotatedMatrix.length; row++) {
            for (int col = 0; col < rotatedMatrix[row].length; col++) {
                rotatedMatrix[row][col] = matrix[matrix.length - 1 - col][row];
            }
        }
        printMatrix(rotatedMatrix);
    }
}
