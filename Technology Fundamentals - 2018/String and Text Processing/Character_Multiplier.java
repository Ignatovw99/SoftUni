import java.util.Scanner;

public class Character_Multiplier {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String[] inputStrings = scanner.nextLine().split("\\s+");
        String firstString = inputStrings[0];
        String secondString = inputStrings[1];
        int characterSum = getCharacterSum(firstString, secondString);
        System.out.println(characterSum);
    }

    private static int getCharacterSum(String firstString, String secondString) {
        int length = firstString.length() > secondString.length() ? firstString.length() : secondString.length();
        int sum = 0;
        int characterProduct = 1;
        for (int i = 0; i < length; i++){
            if (i < firstString.length()){
                characterProduct *= firstString.charAt(i);
            }
            if (i < secondString.length()){
                characterProduct *= secondString.charAt(i);
            }
            if (characterProduct != 1){
                sum += characterProduct;
            }
            characterProduct = 1;
        }
        return sum;
    }
}
