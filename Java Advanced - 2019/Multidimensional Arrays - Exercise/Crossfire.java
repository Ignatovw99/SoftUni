import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Crossfire {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        ArrayList<ArrayList<Integer>> matrix = fillMatrix(dimensions);

        String inputLine = scanner.nextLine();

        while (!"Nuke it from orbit".equals(inputLine)) {
            int[] tokens = Arrays.stream(inputLine.split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            destroyCellsInMatrix(matrix, tokens);
            inputLine = scanner.nextLine();
        }

        printMatrix(matrix);
    }

    private static void printMatrix(ArrayList<ArrayList<Integer>> matrix) {
        for (int row = 0; row < matrix.size(); row++) {
            for (int col = 0; col < matrix.get(row).size(); col++) {
                System.out.print(matrix.get(row).get(col) + " ");
            }
            System.out.println();
        }
    }

    private static void destroyCellsInMatrix(ArrayList<ArrayList<Integer>> matrix, int[] tokens) {
        int row = tokens[0];
        int col = tokens[1];
        int radius = tokens[2];

        long[] rangeHorizontalLine = getHorizontalRange(matrix, row, col, radius);
        long[] rangeVerticalLine = getVerticalRange(matrix, row, radius);

        //Destroying cells vertically
        for (int r = (int)rangeVerticalLine[0]; r <= rangeVerticalLine[1]; r++) {
            if (isIndexValid(matrix, r, col) && r != row) {
                matrix.get(r).remove(col);
            }
        }

        //Destroying cells horizontally
        for (int c = (int)rangeHorizontalLine[1]; c >= rangeHorizontalLine[0]; c--) {
            if (isIndexValid(matrix, row, c)) {
                matrix.get(row).remove(c);
            }
        }

        //remove empty lines
        for (int rowIndex = 0; rowIndex < matrix.size(); rowIndex++)
        {
            ArrayList<Integer> currentLine = matrix.get(rowIndex);

            if (currentLine.size() == 0) {
                matrix.remove(rowIndex);
                rowIndex--;
            }
        }
    }

    private static boolean isIndexValid(ArrayList<ArrayList<Integer>> matrix, int row, int col) {
        return row >= 0 && row < matrix.size() && col >= 0 && col < matrix.get(row).size();
    }

    private static long[] getVerticalRange(ArrayList<ArrayList<Integer>> matrix, int row, int radius) {
        long[] range = new long[2];
        long topCoordinate = row - radius;
        if (topCoordinate < 0) {
            topCoordinate = 0;
        }
        long downCoordinate = row + radius;

        if (downCoordinate >= matrix.size()) {
            downCoordinate = matrix.size() - 1;
        }
        range[0] = topCoordinate;
        range[1] = downCoordinate;
        return range;
    }

    private static long[] getHorizontalRange(ArrayList<ArrayList<Integer>> matrix, int row, int col, int radius) {
        long[] range = new long[2];
        long mostLeftCoordinate = col - radius;
        if (mostLeftCoordinate < 0) {
            mostLeftCoordinate = 0;
        }
        long mostRightCoordinate = col + radius;

        if (isIndexValid(matrix, row, col) && mostRightCoordinate >= matrix.get(row).size()) {
            mostRightCoordinate = matrix.get(row).size() - 1;
        }


        range[0] = mostLeftCoordinate;
        range[1] = mostRightCoordinate;
        return range;
    }

    private static ArrayList<ArrayList<Integer>> fillMatrix(int[] dimensions) {
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();

        int value = 1;

        for (int row = 0; row < dimensions[0]; row++) {
            matrix.add(new ArrayList<>());
            for (int col = 0; col < dimensions[1]; col++) {
                matrix.get(row).add(value++);
            }
        }

        return matrix;
    }
}
