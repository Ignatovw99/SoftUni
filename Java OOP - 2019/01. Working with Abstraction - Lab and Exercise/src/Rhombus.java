import java.util.Scanner;

public class Rhombus {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int maxWidth = Integer.parseInt(scanner.nextLine());

        printRhombus(maxWidth);
    }

    private static void printRhombus(int maxWidth) {
        printUpperPart(maxWidth);
        printMiddlePart(maxWidth);
        printLowerPart(maxWidth);
    }

    private static void printLowerPart(int maxWidth) {
        for (int i = maxWidth - 1; i > 0; i--) {
            printLine(maxWidth, i);
        }
    }

    private static void printMiddlePart(int maxWidth) {
        printLine(maxWidth, maxWidth);
    }

    private static void printUpperPart(int maxWidth) {
        for (int i = 1; i <= maxWidth - 1; i++) {
            printLine(maxWidth, i);
        }
    }

    private static void printLine(int maxWidth, int row) {
        System.out.print(repeatString(" ", maxWidth - row));
        System.out.println(repeatString("* ", row));
    }

    private static String repeatString(String string, int times) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < times; i++) {
            builder.append(string);
        }
        return builder.toString();
    }
}
