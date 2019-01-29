import java.util.Scanner;

public class Fix_Emails {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!"stop".equals(command)) {
            String name = command;
            String email = scanner.nextLine();

            if (isEmailValid(email)) {
                System.out.println(String.format("%s -> %s", name, email));
            }
            command = scanner.nextLine();
        }
    }

    private static boolean isEmailValid(String email) {
        int lastDotIndex = email.lastIndexOf('.');
        if (lastDotIndex != -1) {
            String domainEnd = email.substring(lastDotIndex + 1).toLowerCase();
            switch (domainEnd) {
                case "us":
                case "uk":
                case "com":
                    return false;
            }
            return true;
        }
        return false;
    }
}
