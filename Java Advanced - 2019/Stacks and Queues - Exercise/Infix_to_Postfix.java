import java.util.ArrayDeque;
import java.util.Scanner;

public class Infix_to_Postfix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] infixExpression = scanner.nextLine().split("\\s+");
        String postfixExpression = infixToPostfix(infixExpression);
        System.out.println(postfixExpression);
    }

    private static String infixToPostfix(String[] infixExpression) {
        String result = "";

        ArrayDeque<String> stackWithOperators = new ArrayDeque<>();

        for (int i = 0; i < infixExpression.length; i++) {
            String current = infixExpression[i];

            if (isSymbolLetterOrNumber(current)) {
                result += current + " ";
            } else if (current.equals("(")) {
                stackWithOperators.push(current);
            } else if (current.equals(")")) {
                while (!stackWithOperators.isEmpty() && !stackWithOperators.peek().equals("(")) {
                    result += stackWithOperators.pop() + " ";
                }
                if (!stackWithOperators.isEmpty()) {
                    stackWithOperators.pop();
                }
            } else {  //an operator is encountered
                while (!stackWithOperators.isEmpty() &&
                        getOperatorPrecedence(current) <= getOperatorPrecedence(stackWithOperators.peek())) {
                    result += stackWithOperators.pop() + " ";
                }
                stackWithOperators.push(current);
            }
        }
        //get all operators in the stack
        while (!stackWithOperators.isEmpty()) {
            result += stackWithOperators.pop() + " ";
        }

        return result;
    }

    private static boolean isSymbolLetterOrNumber(String symbol) {
        if(tryParseInt(symbol) || (symbol.charAt(0) >= 'a' && symbol.charAt(0) <= 'z')) {
            return true;
        }
        return false;
    }

    private static boolean tryParseInt(String current) {
        try {
            Integer.parseInt(current);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static int getOperatorPrecedence(String operator) {
        //Higher returned value means higher precedence
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
        }
        return 0;
    }
}
