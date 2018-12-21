import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class SoftUniParking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, String> parkingSystem = new LinkedHashMap<>();

        int countCommands = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < countCommands; i++){
            String[] tokens = scanner.nextLine().split("\\s+");

            String command = tokens[0];
            String username = tokens[1];

            switch (command){
                case "register":
                    String licensePlateNumber = tokens[2];

                    if (parkingSystem.containsKey(username)){
                        System.out.println("ERROR: already registered with plate number " + parkingSystem.get(username));
                    } else {
                        parkingSystem.put(username, licensePlateNumber);
                        System.out.println(String.format("%s registered %s successfully", username, licensePlateNumber));
                    }
                    break;
                case "unregister":
                    if (!parkingSystem.containsKey(username)){
                        System.out.printf("ERROR: user %s not found%n", username);
                    } else {
                        parkingSystem.remove(username);
                        System.out.println(username + " unregistered successfully");
                    }
                    break;
            }
        }
        parkingSystem.forEach((key, value) -> System.out.printf("%s => %s%n", key, value));
    }
}
