import java.util.Scanner;

public class Valid_Usernames {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] usernames = scanner.nextLine().split(", ");

        for (int i = 0; i < usernames.length; i++){
            String username = usernames[i];

            boolean isUsernameValid = checkUsername(username);
            if (isUsernameValid){
                System.out.println(username);
            }
        }
    }

    private static boolean checkUsername(String username) {
        int usernameLength = username.length();
        if (usernameLength >= 3 && usernameLength <= 16){
            for (int i = 0; i < usernameLength; i++){
                char currentChar = username.charAt(i);
                if (Character.isLetter(currentChar) || Character.isDigit(currentChar) || currentChar == '-' || currentChar == '_'){
                    continue;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }
}
