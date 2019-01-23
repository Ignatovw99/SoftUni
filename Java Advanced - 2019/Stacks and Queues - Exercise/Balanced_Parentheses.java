import java.util.ArrayDeque;
import java.util.Scanner;

public class Balanced_Parentheses {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String parentheses = scanner.nextLine();
        ArrayDeque<Character> stackByParentheses = new ArrayDeque<>();
        boolean isBalanced = true;

        for (int i = 0; i < parentheses.length(); i++) {
            char current = parentheses.charAt(i);
            char lastCharacterInStack;

            if (current == ' ') {  // additional functionality
                if (stackByParentheses.isEmpty()) {
                    stackByParentheses.push(current);
                } else {
                    lastCharacterInStack = stackByParentheses.peek();
                    if (lastCharacterInStack != ' ') {
                        stackByParentheses.push(current);
                    } else {
                        if (lastCharacterInStack != current) {
                            isBalanced = false;
                        }
                        stackByParentheses.pop();
                    }
                }
            } else {
                if (current == '{' || current == '[' || current == '(') {
                    stackByParentheses.push(current);
                } else if (current == '}') {
                    if (stackByParentheses.isEmpty()) {
                        isBalanced = false;
                    } else {
                        lastCharacterInStack = stackByParentheses.pop();
                        if (lastCharacterInStack != '{') {
                            isBalanced = false;
                        }
                    }
                } else if (current == ']') {
                    if (stackByParentheses.isEmpty()) {
                        isBalanced = false;
                    } else {
                        lastCharacterInStack = stackByParentheses.pop();
                        if (lastCharacterInStack != '[') {
                            isBalanced = false;
                        }
                    }
                } else if (current == ')') {
                    if (stackByParentheses.isEmpty()) {
                        isBalanced = false;
                    } else {
                        lastCharacterInStack = stackByParentheses.pop();
                        if (lastCharacterInStack != '(') {
                            isBalanced = false;
                        }
                    }
                }
            }

            if (isBalanced == false) {
                System.out.println("NO");
                return;
            }
        }
        if (stackByParentheses.isEmpty()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
