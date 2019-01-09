import java.util.Scanner;

public class String_Explosion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();

        String result = explodeString(inputLine);
        System.out.println(result);
    }

    private static String explodeString(String inputLine) {
        int strength = 0;
        int index = 0;

        while (index < inputLine.length()){
            char currentChar = inputLine.charAt(index);
            if (currentChar == '>'){
                strength += Integer.parseInt(String.valueOf(inputLine.charAt(index + 1)));
            } else {
                if (strength > 0){
                    inputLine = inputLine.substring(0, index) + inputLine.substring(index + 1);
                    index--;
                    strength--;
                }
            }
            index++;
        }
        return inputLine;
    }
}
