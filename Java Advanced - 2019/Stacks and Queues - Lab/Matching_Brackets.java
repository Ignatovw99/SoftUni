import java.util.ArrayDeque;
import java.util.Scanner;

public class Matching_Brackets {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();

        ArrayDeque<Integer> openBracketsIndexes = new ArrayDeque<>();

        for (int i = 0; i < expression.length(); i++) {
            char currentChar = expression.charAt(i);

            if (currentChar == '(') {
                openBracketsIndexes.push(i);
            }
            if (currentChar == ')') {
                int lastOpenBracketIndex = openBracketsIndexes.pop();
                String subexpression = expression.substring(lastOpenBracketIndex, i + 1);
                System.out.println(subexpression);
            }
        }
    }
}
