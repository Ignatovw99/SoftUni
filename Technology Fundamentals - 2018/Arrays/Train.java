import java.util.Arrays;
import java.util.Scanner;

public class Train {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int wagonsCount = Integer.parseInt(scanner.nextLine());
        int[] wagons = new int[wagonsCount];
        int peopleInTrain = 0;

        for (int i = 0; i < wagonsCount; i++) {
            int peopleInCurrentWagon = Integer.parseInt(scanner.nextLine());
            wagons[i] = peopleInCurrentWagon;
            peopleInTrain += peopleInCurrentWagon;
        }

        Arrays.stream(wagons)
                .forEach(w -> System.out.print(w + " "));
        System.out.println("\n" + peopleInTrain);
    }
}
