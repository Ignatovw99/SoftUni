import java.util.Arrays;
import java.util.Scanner;

public class Intersection_Of_Two_Matrices {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int r = Integer.parseInt(scanner.nextLine());
        int c = Integer.parseInt(scanner.nextLine());

        char[][] firstMatrix = readMatrix(r, c, scanner);
        char[][] secondMatrix = readMatrix(r, c, scanner);

        for (int row = 0; row < firstMatrix.length; row++) {
            for (int col = 0; col < secondMatrix[row].length; col++) {
                if (firstMatrix[row][col] == secondMatrix[row][col]) {
                    System.out.print(firstMatrix[row][col] + " ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
    }

    private static char[][] readMatrix(int r, int c, Scanner scanner) {
        int rows = r;
        int cols = c;

        char[][] matrix = new char[rows][cols];

        for (int row = 0; row < matrix.length; row++) {
            char[] line = convertToCharArray(scanner);

            for (int col = 0; col < matrix[row].length; col++) {
                matrix[row][col] = line[col];
            }
        }
        return matrix;
    }

    private static char[] convertToCharArray(Scanner scanner) {
        String[] tokens = scanner.nextLine().split("\\s+");
        char[] arr = new char[tokens.length];

        for (int i = 0; i < tokens.length; i++) {
            arr[i] = tokens[i].charAt(0);
        }
        return arr;
    }
}
