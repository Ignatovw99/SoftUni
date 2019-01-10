import java.util.Scanner;

public class Letters_Change_Numbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] stringsSequence = scanner.nextLine().split("\\s+");
        double totalSum = 0.0;

        for (int i = 0; i < stringsSequence.length; i++) {
            String currentString = stringsSequence[i];
            char firstChar = currentString.charAt(0);
            char lastChar = currentString.charAt(currentString.length() - 1);
            double number = Double.parseDouble(currentString.substring(1, currentString.length() - 1));

            int firstCharacterPosition = 1;
            int lastCharaceterPosition = 1;

            if (Character.isLowerCase(firstChar)){
                firstCharacterPosition += firstChar - 'a';
                number *= firstCharacterPosition;
            } else {
                firstCharacterPosition += firstChar - 'A';
                number /= firstCharacterPosition;
            }

            if (Character.isLowerCase(lastChar)){
                lastCharaceterPosition += lastChar - 'a';
                number += lastCharaceterPosition;
            } else {
                lastCharaceterPosition += lastChar - 'A';
                number -= lastCharaceterPosition;
            }

            totalSum += number;
        }

        System.out.println(String.format("%.2f", totalSum));
    }
}
