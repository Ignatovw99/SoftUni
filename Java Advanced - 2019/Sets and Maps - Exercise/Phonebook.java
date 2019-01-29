import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Phonebook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> phonebook = new HashMap<>();
        String command = scanner.nextLine();

        while (!"search".equals(command)) {
            String[] tokens = command.split("-");
            String name = tokens[0];
            String number = tokens[1];
            phonebook.put(name, number);
            command = scanner.nextLine();
        }

        command = scanner.nextLine();

        while (!"stop".equals(command)) {
            String contact = command;

            if (phonebook.containsKey(contact)) {
                String contactNumber = phonebook.get(contact);
                System.out.println(String.format("%s -> %s", contact, contactNumber));
            } else {
                System.out.println(String.format("Contact %s does not exist.", contact));
            }
            command = scanner.nextLine();
        }
    }
}
