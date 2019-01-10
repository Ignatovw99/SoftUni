import java.util.Scanner;

public class Caesar_Cipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String decryptedText = scanner.nextLine();
        String encryptedText = encryptText(decryptedText);
        System.out.println(encryptedText);
    }

    private static String encryptText(String decryptedText) {
        String result = "";
        for (int i = 0; i < decryptedText.length(); i++){
            char currentChar = decryptedText.charAt(i);
            currentChar = (char)(currentChar + 3);
            result += currentChar;
        }
        return result;
    }
}
