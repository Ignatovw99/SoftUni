import java.util.Scanner;

public class Replace_Repeating_Chars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();

        String result = replaceRepeatingChars(inputLine);

        System.out.println(result);
    }

    private static String replaceRepeatingChars(String inputLine) {
        for (int i = 0; i < inputLine.length() - 1; i++){
            char currentChar = inputLine.charAt(i);
            int startIndex = i + 1;
            int endIndex = i;
            for (int j = i + 1; j < inputLine.length(); j++){
                if (currentChar == inputLine.charAt(j)){
                    endIndex = j;
                }
                if (currentChar != inputLine.charAt(j) || j == inputLine.length() - 1) {
                    inputLine = inputLine.substring(0, startIndex) + inputLine.substring(endIndex + 1);
                    break;
                }
            }
        }
        return inputLine;
    }
}
