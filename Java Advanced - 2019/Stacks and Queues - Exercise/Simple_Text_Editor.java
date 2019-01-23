import java.util.ArrayDeque;
import java.util.Scanner;

public class Simple_Text_Editor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int operationsCount = Integer.parseInt(scanner.nextLine());
        String text = "";
        String currentStatment = "";

        ArrayDeque<String> previousUpdates = new ArrayDeque<>();

        for (int i = 0; i < operationsCount; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");

            int operationCode = Integer.parseInt(tokens[0]);

            switch (operationCode) {
                case 1:
                    text = currentStatment;
                    String textToAdd = tokens[1];
                    currentStatment += textToAdd;
                    previousUpdates.push(text);
                    break;
                case 2:
                    text = currentStatment;
                    int count = Integer.parseInt(tokens[1]);
                    currentStatment = currentStatment.substring(0, text.length() - count);
                    previousUpdates.push(text);
                    break;
                case 3:
                    int index = Integer.parseInt(tokens[1]);
                    printCharacter(currentStatment, index);
                    break;
                case 4:
                    currentStatment = previousUpdates.poll();
                    break;
            }
        }
    }

    private static void printCharacter(String text, int index) {
        if (index >= 0 && index <= text.length()) {
            System.out.println(text.charAt(index - 1));
        }
    }
}
