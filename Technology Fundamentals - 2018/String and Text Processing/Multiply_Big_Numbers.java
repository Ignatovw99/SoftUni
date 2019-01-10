import java.util.Scanner;

public class Multiply_Big_Numbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String firstNumber = scanner.nextLine();
        String secondNumber = scanner.nextLine();

        if (secondNumber.equals("0")){
            System.out.println("0");
        } else if (secondNumber.equals("1")){
            System.out.println(firstNumber);
        } else {
            multiplyNumbers(firstNumber, secondNumber);
        }
    }

    private static void multiplyNumbers(String firstNumber, String secondNumber) {
        String multiplicationResult = "";
        int digit = Integer.parseInt(secondNumber);
        int currentDigit = 0;
        int remainder = 0;
        while (!firstNumber.equals("")){
            currentDigit = Integer.parseInt(String.valueOf(firstNumber.charAt(firstNumber.length() - 1)));
            int result = currentDigit * digit + remainder;
            multiplicationResult += result % 10;
            result /= 10;
            remainder = result;
            firstNumber = firstNumber.substring(0, firstNumber.length() - 1);
        }
        if (remainder != 0){
            multiplicationResult += remainder;
        }

        printResult(multiplicationResult);
    }

    private static void printResult(String multiplicationResult) {
        for (int i = multiplicationResult.length() - 1; i >= 0; i--){
            System.out.print(multiplicationResult.charAt(i));
        }
    }
}
