import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class SoftUni_Party {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        Set<String> reservationList = new TreeSet<>();

        while (!"PARTY".equals(command)) {
            reservationList.add(command);
            command = scanner.nextLine();
        }
        command = scanner.nextLine();
        while (!"END".equals(command)) {
            reservationList.remove(command);
            command = scanner.nextLine();
        }

        System.out.println(reservationList.size());
        for (String guest : reservationList) {
            System.out.println(guest);
        }
    }
}
