import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Scanner;

public class Simple_Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] expressionParts = scanner.nextLine().split("\\s+");
        ArrayDeque<String> expression = new ArrayDeque<>();
        Collections.addAll(expression, expressionParts);

        while (expression.size() > 1) {
            int firstNumber = Integer.parseInt(expression.pop());
            String operator = expression.pop();
            int secondNumber = Integer.parseInt(expression.pop());
            int result = 0;

            switch (operator){
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
            }
            expression.push(String.valueOf(result));
        }
        System.out.println(expression.pop());
    }
}
